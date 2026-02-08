package com.test.springweb.controller;

import com.test.springweb.entity.Product;
import com.test.springweb.repos.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void deleteProduct(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/productsinfo")
    public String productInfo() {
        return "OK";
    }
}
