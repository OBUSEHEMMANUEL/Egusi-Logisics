package com.example.egusilogistics.service;

import com.example.egusilogistics.data.model.Categories;
import com.example.egusilogistics.dto.request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    CustomerService customerService;
    CustomerRegistrationRequest registrationRequest;

    CustomerRegistrationRequest registrationRequest2;
    CustomerUpdateRequest updateRequest;
    CustomerLoginRequest loginRequest;
    @BeforeEach
    void setUp() {
        registrationRequest = new CustomerRegistrationRequest();
        registrationRequest.setEmailAddress("bolaji@gmail.com");
        registrationRequest.setPhoneNumber("08043985634");
        registrationRequest.setPassword("whitehouse@2021");

        registrationRequest2 = new CustomerRegistrationRequest();
        registrationRequest2.setEmailAddress("kapi@gmail.com");
        registrationRequest2.setPhoneNumber("09043985634");
        registrationRequest2.setPassword("kapi@2021");

        updateRequest = new CustomerUpdateRequest();
//        updateRequest.setCourierId();
        updateRequest.setEmailAddress("bolaji@gmail.com");
        updateRequest.setPhoneNumber("08069053454");
        updateRequest.setAddress("Semicolon Africa Sabo");
        updateRequest.setPassword("emmanuel34");
        updateRequest.setFirstName("Emmanuel");
        updateRequest.setLastName("Derek");
    }

    @Test
    void register() {
        customerService.register(registrationRequest);
        var savedCustomer = customerService.register(registrationRequest2);
        assertEquals(201,savedCustomer.getStatusCode());
    }
    @Test
    void login(){
        loginRequest = new CustomerLoginRequest();
        loginRequest.setEmailAddress("kapi@gmail.com");
        loginRequest.setPassword("kapi@2021");
        var loginCustomer = customerService.login(loginRequest);
        assertEquals(201,loginCustomer.getStatusCode());
    }

    @Test
    void getAllCustomer() {
        assertEquals(2,customerService.getAllCustomer().size());
    }

    @Test
    void update() {
        assertEquals(201,customerService.update(updateRequest).getStatusCode());
    }

    @Test
    void addOrdersTest(){
      OrderRegistrationRequest order = new OrderRegistrationRequest();
      order.setCustomerId("63ad786bfde2a62a5b5e9351");
        order.setPackageName("refrigirator");
        order.setCategories(Categories.NON_FRAGILE);
        order.setWeight(20);
        order.setPickUpAddress("iwaya");
        order.setReceiverName("pere");
        order.setDeliveryAddress("semicolon");
        order.setReceiverEmailAddress("pere@gmail.com");
        order.setReceiverPhoneNumber("080659834796");
     var savedOrder =   customerService.addOrders(order);
        assertEquals(201,savedOrder.getStatusCode());
    }

    @Test
    void delete() {
     assertEquals(201,customerService.delete("63a59c189930632f5edd6399").getStatusCode());
    }

    @Test
    void deleteAll() {
        customerService.deleteAll();
    }
}