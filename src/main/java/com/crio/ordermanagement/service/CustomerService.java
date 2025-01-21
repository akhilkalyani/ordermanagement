package com.crio.ordermanagement.service;

import com.crio.ordermanagement.entity.Customer;
import com.crio.ordermanagement.exception.ResourceNotFoundException;
import com.crio.ordermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer cretateCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) throws ResourceNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    public Customer updateCustomer(Long id, Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = getCustomerById(id);
        customer.setName(customerDetails.getName());
        customer.setAddress(customerDetails.getAddress());
        customer.setPhone(customerDetails.getPhone());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
