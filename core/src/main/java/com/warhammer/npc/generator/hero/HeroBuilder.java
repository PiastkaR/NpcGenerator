package com.warhammer.npc.generator.hero;

import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import lombok.Getter;

@Getter
public class HeroBuilder {

    private HeroDescription heroDescription;
    private MainCharacteristics mainCharacteristics;
    private SecondaryCharacteristics secondaryCharacteristics;

    public HeroBuilder withHeroDescription (HeroDescription heroDescription) {
        this.heroDescription = heroDescription;
        return this;
    }

    public HeroBuilder withMainCharacteristics (MainCharacteristics mainCharacteristics) {
        this.mainCharacteristics = mainCharacteristics;
        return this;
    }

    public HeroBuilder withSecondaryCharacteristics (SecondaryCharacteristics secondaryCharacteristics) {
        this.secondaryCharacteristics = secondaryCharacteristics;
        return this;
    }

    public Hero build() {
        return new Hero(heroDescription, mainCharacteristics, secondaryCharacteristics);
    }

    public HeroBuilder withHeroBuilder (Hero hero) {
        this.heroDescription = hero.getDescription();
        this.mainCharacteristics = hero.getMainCharacteristics();
        this.secondaryCharacteristics = hero.getSecondaryCharacteristics();
        return this;
    }

}
