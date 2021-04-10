package com.sripiranavan.freelance.spring.speedhome.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private Role userRole;
}
