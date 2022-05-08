package com.example.java_project_gateway.controller;

import com.example.java_project_gateway.dto.xml.XmlExchangeRatesCommand;
import com.example.java_project_gateway.entity.CurrencyRate;
import com.example.java_project_gateway.exception.DuplicatedRequestIdException;
import com.example.java_project_gateway.exception.InvalidRequestException;
import com.example.java_project_gateway.service.StatisticCollectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xml_api")
@AllArgsConstructor
public class StatisticXmlController {
    private StatisticCollectorService statisticsCollectorService;

    @PostMapping(value = "/command", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<List<CurrencyRate>> getCurrencyHistory(@RequestBody XmlExchangeRatesCommand command) {
        try {
            return new ResponseEntity<>(command.getResult(statisticsCollectorService), HttpStatus.OK);

            //custom exception to prevent equal request
        } catch (DuplicatedRequestIdException | InvalidRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}