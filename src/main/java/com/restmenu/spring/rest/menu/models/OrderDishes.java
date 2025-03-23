package com.restmenu.spring.rest.menu.models;


import jakarta.persistence.*;


@Entity
@Table(name="order_dishes")
public class OrderDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dishes dishes;

    public OrderDishes() {}

    public OrderDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

}




