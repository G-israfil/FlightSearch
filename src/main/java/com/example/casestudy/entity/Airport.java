package com.example.casestudy.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "airports")
@Data
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city")
    private String city;
}
