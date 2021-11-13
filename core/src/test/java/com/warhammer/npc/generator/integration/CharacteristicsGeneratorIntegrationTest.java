package com.warhammer.npc.generator.integration;

import com.warhammer.npc.generator.hero.characteristics.DestinyPointsGenerator;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.VitalityGenerator;
import com.warhammer.npc.generator.mechanics.DiceThrowGenerator;
import com.warhammer.npc.generator.mechanics.CharacteristicsGenerator;
import com.warhammer.npc.generator.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CharacteristicsGeneratorIntegrationTest {

    @Autowired
    private DiceThrowGenerator diceThrow;

    @Autowired
    private VitalityGenerator vitalityGenerator;

    @Autowired
    private DestinyPointsGenerator destinyPointsGenerator;

    @Test
    public void shouldCreateMainCharacteristics() {
        //Arrange
        List<Integer> skills = new ArrayList<>();
        CharacteristicsGenerator human = new CharacteristicsGenerator(diceThrow, vitalityGenerator, destinyPointsGenerator);

        //Act
        MainCharacteristics mainCharacteristics = human.generateHumanMainCharacteristics();
        skills.add(mainCharacteristics.getVim());
        skills.add(mainCharacteristics.getHardiness());
        skills.add(mainCharacteristics.getArcherySkills());
        skills.add(mainCharacteristics.getIntelligence());
        skills.add(mainCharacteristics.getPolish());
        skills.add(mainCharacteristics.getWillpower());
        skills.add(mainCharacteristics.getCombatSkills());
        skills.add(mainCharacteristics.getAgility());

        //Assert
        skills.forEach(skill -> TestUtils.isBetween(skill, 22, 40));
    }

    @Test
    public void shouldCreateSecondaryCharacteristics() {
        //Arrange
        CharacteristicsGenerator human = new CharacteristicsGenerator(diceThrow, vitalityGenerator, destinyPointsGenerator);
        MainCharacteristics mainCharacteristics = human.generateHumanMainCharacteristics();

        //Act
        SecondaryCharacteristics secondaryCharacteristics = human.generateHumanSecondaryCharacteristics(mainCharacteristics);

        //Assert
        assertEquals(1, secondaryCharacteristics.getAttacks());
        assertTrue(TestUtils.isBetween(secondaryCharacteristics.getDurability(), 2, 4));
        assertTrue(TestUtils.isBetween(secondaryCharacteristics.getDestinyPoints(), 2, 3));
        assertEquals(0, secondaryCharacteristics.getInsanityPoints());
        assertEquals(0, secondaryCharacteristics.getMagic());
        assertTrue(TestUtils.isBetween(secondaryCharacteristics.getVitality(), 10, 13));
        assertTrue(TestUtils.isBetween(secondaryCharacteristics.getStrength(), 2, 4));
        assertEquals(secondaryCharacteristics.getSpeed(), 4);
    }

}
