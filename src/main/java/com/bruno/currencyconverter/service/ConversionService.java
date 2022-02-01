package com.bruno.currencyconverter.service;


import com.bruno.currencyconverter.client.ExchangeRatesClient;
import com.bruno.currencyconverter.domain.ConversionTransaction;
import com.bruno.currencyconverter.dto.ConversionRequestDto;
import com.bruno.currencyconverter.dto.ExchangeRateResponseDto;
import com.bruno.currencyconverter.repository.ConversionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConversionService {

    @Value("${client.exchangeRates.token}")
    private String acessTokem;

    private final ConversionRepository convertionRepository;
    private final ExchangeRatesClient exchangeRatesClient;

    public Mono<ConversionTransaction> convert(final ConversionRequestDto converterRequestDto) {

        final Mono<ExchangeRateResponseDto> exchangeRate = exchangeRatesClient.getExchangeRate(acessTokem);

        return exchangeRate.flatMap(dto ->
             getConvertionMono(dto, converterRequestDto)
        );
    }

    public Mono<ConversionTransaction> getConvertionMono(final ExchangeRateResponseDto exchangeRateResponseDto,
                                                         final ConversionRequestDto converterRequestDto) {

        final String originCurrency = converterRequestDto.getOriginCurrency().name();
        final String destinationCurrency = converterRequestDto.getDestinationCurrency().name();

        final BigDecimal originValue = converterRequestDto.getOriginValue();
        final BigDecimal originRate = exchangeRateResponseDto.getRates().get(originCurrency);
        final BigDecimal destinationRate = exchangeRateResponseDto.getRates().get(destinationCurrency);

        final BigDecimal convertionRate = destinationRate.divide(originRate, 5, RoundingMode.HALF_UP);

        final BigDecimal destinationValue = originValue.multiply(convertionRate);

        ConversionTransaction convertion = ConversionTransaction.builder()
                .idUser(converterRequestDto.getIdUser())
                .originCurrency(originCurrency)
                .originValue(originValue)
                .destinationCurrency(destinationCurrency)
                .destinationValue(destinationValue)
                .convertionRate(convertionRate)
                .dateTime(LocalDateTime.now())
                .build();

        return convertionRepository.save(convertion);
    }

    public Flux<ConversionTransaction> getConvertionTransactions(){

        return convertionRepository.findAll();
    }


}
