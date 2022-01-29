package com.bruno.currencyconverter.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class ConverterResponseDto {

   //ID da transação
    private Long idTransaction;
   //ID do usuário
    private Long idUser;
    //Moeda origem
    private String sourceCurrency;
    // Valor origem;
    private BigDecimal sourceValue;
    //Moeda destino;
    private String destinationCurrency;
    //Valor destino;
    private BigDecimal destinationValue;
    //Taxa de conversão utilizada
    private BigDecimal convertionRate;
    //Data/Hora UTC
    private LocalDateTime dateTime;

}
