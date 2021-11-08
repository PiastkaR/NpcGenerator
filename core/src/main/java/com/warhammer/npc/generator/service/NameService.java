package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.Name2ndPartRepository;
import com.warhammer.npc.generator.hero.repository.NameConnectorRepository;
import com.warhammer.npc.generator.hero.repository.NameRepository;
import com.warhammer.npc.generator.hero.repository.NicknameRepository;
import com.warhammer.npc.generator.mechanics.MechanicsUtils;
import com.warhammer.npc.generator.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class NameService {
    private static final String DVARF_MALE_NICKNAME_END = "son";
    private static final String DVARF_FEMALE_NICKNAME_END = "dotr";

    private final NameRepository nameRepository;
    private final NicknameRepository nicknameRepository;
    private final Name2ndPartRepository name2ndPartRepository;
    private final NameConnectorRepository nameConnectorRepository;

    public FullName generateName(Race race, Gender gender) {
        Iterable<Name> allNames = nameRepository.findAll();
        Iterable<Nickname> allNicknames = nicknameRepository.findAll();
        Iterable<Name2ndPart> secondPartOfName = name2ndPartRepository.findAll();

        List<Name> filteredNames = getNamesByUserChoice(allNames, gender, race);
        List<Nickname> filteredNicknames = getNicknamesByUserChoice(allNicknames, race);
        List<Name2ndPart> secondNamePartsForNonHuman = getSecondNamePartsForNonHuman(secondPartOfName, gender, race);

        int drawName = MechanicsUtils.drawFromFiltered(filteredNames);
        int drawNickname = MechanicsUtils.drawFromFiltered(filteredNicknames);

        Name name = filteredNames.get(drawName);
        Nickname nickname = filteredNicknames.get(drawNickname);
        FullName fullName = new FullName(name.getName(), nickname.getNickname(), null);

        if (race != Race.HUMAN) {
            int drawSecondNamePart = MechanicsUtils.drawFromFiltered(secondNamePartsForNonHuman);
            Name2ndPart name2ndPart = secondNamePartsForNonHuman.get(drawSecondNamePart);
            fullName = createNonHumanName(name, nickname, name2ndPart, race, gender);
        }

        return fullName;
    }

    private FullName createNonHumanName(Name name, Nickname nickname, Name2ndPart name2ndPart, Race race, Gender gender) {
        String nonHumanName = name.getName() + name2ndPart.getName2ndPart().toLowerCase(Locale.ROOT);
        FullName fullName = new FullName(nonHumanName, nickname.getNickname(), null);

        if (race == Race.ELF) {
            fullName = getElvishName(nonHumanName, nickname.getNickname());
        }

        if (race == Race.DWARF) {
            fullName = generateNicknameForDwarf(nonHumanName, race, gender);
        }

        return fullName;
    }

    private FullName generateNicknameForDwarf(String name, Race race, Gender gender) {
        String finalAncestorName = "";

        Iterable<Name> allNames = nameRepository.findAll();
        Iterable<Name2ndPart> secondPartOfName = name2ndPartRepository.findAll();
        List<Name> filteredAncestorsNames = getNamesByUserChoice(allNames, gender, race);
        List<Name2ndPart> secondNamePartsAncestors = getSecondNamePartsForNonHuman(secondPartOfName, gender, race);

        int drawNickname = MechanicsUtils.drawFromFiltered(filteredAncestorsNames);
        int drawSecondPart = MechanicsUtils.drawFromFiltered(secondNamePartsAncestors);

        Name ancestorsName = filteredAncestorsNames.get(drawNickname);
        Name ancestorsNamePartTwo = filteredAncestorsNames.get(drawSecondPart);
        String namesEnding = ancestorsNamePartTwo.getName().toLowerCase(Locale.ROOT);

        if (gender == Gender.MALE) {
            finalAncestorName = ancestorsName.getName() + namesEnding + DVARF_MALE_NICKNAME_END;
        } else {
            finalAncestorName = ancestorsName.getName() + namesEnding + DVARF_FEMALE_NICKNAME_END;
        }

        return new FullName(name, finalAncestorName, null);
    }

    private FullName getElvishName(String name, String nickname) {
        Iterable<NameConnector> elfConnector = nameConnectorRepository.findAll();
        List<NameConnector> connectors = StreamSupport.stream(elfConnector.spliterator(), false).collect(Collectors.toList());

        int drawConnector = MechanicsUtils.drawFromFiltered(connectors);
        NameConnector connector = connectors.get(drawConnector);

        return new FullName(name + "-" + connector.getConnector(), nickname, connector.getConnector());
    }

    private static List<Name2ndPart> getSecondNamePartsForNonHuman(Iterable<Name2ndPart> allNames, Gender gender, Race race) {
        return MechanicsUtils.filterData(allNames, name -> isGivenGender(name, gender), name -> isGivenRace(name, race));
    }

    private static List<Nickname> getNicknamesByUserChoice(Iterable<Nickname> allNicknames, Race race) {
        return StreamSupport.stream(allNicknames.spliterator(), false)
                .filter(name -> isGivenRace(name, race))
                .collect(Collectors.toList());
    }

    private static List<Name> getNamesByUserChoice(Iterable<Name> allNames, Gender gender, Race race) {
        if (race == Race.HUMAN) {
            return MechanicsUtils.filterData(allNames, name -> isGivenGender(name, gender), name -> name.getRace() == Race.HUMAN);
        } else {
            return MechanicsUtils.filterData(allNames, name -> isGivenGender(name, Gender.BOTH), name -> isGivenRace(name, race));
        }
    }

    private static boolean isGivenGender(Name name, Gender gender) {
        return gender.getOption() == name.getGender().getOption();
    }

    private static boolean isGivenRace(Name name, Race race) {
        return race == name.getRace();
    }

    private static boolean isGivenGender(Name2ndPart name2ndPart, Gender gender) {
        return gender.getOption() == name2ndPart.getGender().getOption();
    }

    private static boolean isGivenRace(Name2ndPart name2ndPart, Race race) {
        return race == name2ndPart.getRace();
    }

    private static boolean isGivenRace(Nickname nickname, Race race) {
        return race == nickname.getRaceNickname();
    }

}
