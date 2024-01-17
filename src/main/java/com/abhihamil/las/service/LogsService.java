package com.abhihamil.las.service;

import com.abhihamil.las.collection.Logs;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface LogsService {
    List<Logs> saveAllLogs(List<Logs> logs);
    List<Logs> getAllLogs();

    List<Logs> getLogsByTimestampRange(LocalDateTime startTime, LocalDateTime endTime);


}
