package com.factory.optimizer.service;

import com.factory.optimizer.model.*;
import com.factory.optimizer.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FactoryService {

    private final MaterialRepository materialRepository;
    private final ProductRepository productRepository;

    public FactoryService(MaterialRepository materialRepository, ProductRepository productRepository) {
        this.materialRepository = materialRepository;
        this.productRepository = productRepository;
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Map<String, Integer> calculateMaxProfitProduction() {
        // CORREÇÃO: Usamos ArrayList para evitar o erro de lista imutável no sort
        List<Product> products = new ArrayList<>(productRepository.findAll());
        List<Material> materials = materialRepository.findAll();

        if (materials.isEmpty() || products.isEmpty())
            return new HashMap<>();

        Map<Long, Double> currentStock = materials.stream()
                .collect(Collectors.toMap(Material::getId, m -> m.getStockQuantity().doubleValue()));

        // Ordenação por preço (Lógica de P&D: Priorizar lucro)
        products.sort((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()));

        Map<String, Integer> plan = new LinkedHashMap<>();

        for (Product product : products) {
            if (product.getComposition() == null || product.getComposition().isEmpty())
                continue;

            int unitsToMake = Integer.MAX_VALUE;
            for (ProductIngredient ing : product.getComposition()) {
                double available = currentStock.getOrDefault(ing.getMaterial().getId(), 0.0);
                double required = ing.getQuantityRequired().doubleValue();
                if (required > 0)
                    unitsToMake = Math.min(unitsToMake, (int) (available / required));
            }

            if (unitsToMake > 0 && unitsToMake != Integer.MAX_VALUE) {
                plan.put(product.getName(), unitsToMake);
                for (ProductIngredient ing : product.getComposition()) {
                    Long matId = ing.getMaterial().getId();
                    double consumed = unitsToMake * ing.getQuantityRequired().doubleValue();
                    currentStock.put(matId, currentStock.get(matId) - consumed);
                }
            }
        }
        return plan;
    }
}