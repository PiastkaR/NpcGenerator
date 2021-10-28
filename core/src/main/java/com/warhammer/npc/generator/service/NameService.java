package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.NameRepository;
import com.warhammer.npc.generator.model.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class NameService {

    private final NameRepository nameRepository;

    public Name generateName(Race race, Gender gender) {
        Iterable<Name> allNames = nameRepository.findAll();
    }

    private static List<Name> getRaceNames(Iterable<Name> allNames) {
        Stream.of(allNames).filter()

    }

    private static boolean isGivenGender(Name name, Gender gender) {
        return gender.equals(name.getGender());
    }

    private static boolean isGivenRace(Name name, Race race) {
        return race.equals(race.getGender());
    }

}
