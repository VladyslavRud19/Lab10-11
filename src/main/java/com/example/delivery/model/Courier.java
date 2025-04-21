package com.example.delivery.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courierId;
    private String name;
    private String phone;

    @OneToMany
    private List<Order> currentRoute = new ArrayList<>();

    public Long getCourierId() { return courierId; }
    public void setCourierId(Long courierId) { this.courierId = courierId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public List<Order> getCurrentRoute() { return currentRoute; }
    public void setCurrentRoute(List<Order> currentRoute) { this.currentRoute = currentRoute; }
}