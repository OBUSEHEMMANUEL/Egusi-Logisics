package com.example.egusilogistics.dto.request;

import com.example.egusilogistics.data.model.Categories;
import lombok.Data;

@Data
public class OrderRegistrationRequest {
    private String customerId;
    private String packageName;
    private String pickUpAddress;
    private String deliveryAddress;
    private String receiverPhoneNumber;
    private String receiverName;
    private String receiverEmailAddress;
    private int weight;
    private Categories categories;

}
