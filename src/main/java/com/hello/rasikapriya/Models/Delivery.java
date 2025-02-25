package com.hello.rasikapriya.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String deliveryStatus;
    private LocalDateTime deliveryDate;
    private String address;

    @ElementCollection
    private List<Long> orderIds; // Storing order IDs instead of mapping

    // Constructors
    public Delivery() {}

    public Delivery(Long userId, String deliveryStatus, LocalDateTime deliveryDate, String address, List<Long> orderIds) {
        this.userId = userId;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDate = deliveryDate;
        this.address = address;
        this.orderIds = orderIds;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDateTime deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<Long> getOrderIds() { return orderIds; }
    public void setOrderIds(List<Long> orderIds) { this.orderIds = orderIds; }
}
