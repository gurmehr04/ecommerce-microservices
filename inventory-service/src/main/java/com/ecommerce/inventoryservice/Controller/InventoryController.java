package com.ecommerce.inventoryservice.Controller;

import com.ecommerce.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/check/{productName}/{quantity}")
    public String checkStock(@PathVariable String productName, @PathVariable int quantity) {
        return inventoryService.isInStock(productName, quantity) ? "In Stock" : "Out of Stock";
    }
}
