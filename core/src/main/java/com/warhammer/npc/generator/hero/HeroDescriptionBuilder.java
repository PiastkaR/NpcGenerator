package com.warhammer.npc.generator.hero;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.model.FullName;
import com.warhammer.npc.generator.professions.Profession;
import lombok.Getter;

@Getter
public class HeroDescriptionBuilder {
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

    public HeroDescriptionBuilder withName(FullName name) {
        this.name = name;
        return this;
    }

    public HeroDescriptionBuilder withRace(Race race) {
        this.race = race;
        return this;
    }

    public HeroDescriptionBuilder withActualProfession(Profession actualProfession) {
        this.actualProfession = actualProfession;
        return this;
    }

    public HeroDescriptionBuilder withLastProfession(Profession lastProfession) {
        this.lastProfession = lastProfession;
        return this;
    }

    public HeroDescriptionBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public HeroDescriptionBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public HeroDescriptionBuilder withEyesColour(String eyesColour) {
        this.eyesColour = eyesColour;
        return this;
    }

    public HeroDescriptionBuilder withWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public HeroDescriptionBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public HeroDescriptionBuilder withHairColour(String hairColour) {
        this.hairColour = hairColour;
        return this;
    }

    public HeroDescriptionBuilder withStarSign(String starSign) {
        this.starSign = starSign;
        return this;
    }

    public HeroDescriptionBuilder withSiblings(int siblings) {
        this.siblings = siblings;
        return this;
    }

    public HeroDescriptionBuilder withBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public HeroDescriptionBuilder withSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
        return this;
    }

    public HeroDescription build() {
        return new HeroDescription(
                name,
                race,
                actualProfession,
                lastProfession,
                age,
                gender,
                eyesColour,
                weight,
                hairColour,
                height,
                starSign,
                siblings,
                birthPlace,
                specialFeatures
        );
    }

    public HeroDescriptionBuilder withHeroDescription(HeroDescription heroDescription) {
        this.name = heroDescription.getName();
        this.race = heroDescription.getRace();
        this.actualProfession = heroDescription.getActualProfession();
        this.lastProfession = heroDescription.getLastProfession();
        this.age = heroDescription.getAge();
        this.gender = heroDescription.getGender();
        this.eyesColour = heroDescription.getEyesColour();
        this.weight = heroDescription.getWeight();
        this.hairColour = heroDescription.getHairColour();
        this.starSign = heroDescription.getStarSign();
        this.siblings = heroDescription.getSiblings();
        this.birthPlace = heroDescription.getBirthPlace();
        this.specialFeatures = heroDescription.getSpecialFeatures();

        return this;
    }

}
