package com.sripiranavan.freelance.spring.speedhome.repositories;

import com.sripiranavan.freelance.spring.speedhome.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByClientId(String integer);
}
