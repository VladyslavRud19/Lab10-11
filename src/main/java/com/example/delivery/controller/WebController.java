package com.example.delivery.controller;

import com.example.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/orders/status/{orderId}")
    public String showOrderStatus(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", deliveryService.getOrder(orderId)
                .orElse(null));
        return "order-status";
    }
}