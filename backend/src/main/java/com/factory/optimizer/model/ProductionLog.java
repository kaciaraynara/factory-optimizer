package com.factory.optimizer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "production_logs")
public class ProductionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String productName;
    private int quantity;
    private LocalDateTime timestamp;

    public ProductionLog() {}

    public ProductionLog(String productName, int quantity, LocalDateTime timestamp) {
        this.productName = productName;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    // Getters
    public Long getId() { return id; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getTimestamp() { return timestamp; }
}