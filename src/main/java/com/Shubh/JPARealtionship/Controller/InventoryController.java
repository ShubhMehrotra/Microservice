package com.Shubh.JPARealtionship.Controller;


import com.Shubh.JPARealtionship.Entity.Inventory;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.InventoryRequest;
import com.Shubh.JPARealtionship.Service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/addInventory")
    public ResponseEntity<ApiResponse> addInventory(@Valid @RequestBody InventoryRequest inventoryRequest)
    {
        return ResponseEntity.ofNullable(inventoryService.addInventory(inventoryRequest));

    }

    @DeleteMapping("/deleteInventory/{id}")
    public ResponseEntity<ApiResponse> deleteInventory(@Valid @PathVariable Long id)
    {
        return ResponseEntity.ofNullable(inventoryService.deleteInventory(id));

    }

    @PutMapping("updateInventory/{id}")
    public ResponseEntity<ApiResponse> updateInventory(@Valid @RequestBody InventoryRequest inventoryRequest , @PathVariable Long id)
    {
        return ResponseEntity.ofNullable(inventoryService.updateInventory(id, inventoryRequest));
    }

    @GetMapping("getInventory/{id}")
    public ResponseEntity<Inventory> getInventory(@Valid @PathVariable Long id)
    {
        return ResponseEntity.of(Optional.ofNullable(inventoryService.getInventory(id)));
    }



}
