package com.example.casestudy.service;

import com.example.casestudy.dto.AirportDto;
import com.example.casestudy.dto.FlightDto;
import com.example.casestudy.entity.Airport;
import com.example.casestudy.entity.Flight;
import com.example.casestudy.repository.FlightRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;
    public Flight createFlight(@NonNull FlightDto flightDto){
        final Flight flight = new Flight();
        flight.setPrice(flightDto.getPrice());
        flight.setDepartureDate(flightDto.getDepartureDate());
        if(Objects.nonNull(flightDto.getReturnDate())) flight.setReturnDate(flightDto.getReturnDate());

        final Airport departureAirport = this.airportService.getAirport(flightDto.getDepartureAirport());
        flight.setDepartureAirport(departureAirport);
        final Airport destinationAirport = this.airportService.getAirport(flightDto.getDestinationAirport());
        flight.setDestinationAirport(destinationAirport);

        return this.flightRepository.save(flight);
    }

    public void deleteFlight(@NonNull int id){
        this.flightRepository.deleteById(id);
    }
    public Flight updateFlight(@NonNull int id,@NonNull FlightDto flightDto){
        final Flight flight = this.flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found!"));
        if(!Double.isNaN(flightDto.getPrice())) flight.setPrice(flightDto.getPrice());

        if(Objects.nonNull(flightDto.getDepartureAirport())) flight.setDepartureDate(flightDto.getDepartureDate());
        if(Objects.nonNull(flightDto.getReturnDate())) flight.setReturnDate(flightDto.getReturnDate());

        if(Objects.nonNull(flightDto.getDepartureAirport())){
            final Airport departureAirport = this.airportService.getAirport(flightDto.getDepartureAirport());
            flight.setDepartureAirport(departureAirport);
        }

        if(Objects.nonNull(flightDto.getDestinationAirport())){
            final Airport destinationAirport = this.airportService.getAirport(flightDto.getDestinationAirport());
            flight.setDestinationAirport(destinationAirport);
        }

        return this.flightRepository.save(flight);
    }

    public Flight getFlight(@NonNull int id){
        return this.flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found!"));
    }
}
