package com.bruno.currencyconverter.dto;

import com.bruno.currencyconverter.enums.CurrencyEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ConversionRequestDto {

    private String idUser;
    private CurrencyEnum originCurrency;
    private BigDecimal originValue;
    private CurrencyEnum destinationCurrency;

}
