package com.bruno.currencyconverter.client;

import com.bruno.currencyconverter.dto.ExchangeRateResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "exchangeRates", url = "${client.exchangeRates.url}")
public interface ExchangeRatesClient {

    @GetMapping(path = "/latest")
    Mono<ExchangeRateResponseDto> getExchangeRate(@RequestParam("access_key") String token);


}
