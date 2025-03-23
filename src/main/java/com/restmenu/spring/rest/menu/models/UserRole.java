package com.restmenu.spring.rest.menu.models;

public enum UserRole {
    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}

