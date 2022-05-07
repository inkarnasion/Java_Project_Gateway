package com.example.java_project_gateway.dto.json;


import com.example.java_project_gateway.entity.CurrencyRate;
import com.example.java_project_gateway.entity.RequestsStatistic;
import com.example.java_project_gateway.exception.DuplicatedRequestIdException;
import com.example.java_project_gateway.service.StatisticCollectorService;
import com.example.java_project_gateway.tools.Tools;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class CurrentExchangeRatesCommand implements Serializable {
    private String requestId;
    private long timestamp;
    private String client;
    private String currency;

    public List<CurrencyRate> getResult(StatisticCollectorService statisticsCollectorService, String serviceName) throws DuplicatedRequestIdException {
        RequestsStatistic requestsStatistic = new RequestsStatistic( serviceName, requestId, Tools.toLocalDateTime(timestamp), client);

        return statisticsCollectorService.getCurrentCurrencyRates(currency, requestsStatistic);
    }
}
