package com.example.egusilogistics.data.model;

import lombok.Data;
import org.apache.catalina.LifecycleState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document
public class Customer extends User{
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String address;
    @DBRef
    List<Order> order = new ArrayList<>();
}
