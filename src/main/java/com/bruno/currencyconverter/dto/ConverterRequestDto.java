package com.bruno.currencyconverter.dto;

import com.bruno.currencyconverter.enums.CurrencyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConverterRequestDto {

    @JsonProperty("moedaOrigem")
    private CurrencyEnum originCurrency;
    @JsonProperty("valorOrigem")
    private BigDecimal originValue;
    @JsonProperty("moedaDestino")
    private CurrencyEnum destinationCurrency;

}
