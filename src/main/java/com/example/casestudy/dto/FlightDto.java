package com.example.casestudy.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FlightDto {
    private int departureAirport;

    private int destinationAirport;

    private Date departureDate;

    private Date returnDate;

    private double price;
}
