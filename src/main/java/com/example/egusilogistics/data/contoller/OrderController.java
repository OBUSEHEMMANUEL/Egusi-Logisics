package com.example.egusilogistics.data.contoller;

import com.example.egusilogistics.dto.request.OrderRegistrationRequest;
import com.example.egusilogistics.dto.request.OrderUpdateRequest;
import com.example.egusilogistics.dto.response.OrderRegistrationResponse;
import com.example.egusilogistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("api/logistics/order")
    public ResponseEntity<?> create(OrderRegistrationRequest request){
        try {
         return    ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
        }catch (RuntimeException exception){
            OrderRegistrationResponse response = new OrderRegistrationResponse();
            response.setMessage(exception.getMessage());
            response.setStatusCode(401);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderService.create(request));
        }

    }
    @PostMapping("api/logistics/order/placeOrder")
    public ResponseEntity<?> placeOrder(OrderRegistrationRequest request){
        return ResponseEntity.ok(orderService.placingOrder(request));
    }
    @GetMapping("api/logistics/order/getAllOrders")
    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrder());
    }
    @DeleteMapping("api/logistics/order/delete/{id}")
    public ResponseEntity<?> delete(String id){
        return ResponseEntity.ok(orderService.delete(id));
    }
}
