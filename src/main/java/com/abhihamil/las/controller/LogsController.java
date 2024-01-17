package com.abhihamil.las.controller;

import com.abhihamil.las.collection.Logs;
import com.abhihamil.las.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogsController {
    @Autowired
    private LogsService logsService;

    @PostMapping
    public List<Logs> saveLogs(@RequestBody List<Logs> logs) {
        System.out.println("saveLogs controller ");
        return logsService.saveAllLogs(logs);
    }

    @GetMapping("/all")
    public List<Logs> getAllLogs() {
        return logsService.getAllLogs();
    }

    @GetMapping("/byTimestampRange")
    public List<Logs> getLogsByTimestampRange(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        return logsService.getLogsByTimestampRange(startTime, endTime);

    }

}
