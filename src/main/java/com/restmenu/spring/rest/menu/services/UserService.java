package com.restmenu.spring.rest.menu.services;

import com.restmenu.spring.rest.menu.config.AppConfig;
import com.restmenu.spring.rest.menu.models.CustomUser;
import com.restmenu.spring.rest.menu.models.UserRegisterType;
import com.restmenu.spring.rest.menu.models.UserRole;
import com.restmenu.spring.rest.menu.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUsers(List<Long> ids) {
        ids.forEach(id -> {
            Optional<CustomUser> user = userRepository.findById(id);
            user.ifPresent(u -> {
                if ( ! AppConfig.ADMIN_LOGIN.equals(u.getEmail())) {
                    userRepository.deleteById(u.getId());
                }
            });
        });
    }

    @Transactional
    public boolean addUser(String passHash,
                           UserRole role,
                           UserRegisterType registerType,
                           String email,
                           String phone,
                           String address) {
        if (userRepository.existsByEmail(email))
            return false;

        CustomUser user = new CustomUser(passHash, role, registerType, email, phone, address);
        userRepository.save(user);

        return true;
    }

    @Transactional
    public void updateUser(String email, String phone, String address) {
        CustomUser user = userRepository.findByEmail(email);
        if (user == null)
            return;

        user.setAddress(address);
        user.setPhone(phone);

        userRepository.save(user);
    }

    @Transactional
    public void addUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Transactional
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Transactional(readOnly = true)
    public Optional<CustomUser> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
    }

