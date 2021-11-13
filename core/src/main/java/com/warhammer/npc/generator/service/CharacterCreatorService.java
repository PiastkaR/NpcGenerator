package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.Hero;
import com.warhammer.npc.generator.hero.HeroBuilder;
import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterCreatorService {

    private final NameService nameService;
    private final CharacterDescriptionService descriptionService;
    private final CharacteristicsService characteristicsService;

//    public FullName getFullName(String userRace, String userGender) {
//        return nameService.generateName(Race.valueOf(userRace), Gender.valueOf(userGender));
//    }

    public HeroDescription getHeroDescription(String userRace, String userGender) {
        return descriptionService.generateHeroDescription(Race.valueOf(userRace), Gender.valueOf(userGender));
    }

    public Hero getHero(String userRace, String userGender) {
        MainCharacteristics mainCharacteristics = characteristicsService.getMainCharacteristics(Race.valueOf(userRace));
        SecondaryCharacteristics secondaryCharacteristics = characteristicsService.getSecondaryCharacteristics(Race.valueOf(userRace), mainCharacteristics);

        return new HeroBuilder()
                .withHeroDescription(getHeroDescription(userRace, userGender))
                .withMainCharacteristics(mainCharacteristics)
                .withSecondaryCharacteristics(secondaryCharacteristics)
                .build();
    }

}
