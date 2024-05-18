package com.Shubh.JPARealtionship.Service;


import com.Shubh.JPARealtionship.Entity.Inventory;
import com.Shubh.JPARealtionship.Entity.Product;
import com.Shubh.JPARealtionship.Exception.InventoryNotFoundException;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.InventoryRequest;
import com.Shubh.JPARealtionship.Repository.InventoryRepo;
import com.Shubh.JPARealtionship.Repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryImpl implements InventoryService{

    @Autowired
    private InventoryRepo inventoryRepo;
    @Autowired
    private ProductRepo productRepo;
    @Override
    public ApiResponse addInventory(InventoryRequest inventoryRequest) {
        return Optional.ofNullable(inventoryRequest)
                .map(inventoryRequest1 -> {
                        Inventory inventory1=new Inventory();
                        BeanUtils.copyProperties(inventoryRequest,inventory1);
                        inventoryRepo.save(inventory1);
                        return new ApiResponse(HttpStatus.CREATED,"Inventory Saved Successfully");
                    })
                .orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Inventory Failed to get Added"));

    }

    @Override
    public ApiResponse deleteInventory(Long inventoryId) {
        return inventoryRepo.findById(inventoryId)
                .map(inventory -> {
                    inventoryRepo.deleteById(inventoryId);
                    return new ApiResponse(HttpStatus.OK,"Inventory deleted Successfully");
                }).orElse(new ApiResponse(HttpStatus.NOT_FOUND,"Inventory with this ID"+inventoryId
                +"not exist in the system"));
    }

    @Override
    public ApiResponse updateInventory(Long id, InventoryRequest inventoryRequest) {
        return Optional.ofNullable(inventoryRequest)
                .map(req->{
                    Optional<Product> product=productRepo.findById(inventoryRequest.getProductId());
                    return product.map(product1 -> {
                        Inventory inventory=new Inventory();
                        BeanUtils.copyProperties(inventoryRequest,inventory);
                        return new ApiResponse(HttpStatus.OK,"Inventory updated Successfully");


                    }).orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Inventory for this Product ID:"+
                            inventoryRequest.getProductId()+"not exist"));
                }).orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Inventory Failed to get Updated"));
    }

    @Override
    public Inventory getInventory(Long id) {
        return inventoryRepo.findById(id)
                .orElseThrow(()->new InventoryNotFoundException("Product with this ID"+id+"not found in the system"));
    }
}
