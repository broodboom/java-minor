package com.example.assignment2.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public class Rekeninghouder {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @NotNull
    @Size(message = "Minimaal 1 karakter, maximaal 50", min = 1, max = 50)
    private String voornaam;

    @Getter
    @Setter
    @Size(message = "Minimaal 1 karakter, maximaal 50", min = 1, max = 50)
    private String achternaam;
}
