package com.warhammer.npc.generator.mechanics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceThrowGeneratorTest {

    @Test
    void isValueRangeFrom1to10() {
        // Arrange
        List<Integer> results = new ArrayList<>();

        //Act
        for (int i = 0; i < 20; i++) {
            int diceThrow = new DiceThrowGenerator().k10Throw();
            results.add(diceThrow);
        }

        //Assert
        assertTrue(!results.isEmpty());
        assertTrue(results.size() == 20);
        assertTrue(!results.contains(11));
        assertTrue(!results.contains(0));
    }

}