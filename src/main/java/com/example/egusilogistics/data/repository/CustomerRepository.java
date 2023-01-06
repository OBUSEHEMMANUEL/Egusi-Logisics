package com.example.egusilogistics.data.repository;

import com.example.egusilogistics.data.model.Customer;
import com.example.egusilogistics.dto.response.CustomerRegistrationResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
 Optional<Customer> findByEmailAddress(String emailAddress);
}
