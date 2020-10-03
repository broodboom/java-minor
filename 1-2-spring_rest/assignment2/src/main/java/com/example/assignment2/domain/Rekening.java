package com.example.assignment2.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class Rekening {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @NotNull
    private String iban;

    @Getter
    @Setter
    @Max(message = "Bedrag te hoog, wij bedienen geen rijke tata's", value = 20000)
    private double saldo;

    @Getter
    @Setter
    private boolean geblokkeerd;
}
