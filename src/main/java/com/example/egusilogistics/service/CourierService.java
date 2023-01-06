package com.example.egusilogistics.service;

import com.example.egusilogistics.data.model.Courier;
import com.example.egusilogistics.dto.request.CourierLoginRequest;
import com.example.egusilogistics.dto.request.CourierRegistrationRequest;
import com.example.egusilogistics.dto.request.CourierUpdateRequest;
import com.example.egusilogistics.dto.response.CourierRegistrationResponse;

import java.util.List;

public interface CourierService {
    CourierRegistrationResponse register(CourierRegistrationRequest request);
    List<Courier> getAllCourier();
    CourierRegistrationResponse login(CourierLoginRequest login);
    CourierRegistrationResponse update(CourierUpdateRequest request);
    CourierRegistrationResponse delete(String courierId);
    void deleteAll();
}
