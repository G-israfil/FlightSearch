package com.example.casestudy.controller;


import com.example.casestudy.dto.AirportDto;
import com.example.casestudy.dto.FlightDto;
import com.example.casestudy.entity.Airport;
import com.example.casestudy.entity.Flight;
import com.example.casestudy.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;


    @PostMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDto flightDto){
        return ResponseEntity.ok(this.flightService.createFlight(flightDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable(name = "id") final String id){
        this.flightService.deleteFlight(Integer.parseInt(id));
        return ResponseEntity.ok("Flight deleted successfully");
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@RequestBody FlightDto flightDto, @PathVariable(name = "id") final String id){
        return ResponseEntity.ok(this.flightService.updateFlight(Integer.parseInt(id),flightDto));
    }

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable(name = "id") final String id){
        return this.flightService.getFlight(Integer.parseInt(id));
    }
}
