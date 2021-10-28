package com.warhammer.npc.generator.hero.description;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StarSign {
    STAR_SIGN1(0, "Znak1"),
    STAR_SIGN2(1, "Znak2");

    private final int option;
    private final String description;
}
