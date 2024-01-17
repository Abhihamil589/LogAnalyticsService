package com.abhihamil.las.service;

import com.abhihamil.las.collection.Logs;
import com.abhihamil.las.repositoy.LogsAnalyzeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogsAnalyzeImpl implements LogsAnalyzeService {
    @Autowired
    private LogsAnalyzeRepository logsAnalyzeRepository;

    @Override
    public Double getErrorPercentageByTimestampRange(LocalDateTime startTime, LocalDateTime endTime) {
        List<Logs> logsInRange = logsAnalyzeRepository.findByTimestampBetween(startTime, endTime);
        long errorCount = logsInRange.stream()
                .filter(log -> "ERROR".equals(log.getLogLevel()))
                .count();
        long totalLogs = logsInRange.size();

        return totalLogs > 0 ? ((double) errorCount / totalLogs) * 100 : 0;
    }

    @Override
    public List<String> getTopAccessedEndpoints(int limit) {
        List<Logs> allLogs = logsAnalyzeRepository.findAll();

        Map<String, Long> endpointAccessCount = allLogs.stream()
                .collect(Collectors.groupingBy(Logs::getEndpoint, Collectors.counting()));

        return endpointAccessCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
