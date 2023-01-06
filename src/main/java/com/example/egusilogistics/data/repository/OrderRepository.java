package com.example.egusilogistics.data.repository;

import com.example.egusilogistics.data.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
