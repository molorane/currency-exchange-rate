package com.currencyexchangerate.exchangeservice1.controller;

import com.currencyexchangerate.exchangeservice1.model.ExchangeRate;
import com.currencyexchangerate.exchangeservice1.service.UriService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/historical")
@AllArgsConstructor
public class HistoricRatesController {

    private UriService uriService;
    private WebClient.Builder loadBalancedWebClientBuilder;

    @GetMapping("latest/")
    public ResponseEntity<ExchangeRate> latest(){

        ExchangeRate exchangeRate = loadBalancedWebClientBuilder.build()
                .get()
                .uri("http://exchangeservice2-service/xrateservice2/historical/latest")
                .retrieve()
                .bodyToMono(ExchangeRate.class)
                .block();

        return new ResponseEntity<>(exchangeRate, HttpStatus.ACCEPTED);
    }
}
