package com.shubh.product.Service;

import com.shubh.product.Payload.ApiResponse;
import com.shubh.product.Payload.ProductRequest;
import com.shubh.product.Entity.Product;

public interface ProductService {

    public ApiResponse addProduct(ProductRequest productRequest);
    public ApiResponse deleteProduct(Long id);
    public ApiResponse updateProduct(Long id,ProductRequest productRequest);
    public Product searcProduct(Long id);
}
