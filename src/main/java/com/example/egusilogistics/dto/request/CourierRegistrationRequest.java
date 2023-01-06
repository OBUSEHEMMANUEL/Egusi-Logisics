package com.example.egusilogistics.dto.request;

import lombok.Data;

@Data
public class CourierRegistrationRequest {

    private String emailAddress;
    private String password;
    private String phoneNumber;
}
