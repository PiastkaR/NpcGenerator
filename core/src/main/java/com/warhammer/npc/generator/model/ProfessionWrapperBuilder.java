package com.warhammer.npc.generator.model;

import com.warhammer.npc.generator.hero.abilities.Ability;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.description.CharactersClass;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.skills.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfessionWrapperBuilder {

    private Long id;
    private String description;
    private CharactersClass charactersClass;
    private List<Race> racesProfession;
    private MainCharacteristics mainCharacteristicsDevelopment;
    private SecondaryCharacteristics secondaryCharacteristicsDevelopment;
    private List<Equipment> equipment;
    private List<Skill> skills;
    private List<Ability> abilities;

    public ProfessionWrapperBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProfessionWrapperBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProfessionWrapperBuilder withCharactersClass(CharactersClass charactersClass) {
        this.charactersClass = charactersClass;
        return this;
    }

    public ProfessionWrapperBuilder withRace(List<Race> racesProfession) {
        this.racesProfession = racesProfession;
        return this;
    }

    public ProfessionWrapperBuilder withMainCharacteristics(MainCharacteristics mainCharacteristics) {
        this.mainCharacteristicsDevelopment = mainCharacteristics;
        return this;
    }

    public ProfessionWrapperBuilder withSecondaryCharacteristics(SecondaryCharacteristics secondaryCharacteristics) {
        this.secondaryCharacteristicsDevelopment = secondaryCharacteristics;
        return this;
    }

    public ProfessionWrapperBuilder withEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
        return this;
    }

    public ProfessionWrapperBuilder withSkills(List<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public ProfessionWrapperBuilder withAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    public ProfessionWrapperBuilder buildWithSkillsAndAbilities() {
        return new ProfessionWrapperBuilder(
                id,
                description,
                charactersClass,
                racesProfession,
                mainCharacteristicsDevelopment,
                secondaryCharacteristicsDevelopment,
                equipment,
                skills,
                abilities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessionWrapperBuilder)) return false;
        ProfessionWrapperBuilder that = (ProfessionWrapperBuilder) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && charactersClass == that.charactersClass && Objects.equals(racesProfession, that.racesProfession) && Objects.equals(mainCharacteristicsDevelopment, that.mainCharacteristicsDevelopment) && Objects.equals(secondaryCharacteristicsDevelopment, that.secondaryCharacteristicsDevelopment) && Objects.equals(equipment, that.equipment) && Objects.equals(skills, that.skills) && Objects.equals(abilities, that.abilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, charactersClass, racesProfession, mainCharacteristicsDevelopment, secondaryCharacteristicsDevelopment, equipment, skills, abilities);
    }

    @Override
    public String toString() {
        return "ProfessionWrapperBuilder{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", charactersClass=" + charactersClass +
                ", racesProfession=" + racesProfession +
                ", mainCharacteristicsDevelopment=" + mainCharacteristicsDevelopment +
                ", secondaryCharacteristicsDevelopment=" + secondaryCharacteristicsDevelopment +
                ", equipment=" + equipment +
                ", skills=" + skills +
                ", abilities=" + abilities +
                '}';
    }
}
