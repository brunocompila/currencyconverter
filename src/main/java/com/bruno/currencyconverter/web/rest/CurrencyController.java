package com.bruno.currencyconverter.web.rest;

import com.bruno.currencyconverter.domain.Convertion;
import com.bruno.currencyconverter.dto.ConverterRequestDto;
import com.bruno.currencyconverter.dto.ConverterResponseDto;
import com.bruno.currencyconverter.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for currency converter.
 */
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final ConvertService convertService;

    /**
     * POST /currency/convert : Service to convert currency
     *
     * @param idUser  (required)
     * @return Sucesso (status code 200)
     *         Bad Request (status code 400)
     *         Internal Error (status code 500)
     */
//
//    @ApiResponse(value = "registro de Usuário", nickname = "userRegister", notes = "", response = UserRegisterResponse.class, tags={ "users", })
//    @ApiResponses(value = {
//
//            @ApiResponse(code = 200, message = "Sucesso", response = CurrencyConverterResponse.class),
//
//            @ApiResponse(code = 400, message = "Bad Request", response = Error.class) })
    @PostMapping(
            value = "/convert",
            produces = { "application/json" }
    )

    public ResponseEntity<Mono<Convertion>> converter(@RequestBody ConverterRequestDto converterRequestDto,
                                                      @RequestHeader(required = true) final Long idUser){

        Mono<Convertion> convert = convertService.convert(converterRequestDto, idUser);
        return new ResponseEntity<>(convert, HttpStatus.OK);

    }

}
