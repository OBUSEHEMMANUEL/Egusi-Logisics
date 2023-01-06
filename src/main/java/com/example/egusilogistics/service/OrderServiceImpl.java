package com.example.egusilogistics.service;

import com.example.egusilogistics.data.model.Order;
import com.example.egusilogistics.data.repository.OrderRepository;
import com.example.egusilogistics.dto.request.OrderRegistrationRequest;
import com.example.egusilogistics.dto.request.OrderUpdateRequest;
import com.example.egusilogistics.dto.response.OrderRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.egusilogistics.data.model.Categories.FRAGILE;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderRegistrationResponse create(OrderRegistrationRequest request) {
        Order order = placingOrder(request);
        orderRepository.save(order);

        OrderRegistrationResponse response = new OrderRegistrationResponse();
        response.setMessage("ORDER ADDED");
        response.setStatusCode(201);
        return response;
    }
@Override
public Order placingOrder(OrderRegistrationRequest request) {
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setPackageName(request.getPackageName());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setPickUpAddress(request.getPickUpAddress());
        order.setReceiverName(request.getReceiverName());
        order.setReceiverEmailAddress(request.getReceiverEmailAddress());
        order.setReceiverPhoneNumber(request.getReceiverPhoneNumber());
        paymentVoucher(request,order);
        return order;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public OrderRegistrationResponse update(OrderUpdateRequest request) {
      var foundId =  orderRepository.findById (request.getOrderId()).orElseThrow(()-> new RuntimeException("ID NOT FOUND"));
        foundId.setPackageName(request.getPackageName() != null && !request.getPackageName().equals("")
                ?request.getPackageName() : foundId.getPackageName());
        foundId.setDeliveryAddress(request.getDeliveryAddress() != null && !request.getDeliveryAddress().equals("")
                ?request.getDeliveryAddress(): foundId.getDeliveryAddress());
        foundId.setPickUpAddress(request.getPickUpAddress() != null && !request.getPickUpAddress().equals("")
                ? request.getPickUpAddress() : foundId.getPickUpAddress());
        foundId.setReceiverName(request.getReceiverName() != null && !request.getReceiverName().equals("")
                ? request.getReceiverName() : foundId.getReceiverName());
        foundId.setReceiverEmailAddress(request.getReceiverEmailAddress() != null && !request.getReceiverEmailAddress().equals("")
                ? request.getReceiverEmailAddress() : foundId.getReceiverEmailAddress());
        foundId.setReceiverPhoneNumber(request.getReceiverPhoneNumber() != null && !request.getReceiverPhoneNumber().equals("")
                ?request.getReceiverPhoneNumber() : foundId.getReceiverPhoneNumber());
        updatePaymentVoucher(request,foundId);
        orderRepository.save(foundId);
        OrderRegistrationResponse response = new OrderRegistrationResponse();
        response.setMessage("ORDER UPDATED");
        response.setStatusCode(201);
        return response;
    }
    private void updatePaymentVoucher(OrderUpdateRequest updateRequest,Order foundOrder){
        foundOrder.setWeight(updateRequest.getWeight() !=0.00 ? updateRequest.getWeight() : foundOrder.getWeight());
        foundOrder.setCategories(updateRequest.getCategories());
        var placingOrder = foundOrder.getCategories().toString();
        var fragile = foundOrder.getWeight() * foundOrder.getPrice() * 0.20;
        if (placingOrder.equalsIgnoreCase(String.valueOf(FRAGILE)))
            foundOrder.setAmountToPay(foundOrder.getWeight() * foundOrder.getPrice() + fragile);
        else
            foundOrder.setAmountToPay(foundOrder.getWeight() * foundOrder.getPrice());

    }


    private void paymentVoucher(OrderRegistrationRequest request, Order order){
        order.setWeight(request.getWeight());
        order.setCategories(request.getCategories());
        var placingOrder = order.getCategories().toString();
        var fragile = order.getWeight() * order.getPrice() * 0.20;
        if (placingOrder.equalsIgnoreCase(String.valueOf(FRAGILE)))
            order.setAmountToPay(order.getWeight() * order.getPrice() + fragile);
        else
            order.setAmountToPay(order.getWeight() * order.getPrice());
    }


    @Override
    public OrderRegistrationResponse delete(String customerId) {
      orderRepository.deleteById(customerId);
        OrderRegistrationResponse response = new OrderRegistrationResponse();
        response.setMessage("ORDER DELETED SUCCESSFULLY");
        response.setStatusCode(201);
        return response;
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();

    }
}
