package com.warhammer.npc.generator.hero.description;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CharactersClass {
    SCHOLAR(0, "Uczeni"),
    COMMONER(1, "Mieszczanie"),
    RANGER(2, "Wędrowcy"),
    CRIMINAL(3, "Łotry"),
    WARRIOR(4, "Wojownicy");

    private final int option;
    private final String description;

//    public static CharactersClass createFromInt(int option) {
//        return values()[option];
//    }
}
