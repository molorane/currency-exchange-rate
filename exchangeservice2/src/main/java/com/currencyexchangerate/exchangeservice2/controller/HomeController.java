package com.currencyexchangerate.exchangeservice2.controller;

import com.currencyexchangerate.exchangeservice2.dto.ExchangeRateDto;
import com.currencyexchangerate.exchangeservice2.mapper.ExchangeRateMapper;
import com.currencyexchangerate.exchangeservice2.model.ExchangeRate;
import com.currencyexchangerate.exchangeservice2.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historical")
@AllArgsConstructor
public class HomeController {

    private ExchangeRateService exchangeRateService;

    @GetMapping("latest")
    public ResponseEntity<ExchangeRateDto> getAllLatestRates() {

        ExchangeRate exchangeRate = exchangeRateService.getLatestRate(0,1);

        return new ResponseEntity<>(ExchangeRateMapper.convertExchangeRateToDto(exchangeRate), HttpStatus.ACCEPTED);
    }
}
