package com.abhihamil.las.repositoy;

import com.abhihamil.las.collection.Logs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogsRepository extends MongoRepository<Logs, String> {
    List<Logs> findByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);

}
