package com.test.config;

import com.test.model.Product;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    private final DataSource dataSource;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    public BatchConfig(DataSource dataSource, JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public ItemReader<Product> productItemReader() {
        //FlatFileItemReader<Product> flatFileItemReader = new FlatFileItemReaderBuilder<Product>();
        //flatFileItemReader.setResource(new ClassPathResource("products.csv"));

        return new FlatFileItemReaderBuilder<Product>()
                .name("productItemReader")
                .resource(new ClassPathResource("products.csv"))
                .delimited()
                .delimiter(",")
                .names(new String[]{"id", "name",
                        "description", "price"})
                .linesToSkip(1)
                .fieldSetMapper(
                        new BeanWrapperFieldSetMapper<Product>() {
                            {
                                setTargetType(Product.class);
                            }
                        })
                .build();


    }

    @Bean
    public ItemProcessor<Product, Product> productItemProcessor() {
        return new ItemProcessor<Product, Product>() {
            @Override
            public Product process(Product product)
                    throws Exception {
                if (product.getPrice() == 0) {
                    return null; // do not store if quantity is zero
                }
                product.setPrice(
                        product.getPrice()
                                - product.getPrice() * 10 / 100);
                return product;
            }
        };
    }

    @Bean
    public JdbcBatchItemWriter<Product> productItemWriter() {
        return new JdbcBatchItemWriterBuilder<Product>()
                .itemSqlParameterSourceProvider(
                        new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO product1 (id, name, description, price) " +
                        "VALUES (:id, :name, :description,:price)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step step() {
        StepBuilder stepBuilder = new StepBuilder("step-1", jobRepository);
        return stepBuilder.<Product, Product>chunk(3)
                .reader(productItemReader())
                .processor(productItemProcessor())
                .writer(productItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return new JobBuilder("job-1", jobRepository)
                .flow(step())
                .end()
                .build();
    }
}
