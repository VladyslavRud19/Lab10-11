package com.example.delivery.repository;

import com.example.delivery.model.Courier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourierRepositoryTest {

    @Autowired
    private CourierRepository courierRepository;

    @Test
    void testSaveAndFindCourier() {
        Courier courier = new Courier();
        courier.setName("Mike Courier");
        courier.setPhone("5555555555");

        Courier savedCourier = courierRepository.save(courier);
        assertNotNull(savedCourier.getCourierId());

        Courier foundCourier = courierRepository.findById(savedCourier.getCourierId()).orElse(null);
        assertNotNull(foundCourier);
        assertEquals("Mike Courier", foundCourier.getName());
        assertEquals("5555555555", foundCourier.getPhone());
    }
}