package com.Shubh.JPARealtionship.Service;


import com.Shubh.JPARealtionship.Entity.Product;
import com.Shubh.JPARealtionship.Exception.ProductNotFoundException;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.ProductRequest;
import com.Shubh.JPARealtionship.Repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;
    @Override
    public ApiResponse addProduct(ProductRequest productRequest) {
        return Optional.ofNullable(productRequest)
                .map(request->{
                    Product product=new Product();
                    BeanUtils.copyProperties(productRequest,product);
                    productRepo.save(product);
                    return new ApiResponse(HttpStatus.CREATED,"Product created Successfully");
                })
                .orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Product Failed to get Added"));
    }

    @Override
    public ApiResponse deleteProduct(Long id) {
        return productRepo.findById(id)
                .map(product -> {
                    productRepo.deleteById(id);
                    return new ApiResponse(HttpStatus.OK,"Address deleted Successfully");
                }).orElse(new ApiResponse(HttpStatus.NOT_FOUND,"Address with thi ID"+id+"not found in the System"));
    }

    @Override
    public ApiResponse updateProduct(Long id, ProductRequest productRequest) {
        return Optional.ofNullable(productRequest)
                .map(productRequest1 -> {
                    Product product=new Product();
                    BeanUtils.copyProperties(productRequest,product);
                    productRepo.save(product);
                    return new ApiResponse(HttpStatus.CREATED,"Product Updated Successfully");
                }).orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Address with thi ID"+id+"not found in the System"));
    }

    @Override
    public Product searcProduct(Long id) {
        return productRepo.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product with this ID "+id+"not found in the system"));
    }
}
