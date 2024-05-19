package com.test.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductrestapiCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductrestapiCacheApplication.class, args);
	}

}
