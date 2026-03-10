package ru.mentee.power.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.mentee.power.spring.service.VisitService;

@RequiredArgsConstructor
@RestController
public class SimpleController {
    private final VisitService visitService;

    @GetMapping("/")
    public String simple() {
        Long currentCount = visitService.incrementAndGetCount();
        return String.format("Количество посещений: #%d", currentCount);
    }

    @GetMapping("/health")
    public String health() {
        return "{\"status\":\"UP\"}";
    }
}
