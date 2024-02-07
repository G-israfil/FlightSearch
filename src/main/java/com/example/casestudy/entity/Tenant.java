package com.example.casestudy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tenants")
@Data
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",unique = true)
    private String username;

    private UUID uuid;

    @Column(length = 64, nullable = false)
    private String hash;

    @Column(length = 16, nullable = false)
    private String salt;
}
