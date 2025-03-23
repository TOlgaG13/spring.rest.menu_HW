package com.restmenu.spring.rest.menu.repositories;

import com.restmenu.spring.rest.menu.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByEmail(String email);
    boolean existsByEmail(String email);

}

