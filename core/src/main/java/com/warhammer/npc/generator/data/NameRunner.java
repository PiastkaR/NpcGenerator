package com.warhammer.npc.generator.data;

import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.description.CharactersClass;
import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.*;
import com.warhammer.npc.generator.hero.skills.Skill;
import com.warhammer.npc.generator.model.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
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
    @Autowired
    private Name2ndPartRepository name2ndPartRepository;
    @Autowired
    private BirthplaceRepository birthplaceRepository;
    @Autowired
    private ArmourRepository armourRepository;
    @Autowired
    private WeaponRepository weaponRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private AbilityRepository abilityRepository;
    @Autowired
    private ProfessionRepository professionRepository;

    @Override
    public void run(String... args) throws Exception {

//        Name johan = new Name(1L, "Johan", new Nickname(1L, "Albreht"), null, Gender.MALE, Race.HUMAN);
//        Name adam = new Name(2, "Adam", new Nickname(2, "Zawadzki"), null, Gender.MALE, Race.HUMAN);
//        Name ewa = new Name(3, "Ewa", new Nickname(3, "Sikora"), null, Gender.FEMALE, Race.HUMAN);
//        List<Name> names = Arrays.asList(johan, adam, ewa);
//        nameRepository.saveAll(names);
//        Iterable<Name> all = nameRepository.findAll();
//        System.out.println("All male names: " + all);
//        nameRepository.deleteAll(all);

        professionRepository.save(new Profession(1L,"Pustelnik",	CharactersClass.SCHOLAR,List.of(Race.HUMAN), new MainCharacteristics(0,5,5,10,10,0,10,0),	new SecondaryCharacteristics(0,2,0,0,0,0,0,0), Collections.emptyList(),List.of("Sztuka przetrwania", "Przeszukiwanie", "Skradanie się"), List.of("Chodu!", "Twardziel", "Odporność na trucizny", "Wędrowiec","Odwaga")));
        professionRepository.save(new Profession(82L,"Szermierz Estalijski",	CharactersClass.WARRIOR,	List.of(Race.HUMAN),	new MainCharacteristics(15,0,5,5,10,5,0,0),	new SecondaryCharacteristics(1,2,0,0,0,0,0,0),	List.of("Szpada", "ubranie najlepszej jakości", "perfumy"," misktura leczenia"),	List.of("Czytanie i pisanie", "Nauka(anatomia)", "Unik", "Wiedza (Estalia)", "Język Estalijski"),	List.of("Szybki refleks", "Broń specjalna (szermiercza)", "Silny cios", "Morderczy atak")));
        professionRepository.save(new Profession(77L,"Ochroniarz",CharactersClass.WARRIOR,List.of(Race.HUMAN,Race.DWARF,Race.ELF),	new MainCharacteristics(10,0,5,5,5,0,0,0),	new SecondaryCharacteristics(1,3,0,0,0,0,0,0),	List.of("Topór do rzucania", "Kastet","skórzana kurta", "Puklerz"), List.of("Leczenie", "Spostrzegawczość", "Unik", "Zastraszanie"),	List.of("Bardzo silny", "Bijatyka", "Broń specjalna(parująca)", "Broń specjalna(rzucana)", "Ogłuszanie", "Szybkie wyciągniecie")));
        professionRepository.save(new Profession(61L,"Podżegacz",CharactersClass.CRIMINAL,List.of(Race.HUMAN,Race.DWARF,Race.HALFLING),	new MainCharacteristics(5,5,0,0,5,10,0,10),	new SecondaryCharacteristics(0,2,0,0,0,0,0,0),	List.of("skórzana kurta", "ubranie dobnrej jakości", "12 ulotek"),	List.of("Czytanie i pisanie", "Plotkowanie", "Nauka(prawo)", "Przekonywanie", "Spostrzegawczość", "Ukrywanie się", "Język Bretoński", "Język Staroświatowy"),	List.of("Chodu!","Opanowanie", "Przemawianie")));
        professionRepository.save(new Profession(23L,"Zarządca",	CharactersClass.COMMONER,List.of(Race.HUMAN, Race.HALFLING),	new MainCharacteristics(5,5,5,0,0,10,5,5),   new SecondaryCharacteristics(0,2,0,0,0,0,0,0),	List.of("skórzana kurta", "skórzany hełm", "ubranie dobrej jakości", "koń z siodłem i uprzężą"), List.of("Czytanie i pisanie", "Nawigacja", "Jeździectwo", "Nauka (prawo)", "Opieka nad zwierzętami", "Przekonywanie", "Spostrzegawczość", "Zastraszanie"),List.of(	"Etykieta", "Przemawianie")));
//        weaponRepository.save(new Weapon(41L, "Topór do rzucania",40,"rzucana","S-2","8/-","akcja",Collections.EMPTY_LIST));

       skillRepository.save(new Skill(20L, "Śledzenie","zr"));
       skillRepository.save(new Skill(24L, "Gadanina","ogd"));
       skillRepository.save(new Skill(19L, "Mocna głowa","odp"));
       skillRepository.save(new Skill(46L, "Żeglarstwo","zr"));
       skillRepository.save(new Skill(45L, "Język","int"));

//        Iterable<Profession> all = professionRepository.findAll();
//        System.out.println(all);
//        Iterable<Name> allNames = nameRepository.findAll();
//        Iterable<Nickname> allNicknames = nicknameRepository.findAll();
//        Iterable<NameConnector> allConnectors = nameConnectorRepository.findAll();
//        Iterable<Name2ndPart> allNames2 = name2ndPartRepository.findAll();
//        Iterable<Birthplace> birthplaces = birthplaceRepository.findAll();
//
//        List<Nickname> humanNicknames = filterData(allNicknames, nickname -> isHuman(nickname.getRaceNickname()));
//        List<Nickname> hlaflingNicknames = filterData(allNicknames, nickname -> isHalfing(nickname.getRaceNickname()));
//        List<Nickname> elfNicknames = filterData(allNicknames, nickname -> isElf(nickname.getRaceNickname()));
//
//        List<Name> humanMaleNames = filterData(allNames, race -> isHuman(race.getRace())).stream().filter(name -> isMale(name.getGender())).collect(Collectors.toList());
//        List<Name> humanFemaleNames = filterData(allNames, race -> isHuman(race.getRace())).stream().filter(name -> isFemale(name.getGender())).collect(Collectors.toList());
//
//        List<Name2ndPart> halflingMaleNames = filterData(allNames2, race -> isHalfing(race.getRace())).stream().filter(name -> isMale(name.getGender())).collect(Collectors.toList());
//        List<Name2ndPart> halflingFemaleNames = filterData(allNames2, race -> isHalfing(race.getRace())).stream().filter(name -> isFemale(name.getGender())).collect(Collectors.toList());
//        List<Name> halflingBothNames = filterData(allNames, race -> isHalfing(race.getRace())).stream().filter(name -> isBoth(name.getGender())).collect(Collectors.toList());
//
//        List<Name2ndPart> dwarfMaleNames = filterData(allNames2, race -> isDwarf(race.getRace())).stream().filter(name -> isMale(name.getGender())).collect(Collectors.toList());
//        List<Name2ndPart> dwarfFemaleNames = filterData(allNames2, race -> isDwarf(race.getRace())).stream().filter(name -> isFemale(name.getGender())).collect(Collectors.toList());
//        List<Name> dwarfBothNames = filterData(allNames, race -> isDwarf(race.getRace())).stream().filter(name -> isBoth(name.getGender())).collect(Collectors.toList());
//
//        List<Name2ndPart> elfMaleNames = filterData(allNames2, race -> isElf(race.getRace())).stream().filter(name -> isMale(name.getGender())).collect(Collectors.toList());
//        List<Name2ndPart> elfFemaleNames = filterData(allNames2, race -> isElf(race.getRace())).stream().filter(name -> isFemale(name.getGender())).collect(Collectors.toList());
//        List<Name> elfBothNames = filterData(allNames, race -> isElf(race.getRace())).stream().filter(name -> isBoth(name.getGender())).collect(Collectors.toList());
//
//        List<NameConnector> elfConnectors = filterData(allConnectors, race -> isElf(race.getRaceConnector())).stream().collect(Collectors.toList());
//
//        List<Name2ndPart> elfName2 = filterData(allNames2, race -> isElf(race.getRace())).stream().collect(Collectors.toList());
//        List<Name2ndPart> dwarfsName2 = filterData(allNames2, race -> isDwarf(race.getRace())).stream().collect(Collectors.toList());
//        List<Name2ndPart> halflingName2 = filterData(allNames2, race -> isHalfing(race.getRace())).stream().collect(Collectors.toList());
//
//        List<Birthplace> dwarfBirthplaces = filterData(birthplaces, race -> isDwarf(race.getRace())).stream().collect(Collectors.toList());
//        List<Birthplace> elfBirthplaces = filterData(birthplaces, race -> isElf(race.getRace())).stream().collect(Collectors.toList());
//        List<Birthplace> halaflingBirthplaces = filterData(birthplaces, race -> isHalfing(race.getRace())).stream().collect(Collectors.toList());
//        List<Birthplace> allBirthplaces = StreamSupport.stream(birthplaces.spliterator(), false).collect(Collectors.toList());
//
//        System.out.println("Male human Names: " + humanMaleNames.size());
//        System.out.println("Female human Names: " + humanFemaleNames.size());
//
//        System.out.println("Male halfling Names: " + halflingMaleNames.size());
//        System.out.println("Female halfling Names: " + halflingFemaleNames.size());
//        System.out.println("Both halfling Names: " + halflingBothNames.size());
//
//        System.out.println("Male dwarf Names: " + dwarfMaleNames.size());
//        System.out.println("Female dwarf Names: " + dwarfFemaleNames.size());
//        System.out.println("Both dwarf Names: " + dwarfBothNames.size());
//
//
//        System.out.println("Male elf Names: " + elfMaleNames.size());
//        System.out.println("Female elf Names: " + elfFemaleNames.size());
//        System.out.println("Both elf Names: " + elfBothNames.size());
//
//        System.out.println("Human Nicknames: " + humanNicknames.size());
//        System.out.println("Halfling Nicknames: " + hlaflingNicknames.size());
//        System.out.println("Elf Nicknames: " + elfNicknames.size());
//
//        System.out.println("Elf connectors: " + elfConnectors.size());
//
//        System.out.println("Elf name2: " + elfName2.size());
//        System.out.println("Dwarf name2: " + dwarfsName2.size());
//        System.out.println("Halfling name2: " + halflingName2.size());
//
//        System.out.println("Dwarf birthplaces: " + dwarfBirthplaces.size());
//        System.out.println("Elf birthplaces: " + elfBirthplaces.size());
//        System.out.println("Hlafling birthplaces: " + halaflingBirthplaces.size());
//        System.out.println("All birthplaces: " + allBirthplaces.size());

//        System.out.println("All names: " + allNames);
//        System.out.println("All bothDwarves names: " + dwarfBothNames);
//        System.out.println("All bothElfNames: " + elfBothNames);
//        System.out.println("All nicknames: " + allNicknames);
//        System.out.println("All connectors: " + allConnectors);
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
