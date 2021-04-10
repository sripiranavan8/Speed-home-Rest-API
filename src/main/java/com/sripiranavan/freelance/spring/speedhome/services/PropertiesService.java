package com.sripiranavan.freelance.spring.speedhome.services;

import com.sripiranavan.freelance.spring.speedhome.entities.Property;
import com.sripiranavan.freelance.spring.speedhome.repositories.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertiesService implements IPropertiesService {
    private PropertyRepository repository;
    public PropertiesService(PropertyRepository repository){
        this.repository = repository;
    }

    @Override
    public Page<Property> findAll(Pageable pageable) {
        return  repository.findAll(pageable);
    }

    @Override
    public void saveProperty(Property property) {
        repository.saveAndFlush(property);
    }

    @Override
    public Optional<Property> findByPropertyName(String name) {
        return repository.findByPropertyName(name);
    }

    @Override
    public Optional<Property> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Property> findAllBySearch(String search, Pageable pageable) {
        return repository.findAllBySearchField(search,pageable);
    }

    @Override
    public Page<Property> findAllByPropertyStatus(boolean status, Pageable pageable) {
        return repository.findAllByPropertyStatus(status,pageable);
    }
}
