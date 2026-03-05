package com.factory.optimizer.controller;

import com.factory.optimizer.model.Material;
import com.factory.optimizer.service.FactoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materials")
@CrossOrigin(origins = "*")
public class MaterialController {

    private final FactoryService factoryService;

    public MaterialController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @GetMapping
    public List<Material> list() {
        return factoryService.getAllMaterials();
    }

    @PostMapping
    public Material create(@RequestBody Material material) {
        return factoryService.saveMaterial(material);
    }
}