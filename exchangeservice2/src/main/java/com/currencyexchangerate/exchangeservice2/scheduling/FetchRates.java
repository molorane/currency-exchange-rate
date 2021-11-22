package com.currencyexchangerate.exchangeservice2.scheduling;

import com.currencyexchangerate.exchangeservice2.config.ExchangeRatesApiConfig;
import com.currencyexchangerate.exchangeservice2.dto.ExchangeRateDto;
import com.currencyexchangerate.exchangeservice2.model.Currency;
import com.currencyexchangerate.exchangeservice2.model.ExchangeRate;
import com.currencyexchangerate.exchangeservice2.repository.CurrencyRepository;
import com.currencyexchangerate.exchangeservice2.repository.ExchangeRateRepository;
import com.currencyexchangerate.exchangeservice2.service.UriService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class FetchRates {

    private final ExchangeRatesApiConfig  exchangeRatesApiConfig;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final UriService uriService;
    private final WebClient.Builder webClientBuilder;

    private Currency currency;

    public FetchRates(ExchangeRatesApiConfig exchangeRatesApiConfig, CurrencyRepository currencyRepository, ExchangeRateRepository exchangeRateRepository, UriService uriService, WebClient.Builder webClientBuilder) {
        this.exchangeRatesApiConfig = exchangeRatesApiConfig;
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.uriService = uriService;
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void setBaseCurrency() {
        this.currency = currencyRepository.findCurrencyByName("EUR");
    }


    @Scheduled(fixedDelayString = "${update_rates.cron}")
    public void someJob(){

        ExchangeRateDto exchangeRateDto = webClientBuilder.build()
                .get()
                .uri(uriService.getBuilder().toString())
                .retrieve()
                .bodyToMono(ExchangeRateDto.class)
                .block();

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBase(currency);
        exchangeRate.setSuccess(exchangeRateDto.isSuccess());
        exchangeRate.setDate(exchangeRateDto.getDate());
        exchangeRate.setLocalDateTime(exchangeRateDto.getTimestamp().toLocalDateTime());

        Map<Currency, String> rates = exchangeRateDto
                .getRates()
                .entrySet()
                .stream()
                .map(this::map)
                .flatMap( x -> x.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        exchangeRate.setRates(rates);

        exchangeRateRepository.save(exchangeRate);

        System.out.println("Now is "+ LocalDateTime.now());
        System.out.println(currency);
    }

    public HashMap<Currency, String> map(Map.Entry<String, String> entry) {
        HashMap<Currency, String> map = new HashMap<>();

        Currency key = currencyRepository.findCurrencyByName(entry.getKey());

        map.put(key, entry.getValue());

        return map;
    }
}
