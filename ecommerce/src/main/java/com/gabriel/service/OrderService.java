package com.gabriel.service;

import com.gabriel.entity.OrderData;
import com.gabriel.model.CheckoutRequest;
import com.gabriel.model.Order;
import java.util.List;

public interface OrderService {
    // Legacy methods
    Order create(Order order);
    Order invoice(Order order);
    Order pay(Order order);
    Order pick(Order order);
    Order ship(Order order);
    Order complete(Order order);
    Order cancel(Order order);
    Order suspend(Order order);
    Order update(Order order);
    
    // New checkout methods
    OrderData processCheckout(CheckoutRequest checkoutRequest);
    List<OrderData> getOrdersByCustomerId(int customerId);
    OrderData getOrderById(int orderId);
    List<OrderData> getAllOrders();
}
