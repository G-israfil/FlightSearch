package com.example.casestudy.service;

import com.example.casestudy.dto.AirportDto;
import com.example.casestudy.entity.Airport;
import com.example.casestudy.repository.AirportRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    public Airport createAirport(@NonNull AirportDto airportDto){

        final Airport airport = new Airport();
        airport.setCity(airportDto.getCity());
        return this.airportRepository.save(airport);
    }

    public void deleteAirport(@NonNull int id){
        this.airportRepository.deleteById(id);
    }
    public Airport updateAirport(@NonNull int id,@NonNull AirportDto airportDto){
        final Optional<Airport> optionalAirport = this.airportRepository.findById(id);
        final Airport airport = optionalAirport.orElseThrow(() -> new RuntimeException("Airport not exist!"));
        if(StringUtils.hasText(airportDto.getCity())){
            airport.setCity(airportDto.getCity());
        }
        return this.airportRepository.save(airport);
    }

    public Airport getAirport(@NonNull int id){
        return this.airportRepository.findById(id).orElseThrow(() -> new RuntimeException("Airport not exist!"));
    }
}
