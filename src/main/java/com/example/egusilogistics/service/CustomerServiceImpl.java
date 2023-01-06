package com.example.egusilogistics.service;

import com.example.egusilogistics.Validator.UserValidation;
import com.example.egusilogistics.data.model.Customer;
import com.example.egusilogistics.data.model.Order;
import com.example.egusilogistics.data.repository.CustomerRepository;
import com.example.egusilogistics.dto.request.CustomerLoginRequest;
import com.example.egusilogistics.dto.request.CustomerRegistrationRequest;
import com.example.egusilogistics.dto.request.CustomerUpdateRequest;
import com.example.egusilogistics.dto.request.OrderRegistrationRequest;
import com.example.egusilogistics.dto.response.CustomerRegistrationResponse;
import com.example.egusilogistics.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderService orderService;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest request) {
        if(!UserValidation.isPhoneNumber(request.getPhoneNumber())) throw new CustomerException("INVALID PHONE-NUMBER");
        if(!UserValidation.isEmailAddress(request.getEmailAddress())) throw new CustomerException("INVALID EMAIL-ADDRESS");
        if (!UserValidation.isPassword(request.getPassword())) throw new CustomerException("INVALID PASSWORD");
        buildCustomer(request);

        return savedResponse();
    }
    private void buildCustomer(CustomerRegistrationRequest request) {
        Customer customer = new Customer();
        customer.setEmailAddress(request.getEmailAddress());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setPassword(request.getPassword());
        customerRepository.save(customer);
    }
    private CustomerRegistrationResponse savedResponse() {
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("SAVED SUCCESSFULLY");
        response.setStatusCode(201);
        return response;
    }
    @Override
    public List<Customer> getAllCustomer() {
     return customerRepository.findAll();
    }

    @Override
    public CustomerRegistrationResponse update(CustomerUpdateRequest updateRequest) {
      var foundCustomer =  customerRepository.findByEmailAddress(updateRequest.getEmailAddress()).orElseThrow(()-> new CustomerException("Email not found"));
        confirmationRequest(updateRequest, foundCustomer);
        customerRepository.save(foundCustomer);
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("SAVED SUCCESSFULLY");
        response.setStatusCode(201);
        return response;

    }

    @Override
    public CustomerRegistrationResponse login(CustomerLoginRequest loginRequest) {
        var foundEmail = customerRepository.findByEmailAddress(loginRequest.getEmailAddress()).orElseThrow(()-> new CustomerException("Login Email not Registered"));
       if (foundEmail.getPassword().equals(loginRequest.getPassword())){
           CustomerRegistrationResponse response = new CustomerRegistrationResponse();
           response.setMessage("LOGIN SUCCESSFUL");
           response.setStatusCode(201);
           return response;
       }
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("AUTHENTICATION FAILED");
        return response;
    }

    private void confirmationRequest(CustomerUpdateRequest updateRequest, Customer foundCustomer) {
        foundCustomer.setAddress(updateRequest.getAddress() != null && !updateRequest.getAddress().equals("")
                ? updateRequest.getAddress() : foundCustomer.getAddress());
        foundCustomer.setLastName(updateRequest.getLastName() != null&& !updateRequest.getLastName().equals("")
                ? updateRequest.getLastName() : foundCustomer.getLastName());
        foundCustomer.setPhoneNumber(updateRequest.getPhoneNumber() != null && !updateRequest.getPhoneNumber().equals("")
                ? updateRequest.getPhoneNumber() : foundCustomer.getPhoneNumber());
        foundCustomer.setEmailAddress(updateRequest.getEmailAddress()!= null && !updateRequest.getEmailAddress().equals("")
                ? updateRequest.getEmailAddress() : foundCustomer.getEmailAddress());
        foundCustomer.setPassword(updateRequest.getPassword() != null && !updateRequest.getPassword().equals("")
                ? updateRequest.getPassword() : foundCustomer.getPassword());
        foundCustomer.setFirstName(updateRequest.getFirstName() != null && !updateRequest.getFirstName().equals("")
                ? updateRequest.getFirstName() : foundCustomer.getFirstName());
    }

    @Override
    public CustomerRegistrationResponse delete(String customerId) {
        customerRepository.deleteById(customerId);
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("SAVED SUCCESSFULLY");
        response.setStatusCode(201);
        return response;
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    @Override
    public CustomerRegistrationResponse addOrders(OrderRegistrationRequest orderRequest){
        var savedCustomer = customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(()->new RuntimeException("id not found"));
    var order =    orderService.placingOrder(orderRequest);
        savedCustomer.getOrder().add(order);
        orderService.create(orderRequest);
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("ORDER ADDED SUCCESSFULLY");
        response.setStatusCode(201);
        return response;
    }

}
