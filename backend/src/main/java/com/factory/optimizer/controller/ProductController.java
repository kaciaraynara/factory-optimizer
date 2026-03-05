package com.factory.optimizer.controller;

import com.factory.optimizer.model.Product;
import com.factory.optimizer.repository.ProductRepository;
import com.factory.optimizer.service.FactoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final FactoryService factoryService;
    private final ProductRepository productRepository;

    // Construtor corrigido para bater com o nome da classe
    public ProductController(FactoryService factoryService, ProductRepository productRepository) {
        this.factoryService = factoryService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> list() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/optimize")
    public Map<String, Integer> runOptimize() {
        return factoryService.calculateMaxProfitProduction();
    }
}