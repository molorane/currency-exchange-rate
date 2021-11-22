package com.currencyexchangerate.exchangeservice1.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ExchangeRatesApiConfig {

    @Value("${service.access_key}")
    private String accessKey;
    @Value("${service.url.latest}")
    private String urlLatest;
    @Value("${service.url.historical}")
    private String urlHistorical;
    @Value("${service.url.convert}")
    private String urlConvert;
}
