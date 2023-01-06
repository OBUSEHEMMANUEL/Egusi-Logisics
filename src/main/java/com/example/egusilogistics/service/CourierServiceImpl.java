package com.example.egusilogistics.service;

import com.example.egusilogistics.Validator.UserValidation;
import com.example.egusilogistics.data.model.Courier;
import com.example.egusilogistics.data.repository.CourierRepository;
import com.example.egusilogistics.dto.request.CourierLoginRequest;
import com.example.egusilogistics.dto.request.CourierRegistrationRequest;
import com.example.egusilogistics.dto.request.CourierUpdateRequest;
import com.example.egusilogistics.dto.response.CourierRegistrationResponse;
import com.example.egusilogistics.exception.CourierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourierServiceImpl implements CourierService{
    @Autowired
    CourierRepository courierRepository;
    @Override
    public CourierRegistrationResponse register(CourierRegistrationRequest request) {
//        boolean isEmailExist = Boolean.parseBoolean(request.getEmailAddress());
//        if (isEmailExist) {
//            throw new CourierException("Email Exist");
//        } else {

            if (!UserValidation.isPhoneNumber(request.getPhoneNumber()))
                throw new CourierException("INVALID PHONE-NUMBER");
            if (!UserValidation.isEmailAddress(request.getEmailAddress()))
                throw new CourierException("INVALID EMAIL-ADDRESS");
            if (!UserValidation.isPassword(request.getPassword())) throw new CourierException("INVALID PASSWORD");

            buildCourier(request);

            return savedResponse();
//        }
    }

        private CourierRegistrationResponse savedResponse () {
            CourierRegistrationResponse response = new CourierRegistrationResponse();
            response.setMessage("SAVED SUCCESSFULLY");
            response.setStatusCode(201);
            return response;
        }

        private void buildCourier (CourierRegistrationRequest request){
            Courier courier = new Courier();
            if (courierRepository.findByEmailAddress(request.getEmailAddress()).isPresent())
                throw new CourierException("EMAIL EXIST");
            else {
                courier.setEmailAddress(request.getEmailAddress());
                courier.setPhoneNumber(request.getPhoneNumber());
                courier.setPassword(request.getPassword());
                courierRepository.save(courier);
            }
        }

        @Override
        public List<Courier> getAllCourier () {
            return courierRepository.findAll();
        }

    @Override
    public CourierRegistrationResponse login(CourierLoginRequest login) {
      var foundEmail = courierRepository.findByEmailAddress(login.getEmailAddress()).orElseThrow(()-> new CourierException("Wrong Email"));
      if (foundEmail.getPassword().equals(login.getPassword())){
          CourierRegistrationResponse response = new CourierRegistrationResponse();
          response.setStatusCode(201);
          response.setMessage("LOGIN SUCCESSFUL");
          return response;
      }
        CourierRegistrationResponse response = new CourierRegistrationResponse();
        response.setMessage("AUTHENTICATION FAILED");
        return response;
    }

    @Override
        public CourierRegistrationResponse update (CourierUpdateRequest updateRequest){
            var foundCourier = courierRepository.findByEmailAddress(updateRequest.getEmailAddress()).orElseThrow(() -> new CourierException("Email not found"));
            foundCourier.setAddress(updateRequest.getAddress() != null && !updateRequest.getAddress().equals("")
                    ? updateRequest.getAddress() : foundCourier.getAddress());
            foundCourier.setPassword(updateRequest.getPassword() != null && !updateRequest.getPassword().equals("")
                    ? updateRequest.getPassword() : foundCourier.getPassword());
            foundCourier.setFirstName(updateRequest.getFirstName() != null && !updateRequest.getFirstName().equals("")
                    ? updateRequest.getFirstName() : foundCourier.getFirstName());
            foundCourier.setLastName(updateRequest.getLastName() != null && !updateRequest.getLastName().equals("")
                    ? updateRequest.getLastName() : foundCourier.getLastName());
            foundCourier.setPhoneNumber(updateRequest.getPhoneNumber() != null && !updateRequest.getPhoneNumber().equals("")
                    ? updateRequest.getPhoneNumber() : foundCourier.getPhoneNumber());
            foundCourier.setEmailAddress(updateRequest.getEmailAddress() != null && !updateRequest.getEmailAddress().equals("")
                    ? updateRequest.getEmailAddress() : foundCourier.getEmailAddress());
            courierRepository.save(foundCourier);

            CourierRegistrationResponse response = new CourierRegistrationResponse();
            response.setMessage("SAVED SUCCESSFULLY");
            response.setStatusCode(201);
            return response;
        }



    @Override
    public CourierRegistrationResponse delete(String courierId) {
        courierRepository.deleteById(courierId);

        CourierRegistrationResponse response = new CourierRegistrationResponse();
        response.setMessage("DELETED SUCCESSFULLY");
        response.setStatusCode(201);
        return response;
    }

    @Override
    public void deleteAll() {
        courierRepository.deleteAll();
    }
}
