package com.example.casestudy.controller;


import com.example.casestudy.dto.AirportDto;
import com.example.casestudy.entity.Airport;
import com.example.casestudy.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("airport")
@RequiredArgsConstructor
public class AirportController {
   private final AirportService airportService;

    @PostMapping("/create")
    public ResponseEntity<Airport> createAirport(@RequestBody AirportDto airportDto){
        return ResponseEntity.ok(this.airportService.createAirport(airportDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable(name = "id") final String id){
        this.airportService.deleteAirport(Integer.parseInt(id));
        return ResponseEntity.ok("Airport deleted successfully");
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@RequestBody AirportDto airportDto,@PathVariable(name = "id") final String id){
        return ResponseEntity.ok(this.airportService.updateAirport(Integer.parseInt(id),airportDto));
    }

    @GetMapping("/{id}")
    public Airport getAirport(@PathVariable(name = "id") final String id){
        return this.airportService.getAirport(Integer.parseInt(id));
    }
}
