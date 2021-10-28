package com.warhammer.npc.generator.hero.description;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Eye {

    BLUE(0, "Niebieskie"),
    GREEN(1, "Zielone"),
    BROWN(2, "Brązowe"),
    RED(3, "Czerwone"),
    VIOLET(4, "Filoletowe"),
    SILVER(5, "Srebrne"),
    BLACK(6, "Czarne");

    private final int option;
    private final String description;
}
