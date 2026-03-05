package com.factory.optimizer.service; // Pacote deve bater com a pasta service

import com.factory.optimizer.model.*;
import com.factory.optimizer.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FactoryServiceTest {
    @Mock
    private MaterialRepository materialRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private FactoryService factoryService;

    @Test
    void testOptimization() {
        assertNotNull(factoryService);
    }
}