package com.crio.ordermanagement.controller;

import com.crio.ordermanagement.entity.Order;
import com.crio.ordermanagement.exception.ResourceNotFoundException;
import com.crio.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(order));
    }

    @GetMapping("/getOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> getORderById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
