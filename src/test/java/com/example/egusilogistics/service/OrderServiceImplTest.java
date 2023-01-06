package com.example.egusilogistics.service;

import com.example.egusilogistics.data.model.Categories;
import com.example.egusilogistics.dto.request.OrderRegistrationRequest;
import com.example.egusilogistics.dto.request.OrderUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
OrderService orderService;
    OrderRegistrationRequest registrationRequest;
    OrderUpdateRequest request;
    @BeforeEach
    void setUp() {
        registrationRequest = new OrderRegistrationRequest();
        registrationRequest.setPackageName("shoe");
        registrationRequest.setPickUpAddress("iwaya");
        registrationRequest.setReceiverName("ruby");
        registrationRequest.setDeliveryAddress("caritas");
        registrationRequest.setReceiverEmailAddress("rubeluchukwu@gmail.com");
        registrationRequest.setReceiverPhoneNumber("07069556621");
        registrationRequest.setCategories(Categories.NON_FRAGILE);
        registrationRequest.setWeight(20);

        request = new OrderUpdateRequest();
        request.setOrderId("63ad916186894a61182d38c1");
        request.setPackageName("phone");
        request.setPickUpAddress("ringroad benin-city");
        request.setReceiverPhoneNumber("09099556621");
        request.setReceiverEmailAddress("rubeluchukwu@gmail.com");
        request.setReceiverPhoneNumber("07069556621");
        request.setCategories(Categories.FRAGILE);
        request.setReceiverName("ruby");
        request.setWeight(20);


    }

    @Test
    void createTest() {

      assertEquals(201, orderService.create(registrationRequest).getStatusCode());
    }

    @Test
    void getAllOrderTest() {
        assertEquals(1,orderService.getAllOrder().size());
    }

    @Test
    void updateTest() {
        assertEquals(201,orderService.update(request).getStatusCode());

    }

    @Test
    void deleteTest() {
    }

    @Test
    void deleteAllTest() {
        orderService.deleteAll();

        assertEquals(0,orderService.getAllOrder().size());
    }
}