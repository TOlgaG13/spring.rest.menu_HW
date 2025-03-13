package com.restmenu.spring.rest.menu.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="menu1")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private double weight;
    private boolean discount;

    @OneToMany(mappedBy = "dishes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDishes> orderDishesList;

    public Dishes() {}

    public Dishes(String name, double price, double weight, boolean discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public List<OrderDishes> getOrderDishesList() {
        return orderDishesList;
    }

    public void setOrderDishesList(List<OrderDishes> orderDishesList) {
        this.orderDishesList = orderDishesList;
    }

}

