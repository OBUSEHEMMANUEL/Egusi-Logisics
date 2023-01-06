package com.example.egusilogistics.dto.request;

import com.example.egusilogistics.data.model.Categories;
import lombok.Data;

@Data
public class OrderUpdateRequest {
    private String orderId;
    private String packageName;
    private String pickUpAddress;
    private String deliveryAddress;
    private String receiverPhoneNumber;
    private String receiverName;
    private String receiverEmailAddress;
    private int weight;
    private Categories categories;
//    private  double price = 1000.0;
    private double amountToPay;
}
