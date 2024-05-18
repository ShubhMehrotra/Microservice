package com.Shubh.JPARealtionship.Service;

import com.Shubh.JPARealtionship.Entity.LineItem;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.LineItemRequest;

public interface LineItemService {

    public ApiResponse addLineItem(LineItemRequest lineItemRequest);
    public ApiResponse deleteLineItem(Long id);
    public ApiResponse updateLineItem(Long id,LineItemRequest lineItemRequest);
    public LineItem getLineItem(Long id);
}
