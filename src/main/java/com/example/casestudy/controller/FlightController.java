package com.example.casestudy.controller;


import com.example.casestudy.dto.FlightDto;
import com.example.casestudy.dto.GetFlightsRequest;
import com.example.casestudy.entity.Flight;
import com.example.casestudy.security.JwtTokenUtil;
import com.example.casestudy.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private final JwtTokenUtil tokenUtil;

    @PostMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDto flightDto,@RequestHeader String authorization){
        String username = tokenUtil.getUsernameFromToken(authorization.replace("Bearer","").trim());
        return ResponseEntity.ok(this.flightService.createFlight(flightDto, username));
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

    @PostMapping("/listFlights")
    public List<Flight> getFlight(@RequestBody final GetFlightsRequest request){
        return this.flightService.getFlights(request);
    }
}
