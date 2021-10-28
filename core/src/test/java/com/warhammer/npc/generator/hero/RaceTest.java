package com.warhammer.npc.generator.hero;

import com.warhammer.npc.generator.hero.description.Race;
import org.junit.jupiter.api.Test;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class RaceTest {

    @Test
    void isRaceDescriptionCorrect() {
        // Arrange & Act
        Race human = Race.HUMAN;

        //Assert
        assertEquals("Człowiek", valueOf(human));
        assertEquals("Człowiek", valueOf(human));
        assertEquals("Człowiek", valueOf(human));
    }

}