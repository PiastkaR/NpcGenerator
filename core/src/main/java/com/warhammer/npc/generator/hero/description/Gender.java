package com.warhammer.npc.generator.hero.description;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE(0, "Mężczyzna"),
    FEMALE(1, "Kobieta");

    private final int option;
    private final String description;

    @Override
    public String toString() {
        return '\n' + "Name{" +
                ", option=" + option +
                ", description=" + description +
                "}";
    }

}
