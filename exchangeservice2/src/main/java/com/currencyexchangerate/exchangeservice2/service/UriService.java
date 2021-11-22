package com.currencyexchangerate.exchangeservice2.service;

import com.currencyexchangerate.exchangeservice2.config.ExchangeRatesApiConfig;
import lombok.AllArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UriService {

    private ExchangeRatesApiConfig exchangeRatesApiConfig;

    public URIBuilder getBuilder() {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost(exchangeRatesApiConfig.getUrlLatest())
                .addParameter("access_key", exchangeRatesApiConfig.getAccessKey());
        return builder;
    }

    public String getBuilder(String symbols) {
        return getBuilder()
                .toString().concat("&symbols="+symbols);
    }

}
