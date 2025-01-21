package com.crio.ordermanagement.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<GroceryItem> groceryItems= new ArrayList<>();
    @Nonnull
    private LocalDate orderData;
    private Double totalPrice;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(List<GroceryItem> groceryItems) {
        this.groceryItems = groceryItems;
    }

    @Nonnull
    public LocalDate getOrderData() {
        return orderData;
    }

    public void setOrderData(@Nonnull LocalDate orderData) {
        this.orderData = orderData;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }
}
