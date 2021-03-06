package com.currencyexchangerate.exchangeservice2.config;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfig {

    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    public URIBuilder uriBuilder(){
        return new URIBuilder();
    }
}
