package com.warhammer.npc.generator.hero.description;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Hair {

    BLACK(0, "Czarne"),
    BLONDE(1, "Blond");

    private final int option;
    private final String description;
}
