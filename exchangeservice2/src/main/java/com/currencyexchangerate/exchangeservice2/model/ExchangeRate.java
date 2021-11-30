package com.currencyexchangerate.exchangeservice2.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;


@Data
@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean success;

    @Column(name = "timestamp")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "base_currency_id", nullable = false)
    private Currency base;

    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "rates")
    @MapKeyColumn(name = "xrate_id")
    @Column(name = "currency_value")
    private Map<Currency, String> rates;

    @PrePersist
    public void prePersist(){
        localDateTime = LocalDateTime.now();
    }
}
