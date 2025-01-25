package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// this healthcheck class is used to check if the application is running or not
public class HealthCheck {
    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }
}
