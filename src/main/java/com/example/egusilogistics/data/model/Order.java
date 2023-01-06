package com.example.egusilogistics.data.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Order {
    @Id
    private String orderId;
    private String customerId;
    private String packageName;
    private String pickUpAddress;
    private String deliveryAddress;
    private String receiverPhoneNumber;
    private String receiverName;
    private String receiverEmailAddress;
    private int weight;
    private Categories categories;
    private double price = 1000.00;
    private double amountToPay;
}
