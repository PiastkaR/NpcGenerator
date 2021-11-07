package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.model.FullName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterCreatorService {

    private final NameService nameService;

    public FullName getFullName(String userRace, String userGender) {
        return nameService.generateName(Race.valueOf(userRace), Gender.valueOf(userGender));
    }
}
