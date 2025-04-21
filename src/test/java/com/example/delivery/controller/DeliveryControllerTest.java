package com.example.delivery.controller;

import com.example.delivery.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testTrackOrder() throws Exception {
        mockMvc.perform(get("/api/delivery/orders/999/track")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Order not found"));
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        mockMvc.perform(put("/api/delivery/orders/999/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"Delivered\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Status updated successfully"));
    }
}