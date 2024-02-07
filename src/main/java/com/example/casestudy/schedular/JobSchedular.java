package com.example.casestudy.schedular;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JobSchedular {
    @Scheduled(cron = "0 0 0 * * *")
    public void fetchFlights() {
        System.out.println("Fetching the flight");
    }
}
