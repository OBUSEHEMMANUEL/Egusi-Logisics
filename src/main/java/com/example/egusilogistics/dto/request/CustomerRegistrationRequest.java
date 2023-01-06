package com.example.egusilogistics.dto.request;

import com.example.egusilogistics.data.model.Order;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
@Data
public class CustomerRegistrationRequest {
    private String emailAddress;
    private String password;
    private String PhoneNumber;
    private String customerId;


}
