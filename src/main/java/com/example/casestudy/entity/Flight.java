package com.example.casestudy.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "tenant")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "depatureAirport")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "destinationAirport")
    private Airport destinationAirport;

    private LocalDateTime departureDate;

    private Boolean isReturn = false;

    private double price;
}
