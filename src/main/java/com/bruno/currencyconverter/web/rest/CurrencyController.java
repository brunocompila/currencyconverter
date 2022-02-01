package com.bruno.currencyconverter.web.rest;

import com.bruno.currencyconverter.domain.ConversionTransaction;
import com.bruno.currencyconverter.dto.ConversionRequestDto;
import com.bruno.currencyconverter.exceptions.GlobalErrorAttributes;
import com.bruno.currencyconverter.service.ConversionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for currency converter.
 */
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
@Import(GlobalErrorAttributes.class)
public class CurrencyController {

    private final ConversionService convertService;

    @Operation(summary = "Convert a specific currency according to rates of conversion  ", tags = {"currency"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conversion succesfull",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConversionTransaction.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @PostMapping(
            value = "/conversions",
            produces = {"application/json"}
    )

    public ResponseEntity<Mono<ConversionTransaction>> converter(@RequestBody(required = true) ConversionRequestDto converterRequestDto) {

        return new ResponseEntity<>(convertService.convert(converterRequestDto), HttpStatus.OK);

    }


    @Operation(summary = "Get a list of all Currency conversions", tags = {"currency"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfull",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema( implementation =
                                    ConversionTransaction.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @GetMapping(
            value = "/conversions",
            produces = {"application/json"}
    )
    public ResponseEntity<Flux<ConversionTransaction>> getConvertionTransactions() {

        return new ResponseEntity<>(convertService.getConvertionTransactions(), HttpStatus.OK);

    }

}
