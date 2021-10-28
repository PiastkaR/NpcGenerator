package com.warhammer.npc.generator.hero;

import com.warhammer.npc.generator.hero.description.*;
import com.warhammer.npc.generator.model.Name;
import com.warhammer.npc.generator.professions.Profession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class HeroDescription {

    private Name name;
    private Race race;
    private Profession actualProfession;
    private Profession lastProfession;
    private int age;
    private Gender gender;
    private Eye eyesColour;
    private int weight;
    private Hair hairColour;
    int height;
    private StarSign starSign;
    private String siblings;
    private String birthPlace;
    private String specialFeatures;
}
