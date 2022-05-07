package com.example.java_project_gateway.dto.json;


import com.example.java_project_gateway.entity.CurrencyRate;
import com.example.java_project_gateway.entity.RequestsStatistic;
import com.example.java_project_gateway.exception.DuplicatedRequestIdException;
import com.example.java_project_gateway.service.StatisticCollectorService;
import com.example.java_project_gateway.tools.Tools;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExchangeRatesHistoryCommand extends CurrentExchangeRatesCommand {
    private int period;

    @Override
    public List<CurrencyRate> getResult(StatisticCollectorService statisticsCollectorService, String serviceName) throws DuplicatedRequestIdException {
        RequestsStatistic requestsStatistic = new RequestsStatistic( serviceName, getRequestId(), Tools.toLocalDateTime(getTimestamp()), getClient());

        return statisticsCollectorService.getCurrencyRatesHistory(getCurrency(), requestsStatistic, period);
    }
}
