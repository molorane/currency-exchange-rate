package com.currencyexchangerate.exchangeservice2.service;

import com.currencyexchangerate.exchangeservice2.exception.DataNotFoundException;
import com.currencyexchangerate.exchangeservice2.model.ExchangeRate;
import com.currencyexchangerate.exchangeservice2.repository.ExchangeRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public ExchangeRate getLatestRate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return exchangeRateRepository.findAll(pageable).stream().findFirst().get();
        //return exchangeRateRepository.getLatestRate(pageable).stream().findFirst().orElseThrow( () -> new DataNotFoundException("Empty result"));
    }
}
