package com.warhammer.npc.generator.mechanics;

public class MechanicsUtils {

    public static int getTensFromDigit(int digit) {
        return (digit/10)%10;
    }

    public static boolean isBetween(int value, int min, int max) {
        return ((value >= min) && (value <= max));
    }
}
