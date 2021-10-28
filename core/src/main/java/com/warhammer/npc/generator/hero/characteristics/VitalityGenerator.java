package com.warhammer.npc.generator.hero.characteristics;

import com.warhammer.npc.generator.mechanics.MechanicsUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class VitalityGenerator {

    public int getHumanVitality(int k10throw) {
        if (MechanicsUtils.isBetween(k10throw, 1, 3)) {
            return 10;
        }
        if (MechanicsUtils.isBetween(k10throw, 4, 7)) {
            return 11;
        }

        if (MechanicsUtils.isBetween(k10throw, 7, 9)) {
            return 12;
        }

        if (MechanicsUtils.isBetween(k10throw, 10, 10)) {
            return 13;
        }
        return 10;
    }

}
