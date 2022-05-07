package com.example.java_project_gateway.service;

import com.example.java_project_gateway.entity.CurrencyRate;
import com.example.java_project_gateway.entity.RequestsStatistic;
import com.example.java_project_gateway.exception.DuplicatedRequestIdException;
import com.example.java_project_gateway.rabbitmq.RabbitMqSender;
import com.example.java_project_gateway.repository.CurrencyRateRepository;
import com.example.java_project_gateway.repository.RequestsStatisticRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class StatisticCollectorService {


    private CurrencyRateRepository currencyRateRepository;

    private RequestsStatisticRepository requestsStatisticRepository;

    private RabbitMqSender rabbitMqSender;

    public List<CurrencyRate> getCurrentCurrencyRates(String baseCurrency, RequestsStatistic requestsStatistic) throws DuplicatedRequestIdException {
        validateSaveAndSend(requestsStatistic);

        List<CurrencyRate> list = new ArrayList<>();
        for (String targetCurrency : currencyRateRepository.getAllTargetCurrencies(baseCurrency)) {
            CurrencyRate currencyRate = currencyRateRepository.findCurrencyRate(baseCurrency, targetCurrency, requestsStatistic.getTime());
            if (currencyRate != null) {
                list.add(currencyRate);
            }
        }
        return list;
    }

    public List<CurrencyRate> getCurrencyRatesHistory(String baseCurrency, RequestsStatistic requestsStatistic, int period) throws DuplicatedRequestIdException {
        validateSaveAndSend(requestsStatistic);

        LocalDateTime validSinceHistoryStart = requestsStatistic.getTime().minusHours(period);

        List<CurrencyRate> list = new ArrayList<>();
        for (String targetCurrency : currencyRateRepository.getAllTargetCurrencies(baseCurrency)) {
            List<CurrencyRate> currencyRateHistory = currencyRateRepository.findCurrencyRateHistory(baseCurrency, targetCurrency, requestsStatistic.getTime(), validSinceHistoryStart);
            for (CurrencyRate currencyRate : currencyRateHistory) {
                if (currencyRate != null) {
                    list.add(currencyRate);
                }
            }
        }
        return list;
    }

    private void validateSaveAndSend(RequestsStatistic requestsStatistic) throws DuplicatedRequestIdException {
        validateRequestId(requestsStatistic.getRequestId());

        requestsStatisticRepository.save(requestsStatistic);

        rabbitMqSender.send(requestsStatistic);
    }

    private void validateRequestId(String requestId) throws DuplicatedRequestIdException {
        if (requestsStatisticRepository.findByRequestId(requestId) != null){
            throw new DuplicatedRequestIdException("Duplicated request ID");
        }
    }
}
