package com.bruno.currencyconverter.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class ConversionResponseDto {


    private Long idTransaction;
    private Long idUser;
    private String sourceCurrency;
    private BigDecimal sourceValue;
    private String destinationCurrency;
    private BigDecimal destinationValue;
    private BigDecimal convertionRate;
    private LocalDateTime dateTime;

}
