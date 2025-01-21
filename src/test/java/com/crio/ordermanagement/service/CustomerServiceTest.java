package com.crio.ordermanagement.service;

import com.crio.ordermanagement.entity.Customer;
import com.crio.ordermanagement.exception.ResourceNotFoundException;
import com.crio.ordermanagement.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setPhone("123-456-7890");
    }

    @Test
    public void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer createdCustomer = customerService.cretateCustomer(customer);
        assertNotNull(createdCustomer);
        assertEquals("John Doe", createdCustomer.getName());
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> customerList = customerService.getAllCustomers();
        assertFalse(customerList.isEmpty());
        assertEquals(1, customerList.size());
    }

    @Test
    public void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            Customer foundCustomer = customerService.getCustomerById(1L);
            assertNotNull(foundCustomer);
            assertEquals("John Doe", foundCustomer.getName());
        });
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerService.getCustomerById(1L);
        });

        assertEquals("Customer not found with id: 1", exception.getMessage());
    }

    @Test
    public void testUpdateCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            customer.setName("Updated Name");
            Customer updatedCustomer = customerService.updateCustomer(1L, customer);
            assertNotNull(updatedCustomer);
            assertEquals("Updated Name", updatedCustomer.getName());
        });
    }

    @Test
    public void testDeleteCustomer() {
        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
