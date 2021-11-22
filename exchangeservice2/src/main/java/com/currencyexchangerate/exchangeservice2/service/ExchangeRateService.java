package com.currencyexchangerate.exchangeservice2.service;

import com.currencyexchangerate.exchangeservice2.model.ExchangeRate;

public interface ExchangeRateService {

    public ExchangeRate getLatestRate(int page, int size);

}
