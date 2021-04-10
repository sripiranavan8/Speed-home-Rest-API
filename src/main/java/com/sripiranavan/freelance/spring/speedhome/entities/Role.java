package com.sripiranavan.freelance.spring.speedhome.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @OneToMany(fetch = FetchType.EAGER,mappedBy="userRole",cascade = CascadeType.ALL)
    private Set<User> user;
}
