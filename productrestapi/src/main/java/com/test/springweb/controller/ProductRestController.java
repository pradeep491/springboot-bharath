package com.test.springweb.controller;

import com.test.springweb.entity.Product;
import com.test.springweb.repos.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductRestController {

    private ProductRepository repo;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return repo.findById(id).get();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        return repo.save(product);
    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }
    @GetMapping("/productsinfo")
    public String productInfo() {
        return "hello from product controller";
    }
}
