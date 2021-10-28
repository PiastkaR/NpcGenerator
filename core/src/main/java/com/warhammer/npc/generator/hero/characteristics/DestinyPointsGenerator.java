package com.warhammer.npc.generator.hero.characteristics;

import com.warhammer.npc.generator.mechanics.MechanicsUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class DestinyPointsGenerator {

    public int generateHumanDestinyPoints(int k10throw) {
        if (MechanicsUtils.isBetween(k10throw, 1, 4)) {
            return 2;
        }
        if (MechanicsUtils.isBetween(k10throw, 5, 10)) {
            return 3;
        }
        return 2;
    }

}
