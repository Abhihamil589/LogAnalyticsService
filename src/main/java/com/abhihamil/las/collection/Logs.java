package com.abhihamil.las.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "logs")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Logs {

    @Id
    private String logId;
    private Date timestamp;
    private String logLevel;
    private String endpoint;
    private String message;


}
