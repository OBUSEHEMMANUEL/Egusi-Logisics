package com.example.egusilogistics.dto.request;

import lombok.Data;

@Data
public class CustomerUpdateRequest {
    private String emailAddress;
    private String password;
    private String PhoneNumber;
    private String customerId;
    private String firstName;
    private String lastName;
    private String address;
}
