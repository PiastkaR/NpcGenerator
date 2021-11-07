package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.mechanics.HumanCharacteristicsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacteristicsService {

    private final HumanCharacteristicsGenerator humanCharacteristicsGenerator;
}
