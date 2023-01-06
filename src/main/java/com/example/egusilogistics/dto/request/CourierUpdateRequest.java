package com.example.egusilogistics.dto.request;

import lombok.Data;

@Data
public class CourierUpdateRequest {
    private String courierId;
    private String firstName;
    private String lastName;
    private String address;
    private String emailAddress;
    private String password;
    private String PhoneNumber;
}
