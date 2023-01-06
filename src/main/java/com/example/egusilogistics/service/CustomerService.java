package com.example.egusilogistics.service;

import com.example.egusilogistics.data.model.Customer;
import com.example.egusilogistics.data.model.Order;
import com.example.egusilogistics.dto.request.CustomerLoginRequest;
import com.example.egusilogistics.dto.request.CustomerUpdateRequest;
import com.example.egusilogistics.dto.request.CustomerRegistrationRequest;
import com.example.egusilogistics.dto.request.OrderRegistrationRequest;
import com.example.egusilogistics.dto.response.CustomerRegistrationResponse;

import java.util.List;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest request);
    List<Customer> getAllCustomer();
    CustomerRegistrationResponse update(CustomerUpdateRequest request);

    CustomerRegistrationResponse login(CustomerLoginRequest loginRequest);
    CustomerRegistrationResponse delete(String customerId);

    void deleteAll();

    CustomerRegistrationResponse addOrders(OrderRegistrationRequest orderRequest);
}
