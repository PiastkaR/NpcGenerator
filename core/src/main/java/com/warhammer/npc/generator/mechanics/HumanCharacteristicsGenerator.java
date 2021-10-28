package com.warhammer.npc.generator.mechanics;

import com.warhammer.npc.generator.hero.characteristics.DestinyPointsGenerator;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.VitalityGenerator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class HumanCharacteristicsGenerator {

    private final DiceThrowGenerator diceThrow;
    private final VitalityGenerator vitalityGenerator;
    private final DestinyPointsGenerator destinyPointsGenerator;

    public MainCharacteristics generateHumanMainCharacteristics() {
        return new MainCharacteristics(
                diceThrow.twoK10PlusTwenty(),
                diceThrow.twoK10PlusTwenty(),
                diceThrow.twoK10PlusTwenty(),
                diceThrow.twoK10PlusTwenty(),
                diceThrow.twoK10PlusTwenty(),
                diceThrow.twoK10PlusTwenty(),
                diceThrow.twoK10PlusTwenty(),
                diceThrow.twoK10PlusTwenty()
        );
    }

    public SecondaryCharacteristics generateHumanSecondaryCharacteristics(MainCharacteristics mainCharacteristics) {
        int strength = MechanicsUtils.getTensFromDigit(mainCharacteristics.getHardiness());
        int durability = MechanicsUtils.getTensFromDigit(mainCharacteristics.getVim());

        return new SecondaryCharacteristics(
                1,
                generateHumanVitality(diceThrow),
                strength,
                durability,
                4,
                0,
                0,
                generateHumanDestinyPoints(diceThrow)
                );
    }

    private int generateHumanVitality(DiceThrowGenerator diceThrow) {
        return vitalityGenerator.getHumanVitality(diceThrow.k10Throw());
    }

    private int generateHumanDestinyPoints(DiceThrowGenerator diceThrow) {
        return destinyPointsGenerator.generateHumanDestinyPoints(diceThrow.k10Throw());
    }

}
