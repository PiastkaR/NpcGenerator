package com.warhammer.npc.generator.professions;

import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Profession {

    private final String description;
    private final MainCharacteristics mainCharacteristics;
    private final SecondaryCharacteristics secondaryCharacteristics;
}
