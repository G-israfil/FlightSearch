package com.example.casestudy.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.Date;

@Entity
@Table(name = "flights")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "depatureAirport")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "destinationAirport")
    private Airport destinationAirport;

    private Date departureDate;

    private Date returnDate;

    private double price;
}
