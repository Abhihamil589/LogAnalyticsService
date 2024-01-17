package com.abhihamil.las.controller;

import com.abhihamil.las.service.LogsAnalyzeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/las/analyze")
public class LogsAnalyzeController {
    @Autowired
    private LogsAnalyzeService logsAnalyzeService;

    private final String Service_Name = "ErrorPercentage";

    @GetMapping("/errorRate")
    @CircuitBreaker(name = Service_Name, fallbackMethod = "errorRateFallback")
    public Double getErrorPercentageByTimestampRange(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        return logsAnalyzeService.getErrorPercentageByTimestampRange(startTime, endTime);

    }

    // Fallback method for the circuit breaker
    public ResponseEntity<Double> errorRateFallback(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
            Exception ex) {
        System.out.println("inside fallback");
        return ResponseEntity.status(500).body(-1.0);
    }

    @GetMapping("/topEndpoints")
    public List<String> getTopAccessedEndpoints(
            @RequestParam("limit") int limit) {
        return logsAnalyzeService.getTopAccessedEndpoints(limit);

    }
}
