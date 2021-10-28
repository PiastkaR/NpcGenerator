package com.warhammer.npc.generator.mechanics;

import com.warhammer.npc.generator.hero.characteristics.DestinyPointsGenerator;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.VitalityGenerator;
import com.warhammer.npc.generator.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HumanCharacteristicsGeneratorTest {

    @Mock
    DiceThrowGenerator diceThrowGenerator;

    @Mock
    VitalityGenerator vitalityGenerator;

    @Mock
    DestinyPointsGenerator destinyPointsGenerator;

    @InjectMocks
    private HumanCharacteristicsGenerator humanCharacteristicsGenerator;

    @Test
    void shouldGenerateHumanMainCharacteristics() {
        //Arrange
        when(diceThrowGenerator.twoK10PlusTwenty()).thenReturn(2 * new Random().nextInt(10) + 21);

        //Act
        MainCharacteristics mainCharacteristics = humanCharacteristicsGenerator.generateHumanMainCharacteristics();

        //Assert
        TestUtils.isBetween(mainCharacteristics.getCombatSkills(), 22, 40);
    }

    @Test
    void shouldGenerateHumanSecondaryCharacteristics() {
        //Arrange
        when(diceThrowGenerator.twoK10PlusTwenty()).thenReturn(2 * new Random().nextInt(10) + 21);
        MainCharacteristics mainCharacteristics = humanCharacteristicsGenerator.generateHumanMainCharacteristics();
        when(vitalityGenerator.getHumanVitality(anyInt())).thenReturn(10);
        when(destinyPointsGenerator.generateHumanDestinyPoints(anyInt())).thenReturn(2);

        //Act
        SecondaryCharacteristics secondaryCharacteristics = humanCharacteristicsGenerator.generateHumanSecondaryCharacteristics(mainCharacteristics);

        //Assert
        assertEquals(1, secondaryCharacteristics.getAttacks());
        assertTrue(TestUtils.isBetween(secondaryCharacteristics.getDurability(), 2, 4));
        assertEquals(2, secondaryCharacteristics.getDestinyPoints());
        assertEquals(0, secondaryCharacteristics.getMagic());
        assertTrue(TestUtils.isBetween(secondaryCharacteristics.getStrength(), 2, 4));
        assertEquals(10, secondaryCharacteristics.getVitality());
        assertEquals(0, secondaryCharacteristics.getInsanityPoints());
        assertEquals(4, secondaryCharacteristics.getSpeed());
    }

}