package com.example.delivery.repository;

import com.example.delivery.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveAndFindOrder() {
        Order order = new Order();
        order.setStatus("Processing");
        order.setDeliveryAddress("123 Main St");

        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getOrderId());

        Order foundOrder = orderRepository.findById(savedOrder.getOrderId()).orElse(null);
        assertNotNull(foundOrder);
        assertEquals("Processing", foundOrder.getStatus());
    }
}