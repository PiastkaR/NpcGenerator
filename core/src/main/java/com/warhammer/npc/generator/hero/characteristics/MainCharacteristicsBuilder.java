package com.warhammer.npc.generator.hero.characteristics;

import lombok.Setter;

@Setter
public class MainCharacteristicsBuilder {
    private int combatSkills;
    private int archerySkills;
    private int vim;
    private int hardiness;
    private int agility;
    private int intelligence;
    private int willpower;
    private int polish;

    public MainCharacteristicsBuilder withCombatSkills(int combatSkills) {
        this.combatSkills = combatSkills;
        return this;
    }

    public MainCharacteristicsBuilder withArcherySkills(int archerySkills) {
        this.archerySkills = archerySkills;
        return this;
    }

    public MainCharacteristicsBuilder withVim(int vim) {
        this.vim = vim;
        return this;
    }

    public MainCharacteristicsBuilder withHardness(int hardiness) {
        this.hardiness = hardiness;
        return this;
    }

    public MainCharacteristicsBuilder withAgility(int agility) {
        this.agility = agility;
        return this;
    }

    public MainCharacteristicsBuilder withIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public MainCharacteristicsBuilder withWillpower(int willpower) {
        this.willpower = willpower;
        return this;
    }

    public MainCharacteristicsBuilder withPolish(int polish) {
        this.polish = polish;
        return this;
    }

    public MainCharacteristicsBuilder withMain(MainCharacteristics mainCharacteristics) {
        this.combatSkills = mainCharacteristics.getCombatSkills();
        this.archerySkills = mainCharacteristics.getArcherySkills();
        this.vim = mainCharacteristics.getVim();
        this.hardiness = mainCharacteristics.getHardiness();
        this.agility = mainCharacteristics.getAgility();
        this.intelligence = mainCharacteristics.getIntelligence();
        this.willpower = mainCharacteristics.getWillpower();
        this.polish = mainCharacteristics.getPolish();

        return this;
    }

    public MainCharacteristics build() {
        return new MainCharacteristics(
                combatSkills,
                archerySkills,
                vim,
                hardiness,
                agility,
                intelligence,
                willpower,
                polish
        );
    }

}

