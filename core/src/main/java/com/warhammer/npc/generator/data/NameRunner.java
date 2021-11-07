package com.warhammer.npc.generator.data;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.NameConnectorRepository;
import com.warhammer.npc.generator.hero.repository.NameRepository;
import com.warhammer.npc.generator.hero.repository.NicknameRepository;
import com.warhammer.npc.generator.model.Name;
import com.warhammer.npc.generator.model.NameConnector;
import com.warhammer.npc.generator.model.Nickname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class NameRunner implements CommandLineRunner {

    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private NicknameRepository nicknameRepository;


    @Autowired
    private NameConnectorRepository nameConnectorRepository;

    @Override
    public void run(String... args) throws Exception {
//TODO wywalilem 2gi czlon krasnoludow trzeba dac je na nickname!

//        Name johan = new Name(1L, "Johan", new Nickname(1L, "Albreht"), null, Gender.MALE, Race.HUMAN);
//        Name adam = new Name(2, "Adam", new Nickname(2, "Zawadzki"), null, Gender.MALE, Race.HUMAN);
//        Name ewa = new Name(3, "Ewa", new Nickname(3, "Sikora"), null, Gender.FEMALE, Race.HUMAN);
//        List<Name> names = Arrays.asList(johan, adam, ewa);
//        nameRepository.saveAll(names);
//        Iterable<Name> all = nameRepository.findAll();
//        System.out.println("All male names: " + all);
//        nameRepository.deleteAll(all);

        Iterable<Name> allNames = nameRepository.findAll();
        Iterable<Nickname> allNicknames = nicknameRepository.findAll();
        Iterable<NameConnector> allConnectors = nameConnectorRepository.findAll();

        List<Nickname> humanNicknames = filterData(allNicknames, nickname -> isHuman(nickname.getRaceNickname()));
        List<Nickname> hlaflingNicknames = filterData(allNicknames, nickname -> isHalfing(nickname.getRaceNickname()));
        List<Nickname> dwarfNicknames = filterData(allNicknames, nickname -> isDwarf(nickname.getRaceNickname()));
        List<Nickname> elfNicknames = filterData(allNicknames, nickname -> isElf(nickname.getRaceNickname()));

        List<Name> humanMaleNames = filterData(allNames, race -> isHuman(race.getRace())).stream().filter(name -> isMale(name.getGender())).collect(Collectors.toList());
        List<Name> humanFemaleNames = filterData(allNames, race -> isHuman(race.getRace())).stream().filter(name -> isFemale(name.getGender())).collect(Collectors.toList());

        List<Name> halflingMaleNames = filterData(allNames, race -> isHalfing(race.getRace())).stream().filter(name -> isMale(name.getGender())).collect(Collectors.toList());
        List<Name> halflingFemaleNames = filterData(allNames, race -> isHalfing(race.getRace())).stream().filter(name -> isFemale(name.getGender())).collect(Collectors.toList());
        List<Name> halflingBothNames = filterData(allNames, race -> isHalfing(race.getRace())).stream().filter(name -> isBoth(name.getGender())).collect(Collectors.toList());

        List<Name> dwarfMaleNames = filterData(allNames, race -> isDwarf(race.getRace())).stream().filter(name -> isMale(name.getGender())).collect(Collectors.toList());
        List<Name> dwarfFemaleNames = filterData(allNames, race -> isDwarf(race.getRace())).stream().filter(name -> isFemale(name.getGender())).collect(Collectors.toList());
        List<Name> dwarfBothNames = filterData(allNames, race -> isDwarf(race.getRace())).stream().filter(name -> isBoth(name.getGender())).collect(Collectors.toList());

        List<Name> elfMaleNames = filterData(allNames, race -> isElf(race.getRace())).stream().filter(name -> isMale(name.getGender())).collect(Collectors.toList());
        List<Name> elfFemaleNames = filterData(allNames, race -> isElf(race.getRace())).stream().filter(name -> isFemale(name.getGender())).collect(Collectors.toList());
        List<Name> elfBothNames = filterData(allNames, race -> isElf(race.getRace())).stream().filter(name -> isBoth(name.getGender())).collect(Collectors.toList());

        System.out.println("Male human Names: " + humanMaleNames.size());
        System.out.println("Female human Names: " + humanFemaleNames.size());

        System.out.println("Male halfling Names: " + halflingMaleNames.size());
        System.out.println("Female halfling Names: " + halflingFemaleNames.size());
        System.out.println("Both halfling Nicknames: " + halflingBothNames.size());

        System.out.println("Male dwarf Names: " + dwarfMaleNames.size());
        System.out.println("Female dwarf Names: " + dwarfFemaleNames.size());
        System.out.println("Both dwarf Nicknames: " + dwarfBothNames.size());


        System.out.println("Male elf Names: " + elfMaleNames.size());
        System.out.println("Female elf Names: " + elfFemaleNames.size());
        System.out.println("Both elf Nicknames: " + elfBothNames.size());

        System.out.println("Human Nicknames: " + humanNicknames.size());
        System.out.println("Halfling Nicknames: " + hlaflingNicknames.size());
        System.out.println("Dwarf Nicknames: " + dwarfNicknames.size());
        System.out.println("Elf Nicknames: " + elfNicknames.size());

        System.out.println("All nicknames: " + allNames);
    }

    private static <T> List<T> filterData(Iterable<T> iterable, Predicate<T> predicate) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList())
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private static boolean isMale(Gender gender) {
        return gender.getOption() == 0;
    }

    private static boolean isFemale(Gender gender) {
        return gender.getOption() == 1;
    }

    private static boolean isBoth(Gender gender) {
        return gender.getOption() == 2;
    }

    private static boolean isHuman(Race race) {
        return race.getOption() == 0;
    }
    private static boolean isHalfing(Race race) {
        return race.getOption() == 1;
    }
    private static boolean isDwarf(Race race) {
        return race.getOption() == 2;
    }
    private static boolean isElf(Race race) {
        return race.getOption() == 3;
    }

}
