package com.Shubh.JPARealtionship.Service;

import com.Shubh.JPARealtionship.Entity.LineItem;
import com.Shubh.JPARealtionship.Exception.LineItemNotFoundException;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.LineItemRequest;
import com.Shubh.JPARealtionship.Repository.LineItemRepo;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class LineItemServiceImpl implements LineItemService {
    @Autowired
    private LineItemRepo lineItemRepo;
    @Override
    public ApiResponse addLineItem(@Valid LineItemRequest lineItemRequest) {
        return Optional.ofNullable(lineItemRequest)
                .map(request->{
                    LineItem item= new LineItem();
                    BeanUtils.copyProperties(lineItemRequest,item);
                    lineItemRepo.save(item);
                    return new ApiResponse(HttpStatus.CREATED,"Line Item created with ID:" + item.getItemId());
               }).orElse( new ApiResponse(HttpStatus.BAD_REQUEST,"Line Item failed to get Added"));
}
   @Override
    public ApiResponse deleteLineItem(Long id) {
        return lineItemRepo.findById(id)
       .map(lineItem -> { lineItemRepo.deleteById(id);
       return  new ApiResponse(HttpStatus.OK,"Line Item with ID:"+id+"deleted successfully");
}).orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Line Item with this ID not exist in the system"));
}

    @Override
    public ApiResponse updateLineItem(Long id, LineItemRequest lineItemRequest) {
        return Optional.ofNullable(lineItemRequest)
        .map(lineItemRequest1 -> {
                LineItem item=new LineItem();
                BeanUtils.copyProperties(lineItemRequest,item);
                lineItemRepo.save(item);
                return new ApiResponse(HttpStatus.OK,"Line Item Updated Successfully");
        }).orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Line Item with ID:"+id+"not exist in the system"));

    }
    @Override
    public LineItem getLineItem(Long id) {
        return lineItemRepo.findById(id)
                .orElseThrow(()->new LineItemNotFoundException("Line Item with ID:"+id+"not exist in the system"));
    }
}
