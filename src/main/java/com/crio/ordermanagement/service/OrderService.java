package com.crio.ordermanagement.service;

import com.crio.ordermanagement.entity.Order;
import com.crio.ordermanagement.exception.ResourceNotFoundException;
import com.crio.ordermanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) throws ResourceNotFoundException{
        return orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found"));
    }
}
