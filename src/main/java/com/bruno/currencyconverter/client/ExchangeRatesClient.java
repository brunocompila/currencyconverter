package com.bruno.currencyconverter.client;

import com.bruno.currencyconverter.dto.ExchangeRateResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "exchangeRates", url = "${client.exchangeRates.url}")
public interface ExchangeRatesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest")
    Mono<ExchangeRateResponseDto> getExchangeRate(@RequestParam("access_key") String token);


}
