package com.example.java_project_gateway.controller;

import com.example.java_project_gateway.dto.json.CurrentExchangeRatesCommand;
import com.example.java_project_gateway.dto.json.ExchangeRatesHistoryCommand;
import com.example.java_project_gateway.entity.CurrencyRate;
import com.example.java_project_gateway.exception.DuplicatedRequestIdException;
import com.example.java_project_gateway.service.StatisticCollectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/json_api")
@AllArgsConstructor
public class StatisticJsonController {
    private StatisticCollectorService statisticsCollectorService;

    @PostMapping("/current")
    @ResponseBody
    public ResponseEntity<List<CurrencyRate>> getCurrentCurrency(@RequestBody CurrentExchangeRatesCommand command) {
        try {
            return new ResponseEntity<List<CurrencyRate>>(command.getResult(statisticsCollectorService, "JSON_API"), HttpStatus.OK);
        } catch (DuplicatedRequestIdException e) {
            return new ResponseEntity<List<CurrencyRate>>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/history")
    @ResponseBody
    public ResponseEntity<List<CurrencyRate>> getCurrencyHistory(@RequestBody ExchangeRatesHistoryCommand command) {
        try {
            return new ResponseEntity<List<CurrencyRate>>(command.getResult(statisticsCollectorService, "JSON_API"), HttpStatus.OK);
        } catch (DuplicatedRequestIdException e) {
            return new ResponseEntity<List<CurrencyRate>>(HttpStatus.BAD_REQUEST);
        }
    }
}