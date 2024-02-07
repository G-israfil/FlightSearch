package com.example.casestudy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDto {
    private int departureAirport;

    private int destinationAirport;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime returnDate;

    private double price;
}
