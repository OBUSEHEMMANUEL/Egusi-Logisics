package com.example.egusilogistics.data.contoller;

import com.example.egusilogistics.dto.request.CustomerLoginRequest;
import com.example.egusilogistics.dto.request.CustomerRegistrationRequest;
import com.example.egusilogistics.dto.request.CustomerUpdateRequest;
import com.example.egusilogistics.dto.request.OrderRegistrationRequest;
import com.example.egusilogistics.dto.response.CustomerRegistrationResponse;
import com.example.egusilogistics.exception.CustomerException;
import com.example.egusilogistics.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("api/logistics/customer")
    public ResponseEntity<?> registerCustomer(CustomerRegistrationRequest registrationRequest){
        try{
        return  ResponseEntity.status(HttpStatus.CREATED).body(customerService.register(registrationRequest));
        }catch (CustomerException exception){
            CustomerRegistrationResponse response = new CustomerRegistrationResponse();
            response.setMessage(exception.getMessage());
            response.setStatusCode(401);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerService.register(registrationRequest));
        }
    }
    @GetMapping("api/logistics/customer/getAllCustomer")
    public  ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }
@PostMapping("api/logistics/customer/update")
    public ResponseEntity<?> Update(@RequestBody CustomerUpdateRequest updateRequest){
        return ResponseEntity.ok(customerService.update(updateRequest));
}
@PostMapping("api/logistics/customer/login")
    public ResponseEntity<?> login(@RequestBody CustomerLoginRequest loginRequest){
        return ResponseEntity.ok(customerService.login(loginRequest));
}
@PostMapping("api/logistics/customer/addOrders")
    public ResponseEntity<?> addOrders(@RequestBody OrderRegistrationRequest orderRegistrationRequest){
        return ResponseEntity.ok(customerService.addOrders(orderRegistrationRequest));
}
@DeleteMapping("api/logistics/customer/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id ){
        return ResponseEntity.ok(customerService.delete(id));
}
}
