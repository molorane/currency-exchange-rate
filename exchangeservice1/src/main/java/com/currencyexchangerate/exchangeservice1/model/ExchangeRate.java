package com.currencyexchangerate.exchangeservice1.model;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class ExchangeRate {

    private boolean success;
    private Timestamp timestamp;
    private String base;
    private LocalDate date;
    Map<String, String> rates;

}
