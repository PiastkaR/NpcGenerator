package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.HeroDescriptionBuilder;
import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.BirthplaceRepository;
import com.warhammer.npc.generator.mechanics.DiceThrowGenerator;
import com.warhammer.npc.generator.mechanics.MechanicsUtils;
import com.warhammer.npc.generator.model.Birthplace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterDescriptionService {

    private final BirthplaceRepository birthplaceRepository;
    private final DiceThrowGenerator diceThrow;
    private final NameService nameService;

    public HeroDescription generateHeroDescription(Race race, Gender gender) {
        return new HeroDescriptionBuilder()
                .withName(nameService.generateName(race, gender))
                .withRace(race)
                .withGender(gender)
                .withBirthPlace(getBirthPlaceForRace(race).getPlace())
                .withAge(generateAge(race))
                .withEyesColour(generateEyesColour(race))
                .withHairColour(generateHeirColour(race))
                .withHeight(generateHeight(race))
                .withWeight(generateWeight(race))
                .withSiblings(generateNumberOfSiblings(race))
                .withSpecialFeatures(generateSpecialMarkings())
                .withBirthPlace(getBirthPlaceForRace(race).getPlace())
                .withStarSign(generateStarSign())
                .build();
    }

    private Birthplace getBirthPlaceForRace(Race race) {
        Birthplace resultBirthplace;

        Iterable<Birthplace> places = birthplaceRepository.findAll();
        List<Birthplace> humanBirthPlaces = MechanicsUtils.filterData(places, birthplace -> birthplace.getRace() == Race.HUMAN);
        List<Birthplace> nonHumanBirthPlaces = MechanicsUtils.filterData(places, birthplace -> birthplace.getRace() == race);

        if (race == Race.HUMAN) {
            int nonHumanDraw = MechanicsUtils.drawFromFiltered(humanBirthPlaces);
            resultBirthplace = humanBirthPlaces.get(nonHumanDraw);
        } else {
            ArrayList<Birthplace> addedList = new ArrayList<>();
            addedList.addAll(humanBirthPlaces);

            List<Birthplace> birthplaces = tripleProbability(nonHumanBirthPlaces);
            addedList.addAll(birthplaces);

            int nonHumanDraw = MechanicsUtils.drawFromFiltered(addedList);
            resultBirthplace = addedList.get(nonHumanDraw);
        }

        return resultBirthplace;
    }

    public int generateNumberOfSiblings(Race race) {
        if (race == Race.ELF || race == Race.DWARF)
            return MechanicsUtils.getRandomNumberUsingNextInt(0, 3);
        if (race == Race.HALFLING)
            return MechanicsUtils.getRandomNumberUsingNextInt(0, 6);
        return MechanicsUtils.getRandomNumberUsingNextInt(0, 5);
    }

    public int generateAge(Race race) {
        if (race == Race.HUMAN)
            return MechanicsUtils.getRandomNumberUsingNextInt(16, 35);
        if (race == Race.ELF)
            return MechanicsUtils.getRandomNumberUsingNextInt(30, 180);
        if (race == Race.DWARF)
            return MechanicsUtils.getRandomNumberUsingNextInt(20, 115);
        if (race == Race.HALFLING)
            return MechanicsUtils.getRandomNumberUsingNextInt(20, 60);
        return 20;
    }

    public int generateWeight(Race race) {
        if (race == Race.ELF)
            return MechanicsUtils.getRandomNumberUsingNextInt(40, 90);
        if (race == Race.DWARF)
            return MechanicsUtils.getRandomNumberUsingNextInt(45, 120);
        if (race == Race.HALFLING)
            return MechanicsUtils.getRandomNumberUsingNextInt(35, 70);
        return MechanicsUtils.getRandomNumberUsingNextInt(50, 110);
    }

    public int generateHeight(Race race) {
        if (race == Race.ELF)
            return 165 + 2 * diceThrow.k10Throw();
        if (race == Race.DWARF)
            return 135 + 2 * diceThrow.k10Throw();
        if (race == Race.HALFLING)
            return 105 + 2 * diceThrow.k10Throw();
        return 155 + 2 * diceThrow.k10Throw();
    }

    public String generateHeirColour(Race race) {
        List<String> humanHair = Arrays.asList("Popielaty", "Ciemny Blond", "Blond", "Rudy", "Ciemno Rudy", "Jasnobrązowy", "Brązowy", "Brązowy", "Ciemnobrązowy", "Czarny");
        List<String> elfHair = Arrays.asList("Srebrny", "Biały", "Blond", "Jasny Blond", "Ciemny Blond", "Jasnobrązowy", "Kasztanowy", "Brązowy", "Ciemnobrązowy", "Czarny");
        List<String> dwarfHair = Arrays.asList("Popielaty", "Blond", "Ciemno Rudy", "Czerwony", "Rudy", "Brązowy", "Brązowy", "Ciemnobrązowy", "Czarny", "Kruczoczarny");
        List<String> halflingHair = Arrays.asList("Popielaty", "Ciemny Blond", "Blond", "Blond", "Rudy", "Ciemno Rudy", "Jasnobrązowy", "Brązowy", "Ciemnobrązowy", "Czarny");

        if (race == Race.ELF)
            getRandomFromList(elfHair);
        ;
        if (race == Race.DWARF)
            getRandomFromList(dwarfHair);
        if (race == Race.HALFLING)
            getRandomFromList(halflingHair);

        return getRandomFromList(humanHair);
    }

    public String generateEyesColour(Race race) {
        List<String> humanEyes = Arrays.asList("Popielaty", "Ciemny Blond", "Blond", "Rudy", "Ciemno Rudy", "Jasnobrązowy", "Brązowy", "Brązowy", "Ciemnobrązowy", "Czarny");
        List<String> elfEyes = Arrays.asList("Srebrny", "Biały", "Blond", "Jasny Blond", "Ciemny Blond", "Jasnobrązowy", "Kasztanowy", "Brązowy", "Ciemnobrązowy", "Czarny");
        List<String> dwarfEyes = Arrays.asList("Popielaty", "Blond", "Ciemno Rudy", "Czerwony", "Rudy", "Brązowy", "Brązowy", "Ciemnobrązowy", "Czarny", "Kruczoczarny");
        List<String> halflingEyes = Arrays.asList("Popielaty", "Ciemny Blond", "Blond", "Blond", "Rudy", "Ciemno Rudy", "Jasnobrązowy", "Brązowy", "Ciemnobrązowy", "Czarny");

        if (race == Race.ELF)
            getRandomFromList(elfEyes);
        ;
        if (race == Race.DWARF)
            getRandomFromList(dwarfEyes);
        if (race == Race.HALFLING)
            getRandomFromList(halflingEyes);

        return getRandomFromList(humanEyes);
    }

    public String generateSpecialMarkings() {
        List<String> markings = Arrays.asList("Bielmo na oku", "Blizna", " Brak brwi", "Brak palca", "Brak zęba",
                "Brodawki", "Blada cera", " Duży nos", "Dziwny zapach ciała", " Duży pieprzyk", "Kolczyk  w nosie",
                "Kolczyk w uchu", "Niewielka łysina", "Oczy różnego koloru", "Piegi", "Poszarpane ucho", "Ślady po ospie",
                "Tatuaż", " Wystające zęby", "Wytrzeszczone oczy", "Złamany nos");

        return getRandomFromList(markings);
    }

    public String generateStarSign() {
        List<String> starSigns = Arrays.asList("Bębny", "Dudy", "Dwa Byki", "Głupiec Mummit", "Gwiazda Uroku", "Gwiazda Wieczorna",
                "Kociał Rhyi", "Lanclet", "Mędrzec Mammint", "Pas Grungiego", "Rozbity Wóz", "Smok Dragomas", "Sznur Limmera",
                "Tancerka", "Tlusty Kocioł", "Vobist Ulotny", "Wielki Krzyż", "Wół Gnuthus", "Wymund Pustelnik", "Złoty Kogut");

        return getRandomFromList(starSigns);
    }

    private static List<Birthplace> tripleProbability(List<Birthplace> nonHumanBirthPlaces) {
        ArrayList<Birthplace> addedList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            addedList.addAll(nonHumanBirthPlaces);
        }

        return addedList;
    }

    private static String getRandomFromList(List<String> list) {
        int i = MechanicsUtils.drawFromFiltered(list);
        return list.get(i);
    }

}
