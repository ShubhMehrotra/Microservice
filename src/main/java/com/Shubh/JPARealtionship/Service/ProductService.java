package com.Shubh.JPARealtionship.Service;


import com.Shubh.JPARealtionship.Entity.Product;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.ProductRequest;

public interface ProductService {

    public ApiResponse addProduct(ProductRequest productRequest);
    public ApiResponse deleteProduct(Long id);
    public ApiResponse updateProduct(Long id,ProductRequest productRequest);
    public Product searcProduct(Long id);
}
