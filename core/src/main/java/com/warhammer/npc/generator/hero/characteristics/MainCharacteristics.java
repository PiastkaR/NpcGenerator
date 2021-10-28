package com.warhammer.npc.generator.hero.characteristics;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MainCharacteristics {

    private final int combatSkills;
    private final int archerySkills;
    private final int vim;
    private final int hardiness;
    private final int agility;
    private final int intelligence;
    private final int willpower;
    private final int polish;
}
