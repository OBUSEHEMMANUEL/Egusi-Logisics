package com.example.egusilogistics.data.contoller;

import com.example.egusilogistics.dto.request.CourierLoginRequest;
import com.example.egusilogistics.dto.request.CourierRegistrationRequest;
import com.example.egusilogistics.dto.request.CourierUpdateRequest;
import com.example.egusilogistics.dto.response.CourierRegistrationResponse;
import com.example.egusilogistics.exception.CourierException;
import com.example.egusilogistics.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourierController {
    @Autowired
    CourierService courierService;
    @PostMapping("api/logistics/courier")
    public ResponseEntity<?> registerCourier(@RequestBody CourierRegistrationRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courierService.register(request));
        }
        catch (CourierException exception) {
            CourierRegistrationResponse response = new CourierRegistrationResponse();
            response.setMessage(exception.getMessage());
            response.setStatusCode(401);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(courierService.register(request));
        }
    }
    @PatchMapping("api/logistics/courier/update")
    public ResponseEntity<?> updateCourier(@RequestBody CourierUpdateRequest request){
        return ResponseEntity.ok(courierService.update(request));
    }
    @GetMapping("api/logistics/courier/allCourier")
    public ResponseEntity<?> getAllCourier(){
        return ResponseEntity.ok(courierService.getAllCourier());
    }
    @PostMapping("api/logistics/courier/login")
    public ResponseEntity<?> loginCourier(@RequestBody CourierLoginRequest loginRequest){
        return ResponseEntity.ok(courierService.login(loginRequest));
    }
    @DeleteMapping("api/logistics/courier/delete/{courierId}")
    public ResponseEntity<?> deleteCourier(@PathVariable String courierId){
        return ResponseEntity.ok(courierService.delete(courierId));
    }
}
