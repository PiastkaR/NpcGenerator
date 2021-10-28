package com.warhammer.npc.generator.hero.characteristics;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SecondaryCharacteristics {

    private final int attacks;
    private final int vitality;
    private final int strength;
    private final int durability;
    private final int speed;
    private final int magic;
    private final int insanityPoints;
    private final int destinyPoints;

}
