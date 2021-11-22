package com.currencyexchangerate.exchangeservice2.mapper;

import com.currencyexchangerate.exchangeservice2.dto.ExchangeRateDto;
import com.currencyexchangerate.exchangeservice2.model.ExchangeRate;

import java.sql.Timestamp;
import java.util.Map;
import java.util.stream.Collectors;

public class ExchangeRateMapper {


    public static ExchangeRateDto convertExchangeRateToDto(ExchangeRate exchangeRate){

        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setBase(exchangeRate.getBase().getName());
        exchangeRateDto.setDate(exchangeRate.getDate());
        exchangeRateDto.setSuccess(exchangeRate.isSuccess());
        exchangeRateDto.setTimestamp(Timestamp.valueOf(exchangeRate.getLocalDateTime()));

        Map<String, String> rates = exchangeRate.getRates()
                .entrySet()
                .stream()
                .collect(Collectors.toMap( entry -> entry.getKey().getName(), entry -> entry.getValue().toString()));

        exchangeRateDto.setRates(rates);

        return exchangeRateDto;
    }
}
