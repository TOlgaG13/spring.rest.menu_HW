package com.restmenu.spring.rest.menu.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserRegisterType type;

    private String email;
    private String phone;
    private String address;

    public CustomUser(String password, UserRole role, UserRegisterType type, String email, String phone, String address) {
        this.password = password;
        this.role = role;
        this.type = type;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}


