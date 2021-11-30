package com.currencyexchangerate.exchangeservice2.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class FetchRates {

    @Scheduled(fixedDelay = 5000L)
    public void someJob(){
        System.out.println("Now is "+ LocalDateTime.now());
    }
}
