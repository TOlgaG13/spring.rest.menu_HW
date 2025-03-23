package com.restmenu.spring.rest.menu.models;

public enum UserRegisterType {
    FORM, GOOGLE;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}

