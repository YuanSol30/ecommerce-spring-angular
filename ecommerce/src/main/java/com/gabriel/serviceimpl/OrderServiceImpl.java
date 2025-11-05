package com.gabriel.serviceimpl;

import com.gabriel.entity.OrderData;
import com.gabriel.entity.OrderItemData;
import com.gabriel.enums.OrderItemStatus;
import com.gabriel.model.CheckoutRequest;
import com.gabriel.model.Order;
import com.gabriel.repository.OrderDataRepository;
import com.gabriel.repository.OrderItemDataRepository;
import com.gabriel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderDataRepository orderDataRepository;
    
    @Autowired
    private OrderItemDataRepository orderItemDataRepository;
    
    @Override
    public OrderData processCheckout(CheckoutRequest checkoutRequest) {
        // Create new order
        OrderData order = new OrderData();
        order.setCustomerId(checkoutRequest.getCustomer().getId());
        order.setCustomerName(checkoutRequest.getCustomer().getName());
        order.setCustomerEmail(checkoutRequest.getCustomer().getEmail());
        order.setTotalAmount(checkoutRequest.getTotalAmount());
        order.setStatus("CONFIRMED");
        order.setOrderDate(LocalDateTime.now());
        // Set estimated delivery date to 5-7 business days from now
        order.setEstimatedDeliveryDate(LocalDateTime.now().plusDays(7));
        order.setPaymentMethod(checkoutRequest.getPayment().getPaymentMethod());
        order.setPaymentStatus("PAID");
        order.setShippingAddress(checkoutRequest.getCustomer().getAddress());
        
        // Save order first to get the ID
        OrderData savedOrder = orderDataRepository.save(order);
        
        // Create order items
        List<OrderItemData> orderItems = new ArrayList<>();
        for (CheckoutRequest.CartItem item : checkoutRequest.getItems()) {
            OrderItemData orderItem = new OrderItemData();
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setCustomerId(checkoutRequest.getCustomer().getId());
            orderItem.setCustomerName(checkoutRequest.getCustomer().getName());
            orderItem.setProductId(item.getProductId());
            orderItem.setProductName(item.getProductName());
            orderItem.setProductDescription(item.getProductDescription());
            orderItem.setProductCategoryName(item.getCategoryName());
            orderItem.setProductImageFile(item.getImageFile());
            orderItem.setProductUnitOfMeasure(item.getUnitOfMeasure());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());
            orderItem.setStatus(OrderItemStatus.ORDERED);
            orderItem.setCreated(new Date());
            orderItem.setLastUpdated(new Date());
            
            orderItems.add(orderItem);
        }
        
        // Save all order items
        orderItemDataRepository.saveAll(orderItems);
        
        // Set order items to the order (for response)
        savedOrder.setOrderItems(orderItems);
        
        return savedOrder;
    }
    
    @Override
    public List<OrderData> getOrdersByCustomerId(int customerId) {
        return orderDataRepository.findByCustomerId(customerId);
    }
    
    @Override
    public OrderData getOrderById(int orderId) {
        return orderDataRepository.findById(orderId).orElse(null);
    }
    
    @Override
    public List<OrderData> getAllOrders() {
        return orderDataRepository.findAll();
    }
    
    // Legacy methods - keeping for compatibility
    @Override
    public Order create(Order order) {
        return order;
    }
    
    @Override
    public Order invoice(Order order) {
        return order;
    }
    
    @Override
    public Order pay(Order order) {
        return order;
    }
    
    @Override
    public Order pick(Order order) {
        return order;
    }
    
    @Override
    public Order ship(Order order) {
        return order;
    }
    
    @Override
    public Order complete(Order order) {
        return order;
    }
    
    @Override
    public Order cancel(Order order) {
        return order;
    }
    
    @Override
    public Order suspend(Order order) {
        return order;
    }
    
    @Override
    public Order update(Order order) {
        return order;
    }
}