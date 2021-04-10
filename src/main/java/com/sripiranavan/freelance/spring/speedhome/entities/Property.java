package com.sripiranavan.freelance.spring.speedhome.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String propertyName;
    @NotBlank
    private String propertyType;
    @NotNull
    private boolean propertyStatus;
    @NotBlank
    private String propertyDescription;
}
