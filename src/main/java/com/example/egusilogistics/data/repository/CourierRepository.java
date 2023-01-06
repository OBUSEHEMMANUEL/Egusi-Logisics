package com.example.egusilogistics.data.repository;

import com.example.egusilogistics.data.model.Courier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourierRepository extends MongoRepository<Courier, String> {
    Optional<Courier> findByEmailAddress(String emailAddress);

}
