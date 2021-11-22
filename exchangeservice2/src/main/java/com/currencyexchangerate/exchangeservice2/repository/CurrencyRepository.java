package com.currencyexchangerate.exchangeservice2.repository;

import com.currencyexchangerate.exchangeservice2.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findCurrencyByName(String name);
}
