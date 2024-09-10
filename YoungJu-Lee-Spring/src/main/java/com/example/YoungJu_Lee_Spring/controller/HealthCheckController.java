package com.example.YoungJu_Lee_Spring.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "스프링아 건강하니? 네 건강해요.";
    }

}
