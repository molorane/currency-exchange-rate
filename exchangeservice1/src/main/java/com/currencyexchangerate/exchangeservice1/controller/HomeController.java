package com.currencyexchangerate.exchangeservice1.controller;

import com.currencyexchangerate.exchangeservice1.config.ExchangeRatesApiConfig;
import com.currencyexchangerate.exchangeservice1.model.ExchangeRate;
import com.currencyexchangerate.exchangeservice1.service.UriService;
import lombok.AllArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@AllArgsConstructor
public class HomeController {

    private UriService uriService;
    private WebClient.Builder webClientBuilder;

    @GetMapping("latest/")
    public ResponseEntity<ExchangeRate> latest(){

        ExchangeRate exchangeRate = webClientBuilder.build()
                .get()
                .uri(uriService.getBuilder().toString())
                .retrieve()
                .bodyToMono(ExchangeRate.class)
                .block();

        return new ResponseEntity<>(exchangeRate, HttpStatus.ACCEPTED);
    }

    @GetMapping("symbols")
    public ResponseEntity<ExchangeRate> latestWithSymbols(@RequestParam String symbols){

        String s = uriService.getBuilder(symbols).toString();
        ExchangeRate exchangeRate = webClientBuilder.build()
                .get()
                .uri(uriService.getBuilder(symbols))
                .retrieve()
                .bodyToMono(ExchangeRate.class)
                .block();

        return new ResponseEntity<>(exchangeRate, HttpStatus.ACCEPTED);
    }
}
