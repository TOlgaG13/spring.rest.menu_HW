package com.restmenu.spring.rest.menu.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendMessage(String to, String code);
}

