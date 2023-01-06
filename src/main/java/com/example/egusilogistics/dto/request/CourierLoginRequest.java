package com.example.egusilogistics.dto.request;

import lombok.Data;

@Data
public class CourierLoginRequest {
    private String emailAddress;
    private String password;
}
