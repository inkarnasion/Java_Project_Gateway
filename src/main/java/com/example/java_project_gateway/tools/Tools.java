package com.example.java_project_gateway.tools;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;



/**
Additional method to reuse Epoch to calculate current time, and not to add in all classes
*/
public class Tools {
    public static LocalDateTime toLocalDateTime(long timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC);
    }
}