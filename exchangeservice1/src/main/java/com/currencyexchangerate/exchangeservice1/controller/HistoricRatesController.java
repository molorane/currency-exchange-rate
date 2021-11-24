package com.currencyexchangerate.exchangeservice1.controller;

import com.currencyexchangerate.exchangeservice1.model.ExchangeRate;
import com.currencyexchangerate.exchangeservice1.service.UriService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/historical")
public class HistoricRatesController {

    private final UriService uriService;
    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final Logger logger = LoggerFactory.getLogger(HistoricRatesController.class);

    public HistoricRatesController(UriService uriService, WebClient.Builder loadBalancedWebClientBuilder) {
        this.uriService = uriService;
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
    }

    @GetMapping("latest/")
    @CircuitBreaker(name = "service1", fallbackMethod = "xrateFallback")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterfallback")
    @Retry(name = "retryService1", fallbackMethod = "retryfallback")
    @Bulkhead(name = "bulkheadService1", fallbackMethod = "bulkHeadFallback")
    public ResponseEntity<?> latest(){

        ExchangeRate exchangeRate = loadBalancedWebClientBuilder.build()
                .get()
                .uri("http://exchangeservice2-service/xrateservice2/historical/latest")
                .retrieve()
                .bodyToMono(ExchangeRate.class)
                .block();

        return new ResponseEntity<>(exchangeRate, HttpStatus.ACCEPTED);
    }

    public  ResponseEntity<?> rateLimiterfallback(Throwable t) {
        String str = "Inside rateLimiterfallback, cause - "+t.toString();
        logger.error(str);
        return new ResponseEntity<>(str, HttpStatus.ACCEPTED);
    }

    public  ResponseEntity<?> bulkHeadFallback(Throwable t) {
        String str = "Inside bulkHeadFallback, cause -  "+t.toString();
        logger.error(str);
        return new ResponseEntity<>( "Inside bulkHeadFallback method. Some error occurred while calling service2", HttpStatus.ACCEPTED);
    }

    public  ResponseEntity<?> retryfallback(Throwable t) {
        String str = "Inside retryfallback, cause - "+t.toString();
        logger.error(str);
        return new ResponseEntity<>( "Inside retryfallback method. Some error occurred while calling service2", HttpStatus.ACCEPTED);
    }

    public  ResponseEntity<?> xrateFallback(Throwable t) {
        String str = "Service 2 is down, cause - "+t.toString();
        logger.error(str);
        return new ResponseEntity<>( "Service 2 is down", HttpStatus.ACCEPTED);
    }

}
