package com.bruno.currencyconverter.dto;

import com.bruno.currencyconverter.enums.CurrencyEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConverterRequestDto {

    private String idUser;
    private CurrencyEnum originCurrency;
    private BigDecimal originValue;
    private CurrencyEnum destinationCurrency;

}
