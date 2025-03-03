package com.hello.rasikapriya.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchases")
public class PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;

    private String customerName;
    private LocalDate purchaseDate;
    private double totalAmount;
    private String status;

    public int getPurchaseId() { return purchaseId; }
    public void setPurchaseId(int purchaseId) { this.purchaseId = purchaseId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
