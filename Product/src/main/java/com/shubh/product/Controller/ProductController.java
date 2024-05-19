package com.shubh.product.Controller;


import com.shubh.product.Payload.ApiResponse;
import com.shubh.product.Payload.ProductRequest;
import com.shubh.product.Entity.Product;
import com.shubh.product.Service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;
    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody ProductRequest productRequest)
    {
        return ResponseEntity.of(Optional.ofNullable(productService.addProduct(productRequest)));
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id)
    {
        return ResponseEntity.of(Optional.ofNullable(productService.deleteProduct(id)));

    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest)
    {
        return  ResponseEntity.of(Optional.ofNullable(productService.updateProduct(id, productRequest)));

    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getUserById(@PathVariable Long id)
    {

        return ResponseEntity.of(Optional.ofNullable(productService.searcProduct(id)));
    }






}
