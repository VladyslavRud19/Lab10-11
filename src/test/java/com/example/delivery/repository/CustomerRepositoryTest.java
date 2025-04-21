package com.example.delivery.repository;

import com.example.delivery.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveAndFindCustomer() {
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("jane@example.com");
        customer.setPhone("9876543210");

        Customer savedCustomer = customerRepository.save(customer);
        assertNotNull(savedCustomer.getCustomerId());

        Customer foundCustomer = customerRepository.findById(savedCustomer.getCustomerId()).orElse(null);
        assertNotNull(foundCustomer);
        assertEquals("Jane Doe", foundCustomer.getName());
        assertEquals("jane@example.com", foundCustomer.getEmail());
    }
}