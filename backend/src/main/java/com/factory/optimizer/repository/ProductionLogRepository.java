package com.factory.optimizer.repository;

import com.factory.optimizer.model.ProductionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionLogRepository extends JpaRepository<ProductionLog, Long> {
}