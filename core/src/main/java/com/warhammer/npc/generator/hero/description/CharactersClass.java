package com.warhammer.npc.generator.hero.description;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CharactersClass {
    SCHOLARS(0, "Uczeni"),
    TOWNSPEOPLE(1, "Mieszczanie"),
    COURTIERS(2, "Dworzanie"),
    COMMON_PEOPLE(3, "Pospólstwo"),
    VAGABONDS(4, "Wędrowcy"),
    ROGUES(5, "Łotry"),
    WARRIORS(6, "Wojownicy"),
    SEA_WOLVES(7, "Wodniacy");

    private final int option;
    private final String description;

//    public static CharactersClass createFromInt(int option) {
//        return values()[option];
//    }
}
