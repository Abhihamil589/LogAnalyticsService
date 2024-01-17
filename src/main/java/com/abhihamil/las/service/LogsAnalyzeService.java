package com.abhihamil.las.service;

import java.time.LocalDateTime;
import java.util.List;

public interface LogsAnalyzeService {
    Double getErrorPercentageByTimestampRange(LocalDateTime startTime, LocalDateTime endTime);

    List<String> getTopAccessedEndpoints(int limit);
}
