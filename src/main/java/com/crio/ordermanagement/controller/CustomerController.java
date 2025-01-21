package com.crio.ordermanagement.controller;

import com.crio.ordermanagement.entity.Customer;
import com.crio.ordermanagement.exception.ResourceNotFoundException;
import com.crio.ordermanagement.service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.cretateCustomer(customer));
    }

    @GetMapping("/getCustomers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(id, customer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
        }
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
