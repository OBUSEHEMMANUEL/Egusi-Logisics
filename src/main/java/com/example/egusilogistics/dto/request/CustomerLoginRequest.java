package com.example.egusilogistics.dto.request;

import lombok.Data;

@Data
public class CustomerLoginRequest {
    public String emailAddress;
    public String password;
}
