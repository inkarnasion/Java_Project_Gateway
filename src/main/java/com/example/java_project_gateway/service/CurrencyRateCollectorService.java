package com.example.java_project_gateway.service;

import com.example.java_project_gateway.dto.fixer.FixerResponse;
import com.example.java_project_gateway.entity.CurrencyRate;
import com.example.java_project_gateway.repository.CurrencyRateRepository;
import com.example.java_project_gateway.tools.Tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class CurrencyRateCollectorService {

    private final CurrencyRateRepository currencyRateRepository;

    /**
     * Refresh the exchange rates on each 10 hours
     *
     * @throws JsonProcessingException
     */
     @Scheduled(fixedRate = 36_000_000)
    private void collectRates() throws JsonProcessingException {
        System.out.println("Work");

        RestTemplate restTemplate = new RestTemplate();
        // get URL with added key and password
        String fooResourceUrl = "https://api.apilayer.com/fixer/latest?apikey=NKqh6Qz7pVXjylRrrQQphYXu3KxZgJox";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

        System.out.println(response);

        // getting all currency
        FixerResponse fixerResponce = restTemplate.getForObject(fooResourceUrl, FixerResponse.class);

        assert fixerResponce != null;
        System.out.println(fixerResponce.toString());

        //get last valid timestamp
        LocalDateTime validSince = Tools.toLocalDateTime(fixerResponce.getTimestamp());

        // get result
        List<CurrencyRate> rates = new ArrayList<>();
        for (Map.Entry<String, Double> mapEntry : fixerResponce.getRates().entrySet()) {
            CurrencyRate eur = new CurrencyRate(validSince, "EUR", mapEntry.getKey(), mapEntry.getValue());
            rates.add(eur);
        }

        System.out.println(rates.toString());

        // save result in database
        currencyRateRepository.saveAll(rates);
    }
}
