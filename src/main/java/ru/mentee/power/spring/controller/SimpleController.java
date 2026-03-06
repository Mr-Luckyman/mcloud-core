package ru.mentee.power.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    private int visitorCount = 0;

    @GetMapping("/")
    public String simple() {
        visitorCount++;
        return String.format("Количество посещений: #%d", visitorCount);
    }

    @GetMapping("/health")
    public String health() {
        return "{\"status\":\"UP\"}";
    }
}
