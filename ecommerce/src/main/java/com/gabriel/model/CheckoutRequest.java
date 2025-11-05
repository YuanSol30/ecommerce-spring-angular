package com.gabriel.model;

import lombok.Data;
import java.util.List;

@Data
public class CheckoutRequest {
    private CustomerInfo customer;
    private PaymentInfo payment;
    private List<CartItem> items;
    private double totalAmount;
    
    @Data
    public static class CustomerInfo {
        private int id;
        private String name;
        private String email;
        private String address;
    }
    
    @Data
    public static class PaymentInfo {
        private String cardNumber;
        private String expiryMonth;
        private String expiryYear;
        private String cvv;
        private String cardHolderName;
        private String paymentMethod; // "credit_card", "debit_card", "paypal"
    }
    
    @Data
    public static class CartItem {
        private int productId;
        private String productName;
        private String productDescription;
        private String categoryName;
        private String imageFile;
        private double price;
        private int quantity;
        private String unitOfMeasure;
    }
}