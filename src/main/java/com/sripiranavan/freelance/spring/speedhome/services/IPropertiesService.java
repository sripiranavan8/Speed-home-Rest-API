package com.sripiranavan.freelance.spring.speedhome.services;

import com.sripiranavan.freelance.spring.speedhome.entities.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPropertiesService {
    public Page<Property> findAll(Pageable pageable);
    public Page<Property> findAllBySearch(String search, Pageable pageable);
    public Page<Property> findAllByPropertyStatus(boolean status,Pageable pageable);
    public void saveProperty(Property property);
    public Optional<Property> findByPropertyName(String name);
    public Optional<Property> findById(Long id);
}
