package com.warhammer.npc.generator.hero;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.model.FullName;
import com.warhammer.npc.generator.professions.Profession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class HeroDescription {

    private FullName name;
    private Race race;
    private Profession actualProfession;
    private Profession lastProfession;
    private int age;
    private Gender gender;
    private String eyesColour;
    private int weight;
    private String hairColour;
    int height;
    private String starSign;
    private int siblings;
    private String birthPlace;
    private String specialFeatures;

}
