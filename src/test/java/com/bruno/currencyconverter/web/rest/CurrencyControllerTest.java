package com.bruno.currencyconverter.web.rest;

import com.bruno.currencyconverter.domain.ConversionTransaction;
import com.bruno.currencyconverter.dto.ConversionRequestDto;
import com.bruno.currencyconverter.enums.CurrencyEnum;
import com.bruno.currencyconverter.service.ConversionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CurrencyController.class)
class CurrencyControllerTest {

    @MockBean
    private ConversionService convertService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void converter_sucess() {

        ConversionRequestDto converterRequestDto = ConversionRequestDto.builder()
                .destinationCurrency(CurrencyEnum.BRL)
                .originCurrency(CurrencyEnum.USD)
                .idUser("0099")
                .originValue(new BigDecimal("2.2"))
                .build();

        Mockito.when(convertService.convert(converterRequestDto)).thenReturn(
                      Mono.just(ConversionTransaction.builder().build()));

        webTestClient.post()
                .uri("/currency/conversions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(converterRequestDto))
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void getConvertionTransactions_sucess() {

        Mockito.when(convertService.getConvertionTransactions())
                .thenReturn(Flux.fromIterable(Arrays
                        .asList(ConversionTransaction.builder()
                                .build())));

        webTestClient.get()
                .uri("/currency/conversions")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(ConversionTransaction.class);

    }
}