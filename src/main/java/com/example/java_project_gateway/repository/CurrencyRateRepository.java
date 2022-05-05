package com.example.java_project_gateway.repository;

import com.example.java_project_gateway.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    @Query(
            nativeQuery = true,
            value = "select distinct(target_currency) as target_currency " +
                    "  from currency_rate " +
                    " where base_currency = :baseCurrency "
    )
    List<String> getAllTargetCurrencies(@Param("baseCurrency") String baseCurrency);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "  from currency_rate " +
                    " where base_currency = :baseCurrency " +
                    "   and target_currency = :targetCurrency " +
                    "   and valid_since <= :validSince " +
                    " order by valid_since desc " +
                    " limit 1"
    )
    CurrencyRate findCurrencyRate(
            @Param("baseCurrency") String baseCurrency,
            @Param("targetCurrency") String targetCurrency,
            @Param("validSince") LocalDateTime validSince
    );

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "  from currency_rate " +
                    " where base_currency = :baseCurrency " +
                    "   and target_currency = :targetCurrency " +
                    "   and valid_since <= :validSinceHistoryEnd " +
                    "   and valid_since >= :validSinceHistoryStart " +
                    " order by valid_since desc "
    )
    List<CurrencyRate> findCurrencyRateHistory(
            @Param("baseCurrency") String baseCurrency,
            @Param("targetCurrency") String targetCurrency,
            @Param("validSinceHistoryEnd") LocalDateTime validSinceHistoryEnd,
            @Param("validSinceHistoryStart") LocalDateTime validSinceHistoryStart
    );
}
