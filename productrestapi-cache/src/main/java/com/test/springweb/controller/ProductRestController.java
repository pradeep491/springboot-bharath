package com.test.springweb.controller;

import com.test.springweb.entity.Product;
import com.test.springweb.repos.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductRestController {
    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private ProductRepository repo;

    @GetMapping("/products/")
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @GetMapping("/products/{id}")
    @Transactional(readOnly = true)
    @Cacheable("product-cache")
    public Product getProduct(@PathVariable("id") Long id) {
        logger.info("finding product by id:"+id);
        return repo.findById(id).get();
    }

    @PostMapping("/products/")
    public Product createProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    @PutMapping("/products/")
    public Product updateProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    @DeleteMapping("/products/{id}")
    @CacheEvict("product-cache")
    public void deleteProduct(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/productsinfo")
    public String productInfo() {
        return "hello from product controller";
    }
}
