package com.example.casestudy.dto;


import com.example.casestudy.entity.Airport;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetFlightsRequest {

    private Airport departureAirport;
    private Airport destinationAirport;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime returnDate;
}
