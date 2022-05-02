package com.example.java_project_gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "currency_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRate extends BaseEntity {
    private LocalDateTime validSince;

    private String baseCurrency;

    private String targetCurrency;

    private Double exchangeRate;
}
