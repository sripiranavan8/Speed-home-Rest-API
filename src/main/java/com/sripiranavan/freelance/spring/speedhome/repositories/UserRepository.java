package com.sripiranavan.freelance.spring.speedhome.repositories;

import com.sripiranavan.freelance.spring.speedhome.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserName(String username);
}
