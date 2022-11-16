package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.Hero;
import com.warhammer.npc.generator.hero.HeroBuilder;
import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.abilities.Ability;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristicsBuilder;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.description.CharactersClass;
import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterCreatorService {

    private final CharacterDescriptionService descriptionService;
    private final CharacteristicsService characteristicsService;

    public HeroDescription getHeroDescription(String userRace, String userGender, String userClass) {
        return descriptionService.generateHeroDescription(Race.valueOf(userRace), Gender.valueOf(userGender), CharactersClass.valueOf(userClass));
    }

    public Hero getHero(String userRace, String userGender, String userClass) {
        HeroDescription heroDescription = getHeroDescription(userRace, userGender, userClass);
        MainCharacteristics mainCharacteristics = characteristicsService.getMainCharacteristics(Race.valueOf(userRace));
        SecondaryCharacteristics secondaryCharacteristics = characteristicsService.getSecondaryCharacteristics(Race.valueOf(userRace), mainCharacteristics);
        MainCharacteristics improvedMainCharacteristics = resolveCharChangeFromAbilities(mainCharacteristics, heroDescription.getAbilities());

        return new HeroBuilder()
                .withHeroDescription(heroDescription)
                .withMainCharacteristics(improvedMainCharacteristics)
                .withSecondaryCharacteristics(secondaryCharacteristics)
                .build();
    }


    private MainCharacteristics resolveCharChangeFromAbilities(MainCharacteristics mainCharacteristics, List<Ability> abilities) {
        MainCharacteristics postBlessingMains = shallyasBlessing(mainCharacteristics);
        MainCharacteristicsBuilder mainCharacteristicsBuilder = new MainCharacteristicsBuilder();
        mainCharacteristicsBuilder.withMain(postBlessingMains);

        for (Ability ability : abilities) {
            String description = ability.getDescription();
            if (description.equals("+5sw")) {
                mainCharacteristicsBuilder.setWillpower(mainCharacteristics.getWillpower() + 5);
            }
            if (description.equals("+5us")) {
                mainCharacteristicsBuilder.setArcherySkills(mainCharacteristics.getArcherySkills() + 5);
            }
            if (description.equals("+5ww")) {
                mainCharacteristicsBuilder.setCombatSkills(mainCharacteristics.getCombatSkills() + 5);
            }
            if (description.equals("+5zr")) {
                mainCharacteristicsBuilder.setAgility(mainCharacteristics.getAgility() + 5);
            }
            if (description.equals("+5k")) {
                mainCharacteristicsBuilder.setVim(mainCharacteristics.getVim() + 5);
            }
            if (description.equals("+5odp")) {
                mainCharacteristicsBuilder.withMain(postBlessingMains);
                mainCharacteristicsBuilder.setHardiness(mainCharacteristics.getHardiness() + 5);
            }
            if (description.equals("+5int")) {
                mainCharacteristicsBuilder.setIntelligence(mainCharacteristics.getIntelligence() + 5);
            }
            if (description.equals("+5ogd")) {
                mainCharacteristicsBuilder.setPolish(mainCharacteristics.getPolish() + 5);
            }
        }

        return mainCharacteristicsBuilder.build();
    }

    private MainCharacteristics shallyasBlessing(MainCharacteristics mainCharacteristics) {
        List<Integer> characteristics = new ArrayList<>();
        characteristics.add(mainCharacteristics.getCombatSkills());
        characteristics.add(mainCharacteristics.getArcherySkills());
        characteristics.add(mainCharacteristics.getVim());
        characteristics.add(mainCharacteristics.getHardiness());
        characteristics.add(mainCharacteristics.getAgility());
        characteristics.add(mainCharacteristics.getIntelligence());
        characteristics.add(mainCharacteristics.getWillpower());
        characteristics.add(mainCharacteristics.getPolish());

        Integer min = Collections.min(characteristics);
        if (min < 31) {
            int index = characteristics.indexOf(min);
            characteristics.set(index, 31);
        }

        return new MainCharacteristicsBuilder()
                .withCombatSkills(mainCharacteristics.getCombatSkills())
                .withArcherySkills(mainCharacteristics.getArcherySkills())
                .withVim(mainCharacteristics.getVim())
                .withHardness(mainCharacteristics.getHardiness())
                .withAgility(mainCharacteristics.getAgility())
                .withIntelligence(mainCharacteristics.getIntelligence())
                .withPolish(mainCharacteristics.getPolish())
                .withWillpower(mainCharacteristics.getWillpower())
                .build();
    }

}
