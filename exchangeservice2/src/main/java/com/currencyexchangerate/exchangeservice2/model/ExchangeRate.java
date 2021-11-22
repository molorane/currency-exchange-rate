package com.currencyexchangerate.exchangeservice2.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;


@Data
@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean success;
    private Timestamp timestamp;
    private String base;
    private LocalDate date;

}
