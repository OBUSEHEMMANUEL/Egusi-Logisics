package com.example.egusilogistics.service;

import com.example.egusilogistics.dto.request.CourierLoginRequest;
import com.example.egusilogistics.dto.request.CourierRegistrationRequest;
import com.example.egusilogistics.dto.request.CourierUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourierServiceImplTest {
    @Autowired
    CourierService courierService;
    CourierRegistrationRequest registrationRequest;
    CourierRegistrationRequest registrationRequest2;

    CourierUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        registrationRequest = new CourierRegistrationRequest();
        registrationRequest.setEmailAddress("bolaji@gmail.com");
        registrationRequest.setPhoneNumber("08069053454");
        registrationRequest.setPassword("perry@28");

        registrationRequest2 = new CourierRegistrationRequest();
        registrationRequest2.setEmailAddress("derekManuel@208.com");
        registrationRequest2.setPhoneNumber("07056626566");
        registrationRequest2.setPassword("derek@28");

        updateRequest = new CourierUpdateRequest();
//        updateRequest.setCourierId();
        updateRequest.setEmailAddress("derekManuel@208.com");
        updateRequest.setPhoneNumber("08069053454");
        updateRequest.setAddress("Semicolon Africa Sabo");
        updateRequest.setPassword("emmanuel34");
        updateRequest.setFirstName("Emmanuel");
        updateRequest.setLastName("Derek");

    }

    @Test
    void registerTest() {
        var registerCourier =     courierService.register(registrationRequest);
//        var registerCourier = courierService.register(registrationRequest2);
        assertEquals(201, registerCourier.getStatusCode());
    }
    @Test
    void loginTest(){
        CourierLoginRequest login = new CourierLoginRequest();
        login.setEmailAddress("bolaji@gmail.com");
        login.setPassword("perry@28");
        assertEquals(201,courierService.login(login).getStatusCode());
    }

    @Test
    void getAllCourierTest() {
        assertEquals(2, courierService.getAllCourier().size());
    }

    @Test
    void updateTest() {
        var updateCourier = courierService.update(updateRequest);
        assertEquals(201, updateCourier.getStatusCode());

    }

    @Test
    void deleteTest() {
//        assertEquals(3,courierService.getAllCourier().size());
        courierService.delete("63ad4bfaa776061043d71055");
      assertEquals(1,  courierService.getAllCourier().size());
    }

    @Test
    void deleteAllTest() {
        courierService.deleteAll();
        assertEquals(0,courierService.getAllCourier().size());
    }


}