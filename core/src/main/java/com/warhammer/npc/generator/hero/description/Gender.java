package com.warhammer.npc.generator.hero.description;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender {
    MALE(0, "Mężczyzna"),
    FEMALE(1, "Kobieta"),
    BOTH(2, "1 Człon Imienia");

    private final int option;
    private final String description;

    @Override
    public String toString() {
        return "Gender {" +
                " option=" + option +
                ", description=" + description +
                "}";
    }

}
