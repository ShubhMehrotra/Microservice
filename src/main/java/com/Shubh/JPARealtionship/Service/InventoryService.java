package com.Shubh.JPARealtionship.Service;


import com.Shubh.JPARealtionship.Entity.Inventory;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.InventoryRequest;

public interface InventoryService {

    public ApiResponse addInventory(InventoryRequest inventoryRequest);
    public ApiResponse deleteInventory(Long inventoryId);
    public ApiResponse updateInventory(Long id,InventoryRequest inventoryRequest);
    public Inventory getInventory(Long id);
}
