package com.bruno.currencyconverter.service;

import com.bruno.currencyconverter.client.ExchangeRatesClient;
import com.bruno.currencyconverter.domain.ConversionTransaction;
import com.bruno.currencyconverter.dto.ConversionRequestDto;
import com.bruno.currencyconverter.dto.ExchangeRateResponseDto;
import com.bruno.currencyconverter.enums.CurrencyEnum;
import com.bruno.currencyconverter.repository.ConversionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
class ConversionServiceTest {

    @InjectMocks
    private ConversionService convertService;

    @Mock
    private ExchangeRatesClient exchangeRatesClient;

    @Mock
    private ConversionRepository convertionRepository;

    /**
     * Test get value of Conversion
     */
    @Test
    void getConversion_get_destination_value_sucess(){

        ReflectionTestUtils.setField(convertService, "acessTokem", "ree");

        BigDecimal destinationValue = new BigDecimal(3);

        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(CurrencyEnum.BRL.name(), new BigDecimal(1));
        rates.put(CurrencyEnum.USD.name(), new BigDecimal(2));

        ExchangeRateResponseDto exchangeRateResponseDto = new ExchangeRateResponseDto();
        exchangeRateResponseDto.setRates(rates);

        Mockito.when(exchangeRatesClient.getExchangeRate(Mockito.anyString())).
                thenReturn(Mono.just(exchangeRateResponseDto));

        Mockito.when(convertionRepository.save(Mockito.any()))
                .thenReturn(Mono.just(ConversionTransaction.builder()
                                .destinationValue(destinationValue)
                        .build()));

        ConversionRequestDto converterRequestDto = ConversionRequestDto.builder()
                .originValue(new BigDecimal(1))
                .destinationCurrency(CurrencyEnum.BRL)
                .originCurrency(CurrencyEnum.USD)
                .build();

        Mono<ConversionTransaction> convert = convertService.convert(converterRequestDto);

        Assertions.assertEquals(destinationValue, convert.block().getDestinationValue());

    }

}