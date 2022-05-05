package com.example.java_project_gateway.dto.fixer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class FixerResponse implements Serializable {

    // current time
    private long timestamp;

    // input currency
    private String baseCurrency;

    // additional currency and compared result with base currency
    private Map<String, Double> rates;
}
