package com.currencyexchangerate.exchangeservice1.service;

import com.currencyexchangerate.exchangeservice1.config.ExchangeRatesApiConfig;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UriService {

    @Autowired
    private ExchangeRatesApiConfig exchangeRatesApiConfig;

    private URIBuilder builder = new URIBuilder();

    public URIBuilder getBuilder() {
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
