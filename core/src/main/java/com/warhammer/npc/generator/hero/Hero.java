package com.warhammer.npc.generator.hero;

import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Hero {

    private final HeroDescription description;
    private final MainCharacteristics mainCharacteristics;
    private final SecondaryCharacteristics secondaryCharacteristics;

}
