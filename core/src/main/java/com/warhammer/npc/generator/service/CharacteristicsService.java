package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.mechanics.CharacteristicsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacteristicsService {

    private final CharacteristicsGenerator characteristicsGenerator;

    public MainCharacteristics getMainCharacteristics(Race race) {
        if (race == Race.ELF)
            return characteristicsGenerator.generateElfMainCharacteristics();
        if (race == Race.DWARF)
            return characteristicsGenerator.generateDwarfMainCharacteristics();
        if (race == Race.HALFLING)
            return characteristicsGenerator.generateHalflingMainCharacteristics();
        return characteristicsGenerator.generateHumanMainCharacteristics();
    }

    public SecondaryCharacteristics getSecondaryCharacteristics(Race race, MainCharacteristics mainCharacteristics) {
        if (race == Race.ELF)
            return characteristicsGenerator.generateElfSecondaryCharacteristics(mainCharacteristics);
        if (race == Race.DWARF)
            return characteristicsGenerator.generateDwarfSecondaryCharacteristics(mainCharacteristics);
        if (race == Race.HALFLING)
            return characteristicsGenerator.generateHalflingSecondaryCharacteristics(mainCharacteristics);
        return characteristicsGenerator.generateHumanSecondaryCharacteristics(mainCharacteristics);
    }

    private MainCharacteristics shallyiasBlessing() {
        return null;
    }

}
