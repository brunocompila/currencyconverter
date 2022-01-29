package com.bruno.currencyconverter.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
public class Convertion {

    @Id
    private String idTransaction;
    private Long idUser;
    private String originCurrency;
    private BigDecimal originValue;
    private String destinationCurrency;
    private BigDecimal destinationValue;
    private BigDecimal convertionRate;
    private LocalDateTime dateTime;

}
