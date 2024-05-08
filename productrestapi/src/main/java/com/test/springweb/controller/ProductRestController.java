package com.test.springweb.controller;

import com.test.springweb.entity.Product;
import com.test.springweb.repos.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("/product")
public class ProductRestController {

    private ProductRepository repo;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @GetMapping("/prodcuts/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return repo.findById(id).get();
    }

    @PostMapping("/prodcuts")
    public List<Product> createProduct(@RequestBody List<Product> product) {
        return repo.saveAll(product);
    }

    @PutMapping("/prodcuts")
    public Product updateProduct(@RequestBody Product product) {
        return repo.save(product);
    }
    @DeleteMapping("/prodcuts/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }
}
