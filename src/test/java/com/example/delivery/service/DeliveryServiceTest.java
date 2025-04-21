package com.example.delivery.service;

import com.example.delivery.model.*;
import com.example.delivery.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryServiceTest {

    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CourierRepository courierRepository;

    @Test
    void testCreateOrder() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("1234567890");

        Item item = new Item();
        item.setName("Book");
        item.setQuantity(2);
        item.setPrice(10.0);

        Order order = deliveryService.createOrder(customer, List.of(item), "123 Main St");

        assertNotNull(order);
        assertEquals("Processing", order.getStatus());
        assertEquals("123 Main St", order.getDeliveryAddress());
    }

    @Test
    void testAssignOrderToCourier() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer = customerRepository.save(customer);

        Item item = new Item();
        item.setName("Book");
        item.setQuantity(2);
        item.setPrice(10.0);

        Order order = deliveryService.createOrder(customer, List.of(item), "123 Main St");
        Courier courier = new Courier();
        courier.setName("Jane Courier");
        courier.setPhone("0987654321");
        courier = courierRepository.save(courier);

        deliveryService.assignOrderToCourier(order.getOrderId(), courier.getCourierId());

        Courier updatedCourier = courierRepository.findById(courier.getCourierId()).orElse(null);
        assertNotNull(updatedCourier);
        assertFalse(updatedCourier.getCurrentRoute().isEmpty());
    }

    @Test
    void testTrackOrder() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer = customerRepository.save(customer);

        Item item = new Item();
        item.setName("Book");
        item.setQuantity(2);
        item.setPrice(10.0);

        Order order = deliveryService.createOrder(customer, List.of(item), "123 Main St");
        String status = deliveryService.trackOrder(order.getOrderId());
        assertEquals("Processing", status);

        String nonExistentStatus = deliveryService.trackOrder(999L);
        assertEquals("Order not found", nonExistentStatus);
    }
}