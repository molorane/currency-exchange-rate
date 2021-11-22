package com.currencyexchangerate.exchangeservice2.repository;

import com.currencyexchangerate.exchangeservice2.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

}
