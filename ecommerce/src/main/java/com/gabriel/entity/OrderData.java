package com.gabriel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_data")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int customerId;
    private String customerName;
    private String customerEmail;
    private double totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private String paymentMethod;
    private String paymentStatus;
    private String shippingAddress;
    
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemData> orderItems;
}
