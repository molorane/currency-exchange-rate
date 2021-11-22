package com.currencyexchangerate.exchangeservice2.repository;

import com.currencyexchangerate.exchangeservice2.model.ExchangeRate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query("SELECT c FROM ExchangeRate c")
    List<ExchangeRate> getLatestRate(Pageable pageable);
}
