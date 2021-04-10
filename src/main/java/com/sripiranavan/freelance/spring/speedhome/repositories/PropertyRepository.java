package com.sripiranavan.freelance.spring.speedhome.repositories;

import com.sripiranavan.freelance.spring.speedhome.entities.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property,Long>{
    public Optional<Property> findByPropertyName(String name);

    public Page<Property> findAllByPropertyStatus(boolean status, Pageable pageable);

    @Query(value = "select * from properties p where lower(p.property_name) like lower(concat('%', :search, '%')) " +
                    "or lower(p.property_type) like lower(concat('%', :search, '%'))",
            countQuery = "select count(*) FROM properties p where lower(p.property_name) like lower(concat('%', :search, '%')) " +
                    "or lower(p.property_type) like lower(concat('%', :search, '%'))",
            nativeQuery = true)
    Page<Property> findAllBySearchField(String search,Pageable pageable);
}
