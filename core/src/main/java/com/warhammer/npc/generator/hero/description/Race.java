package com.warhammer.npc.generator.hero.description;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Race {
    HUMAN(0, "Człowiek"),
    HALFLING(1, "Niziołek"),
    DWARF(2, "Krasnolud"),
    ELF(3, "Elf");

    private final int option;
    private final String description;

    @Override
    public String toString() {
        return "Race {" +
                "option=" + option + '\'' +
                ", description=" + description +
                '}';
    }

}
