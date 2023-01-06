package com.example.egusilogistics.service;

import com.example.egusilogistics.data.model.Order;
import com.example.egusilogistics.dto.request.OrderRegistrationRequest;
import com.example.egusilogistics.dto.request.OrderUpdateRequest;
import com.example.egusilogistics.dto.response.CustomerRegistrationResponse;
import com.example.egusilogistics.dto.response.OrderRegistrationResponse;

import java.util.List;

public interface OrderService {
    OrderRegistrationResponse create(OrderRegistrationRequest request);

    Order placingOrder(OrderRegistrationRequest request);

    List<Order> getAllOrder();
    OrderRegistrationResponse update(OrderUpdateRequest request);
    OrderRegistrationResponse delete(String customerId);
    void deleteAll();
}
