package com.ecommerce.inventoryservice.Service;

import com.ecommerce.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String productName, int quantity) {
        return inventoryRepository.findByProductName(productName)
                .map(i -> i.getQuantity() >= quantity)
                .orElse(false);
    }
}
