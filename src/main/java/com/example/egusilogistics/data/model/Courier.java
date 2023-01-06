package com.example.egusilogistics.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Courier extends User{
    @Id
    private String courierId;
    private String firstName;
    private String lastName;
    private String address;
}
