package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.NameRepository;
import com.warhammer.npc.generator.hero.repository.NicknameRepository;
import com.warhammer.npc.generator.model.FullName;
import com.warhammer.npc.generator.model.Name;
import com.warhammer.npc.generator.model.Nickname;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class NameService {

    private final NameRepository nameRepository;
    private final NicknameRepository nicknameRepository;

    public FullName generateName(Race race, Gender gender) {
        Iterable<Name> allNames = nameRepository.findAll();
        Iterable<Nickname> allNicknames = nicknameRepository.findAll();

        List<Name> filteredNames = getNamesByUserChoice(allNames, gender, race);
        List<Nickname> filteredNicknames = getNicknamesByUserChoice(allNicknames, race);

        int drawName = drawFromFiltered(filteredNames);
        int drawNickname = drawFromFiltered(filteredNicknames);

        Name name = filteredNames.get(drawName);
        Nickname nickname = filteredNicknames.get(drawNickname);

        return new FullName(name, nickname, null);
    }

    private <T> int drawFromFiltered(List<T> filteredList) {
        int size = filteredList.size();
        return new Random().nextInt(size);
    }

    private static List<Nickname> getNicknamesByUserChoice(Iterable<Nickname> allNicknames, Race race) {
        return StreamSupport.stream(allNicknames.spliterator(), false)
                .filter(name -> isGivenRace(name, race))
                .collect(Collectors.toList());
    }

    private static List<Name> getNamesByUserChoice(Iterable<Name> allNames, Gender gender, Race race) {
        return StreamSupport.stream(allNames.spliterator(), false)
                .filter(name -> isGivenGender(name, gender))
                .filter(name -> isGivenRace(name, race))
                .collect(Collectors.toList());
    }

    private static boolean isGivenGender(Name name, Gender gender) {
        return gender.getOption() == name.getGender().getOption();
    }

    private static boolean isGivenRace(Name name, Race race) {
        return race == name.getRace();
    }

    private static boolean isGivenRace(Nickname nickname, Race race) {
        return race == nickname.getRaceNickname();
    }

}
