package com.example.java_project_gateway.controller;

import com.example.java_project_gateway.entity.CurrencyRate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class StatisticJsonController {
    @PostMapping("/current")
    @ResponseBody
    public ResponseEntity<List<CurrencyRate>> getCurrentCurrency() {


    }

    @PostMapping("/history")
    @ResponseBody
    public ResponseEntity<List<CurrencyRate>> getCurrencyHistory() {

        
    }
}