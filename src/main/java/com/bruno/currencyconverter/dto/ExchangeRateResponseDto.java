package com.bruno.currencyconverter.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
public class ExchangeRateResponseDto {

    private String base;
    private Long timestamp;
    private LocalDate date;
    private Map<String, BigDecimal> rates;


}
