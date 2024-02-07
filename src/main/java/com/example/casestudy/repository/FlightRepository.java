package com.example.casestudy.repository;

import com.example.casestudy.entity.Airport;
import com.example.casestudy.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {

    List<Flight> findAllByDepartureAirportAndDestinationAirportAndDepartureDateAndIsReturn(Airport departureAirport, Airport destinationAirport, LocalDateTime departureDate,Boolean isReturn);
}
