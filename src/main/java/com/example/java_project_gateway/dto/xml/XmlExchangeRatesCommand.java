package com.example.java_project_gateway.dto.xml;

import com.example.java_project_gateway.entity.CurrencyRate;
import com.example.java_project_gateway.entity.RequestsStatistic;
import com.example.java_project_gateway.exception.DuplicatedRequestIdException;
import com.example.java_project_gateway.exception.InvalidRequestException;
import com.example.java_project_gateway.service.StatisticCollectorService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@JacksonXmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmlExchangeRatesCommand implements Serializable {

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    @JacksonXmlProperty
    private Get get;

    @JacksonXmlProperty
    private History history;

    public List<CurrencyRate> getResult(StatisticCollectorService statisticsCollectorService) throws DuplicatedRequestIdException, InvalidRequestException {
        if (get != null){
            RequestsStatistic requestsStatistic = new RequestsStatistic( "XML_API", id, LocalDateTime.now(), get.getConsumer());
            return statisticsCollectorService.getCurrentCurrencyRates(get.getCurrency(), requestsStatistic);
        } else if (history != null) {
            RequestsStatistic requestsStatistic = new RequestsStatistic( "XML_API", id, LocalDateTime.now(), history.getConsumer());
            return statisticsCollectorService.getCurrencyRatesHistory(history.getCurrency(), requestsStatistic, history.getPeriod());
        } else {
            throw new InvalidRequestException("Missing mandatory element <get> or <history>");
        }
    }
}
