package com.abhihamil.las.service;

import com.abhihamil.las.collection.Logs;
import com.abhihamil.las.repositoy.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service

public class LogsServiceImpl implements LogsService {
    @Autowired
    private LogsRepository logsRepository;

    @Override
    public List<Logs> saveAllLogs(List<Logs> logs) {
        return logsRepository.saveAll(logs);
    }

    @Override
    public List<Logs> getAllLogs() {
        return logsRepository.findAll();
    }


    public List<Logs> getLogsByTimestampRange(LocalDateTime startTime, LocalDateTime endTime) {
        return logsRepository.findByTimestampBetween(startTime, endTime);
    }


}

