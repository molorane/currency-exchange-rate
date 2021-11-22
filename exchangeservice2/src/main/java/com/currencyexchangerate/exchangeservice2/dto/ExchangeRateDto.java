package com.currencyexchangerate.exchangeservice2.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;

@Data
public class ExchangeRateDto {

    private boolean success;
    private Timestamp timestamp;
    private String base;
    private LocalDate date;
    Map<String, String> rates;

}
