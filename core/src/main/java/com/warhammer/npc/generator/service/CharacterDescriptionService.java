package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.HeroDescriptionBuilder;
import com.warhammer.npc.generator.hero.abilities.Ability;
import com.warhammer.npc.generator.hero.description.CharactersClass;
import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.AbilityRepository;
import com.warhammer.npc.generator.hero.repository.BirthplaceRepository;
import com.warhammer.npc.generator.hero.repository.SkillRepository;
import com.warhammer.npc.generator.hero.skills.Skill;
import com.warhammer.npc.generator.mechanics.DiceThrowGenerator;
import com.warhammer.npc.generator.mechanics.MechanicsUtils;
import com.warhammer.npc.generator.model.Birthplace;
import com.warhammer.npc.generator.model.ProfessionWrapperBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CharacterDescriptionService {

    private final BirthplaceRepository birthplaceRepository;
    private final SkillRepository skillRepository;
    private final AbilityRepository abilityRepository;
    private final DiceThrowGenerator diceThrow;
    private final NameService nameService;
    private final ProfessionService professionService;

    public HeroDescription generateHeroDescription(Race race, Gender gender, CharactersClass charactersClass) {
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
                .withAbilities(getRandomAbilitiesForRace(race))
                .withSkills(getRandomSkillsForRace(race))
                .withActualProfession(generateProfession(race, charactersClass))
                .build();

    }

    private ProfessionWrapperBuilder generateProfession(Race race, CharactersClass charactersClass) {
        return professionService.getProfession(race, charactersClass);
    }

    private List<Skill> getRandomSkillsForRace(Race race) {
        Iterable<Skill> iterable = skillRepository.findAll();
        List<Skill> skills = new ArrayList<>();
        List<Skill> collect = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        skills.addAll(collect);

        Optional<Skill> plotkowanie = skillRepository.findById(6L);
        Optional<Skill> wiedza = skillRepository.findById(42L);
        Optional<Skill> jezyk = skillRepository.findById(45L);
        Optional<Skill> nauka = skillRepository.findById(29L);
        Optional<Skill> rzemioslo = skillRepository.findById(33L);

        Skill wiedzaOImperium = supplyWiedzaOrNauka(wiedza.get(), " o Imperium");
        Skill wiedzaElfy = supplyWiedzaOrNauka(wiedza.get(), " Elfy");
        Skill wiedzaNiziolki = supplyWiedzaOrNauka(wiedza.get(), " Niziolki");
        Skill wiedzaKrasnolody = supplyWiedzaOrNauka(wiedza.get(), " Krasnoludy");

        Skill eltharin = supplyWiedzaOrNauka(jezyk.get(), " Eltharin");
        Skill staroswiatowy = supplyWiedzaOrNauka(jezyk.get(), " Staroświatowy");
        Skill niziolkowy = supplyWiedzaOrNauka(jezyk.get(), " Niziolkowy");
        Skill khazalid = supplyWiedzaOrNauka(jezyk.get(), " Khazalid");

        Skill naukaHeraldyka = supplyWiedzaOrNauka(nauka.get(), " Heraldyka");

        Skill rzemiosloGotowanie = supplyWiedzaOrNauka(rzemioslo.get(), " Gotowanie");
        Skill rzemiosloKowalstwo = supplyWiedzaOrNauka(rzemioslo.get(), " Kowalstwo");
        skills.remove(plotkowanie.get());
        skills.remove(wiedza.get());
        skills.remove(jezyk.get());
        skills.remove(nauka.get());
        skills.remove(rzemioslo.get());

        List<Skill> basicSkills = new ArrayList<>();
        if (race == Race.HUMAN) {
            basicSkills.add(plotkowanie.get());
            basicSkills.add(wiedzaOImperium);

            return getBasics(skills, basicSkills);
        }
        if (race == Race.HALFLING) {
            basicSkills.add(plotkowanie.get());
            basicSkills.add(niziolkowy);
            basicSkills.add(staroswiatowy);
            basicSkills.add(wiedzaNiziolki);
            basicSkills.add(naukaHeraldyka);
            basicSkills.add(rzemiosloGotowanie);

            return getBasics(skills, basicSkills);
        }
        if (race == Race.ELF) {
            basicSkills.add(staroswiatowy);
            basicSkills.add(eltharin);
            basicSkills.add(wiedzaElfy);

            return getBasics(skills, basicSkills);
        }
        if (race == Race.DWARF) {
            basicSkills.add(plotkowanie.get());
            basicSkills.add(staroswiatowy);
            basicSkills.add(wiedzaKrasnolody);
            basicSkills.add(khazalid);
            basicSkills.add(rzemiosloKowalstwo);

            return getBasics(skills, basicSkills);
        }
        return null;
    }

    private Skill supplyWiedzaOrNauka(Skill wiedza, String oCzym) {
        Supplier<String> supplier = () -> wiedza.getName().concat(oCzym);
        return new Skill(500L, supplier.get(), "wiedza");
    }

    private Ability supplyBsOrOther(Ability wiedza, String oCzym) {
        Supplier<String> supplier = () -> wiedza.getName().concat(oCzym);
        return new Ability(500L, supplier.get(), "wiedza");
    }

    private static <T> List<T> getBasics(List<T> allSkills, List<T> basicSkills) {
        T random1FromBasics = getRandomFromList(allSkills);
        allSkills.remove(random1FromBasics);
        T random2FromBasics = getRandomFromList(allSkills);
        basicSkills.add(random1FromBasics);
        basicSkills.add(random2FromBasics);

        return basicSkills;
    }

    private List<Ability> getRandomAbilitiesForRace(Race race) {
        Iterable<Ability> iterable = abilityRepository.findAll();
        List<Ability> abilities = new ArrayList<>();
        List<Ability> collect = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        abilities.addAll(collect);

        Optional<Ability> bronSpecjalna = abilityRepository.findById(10L);
        Ability bsDlugiLuk = supplyBsOrOther(bronSpecjalna.get(), " długi łuk");
        Ability proca = supplyBsOrOther(bronSpecjalna.get(), " proca");

        Ability bystryWzrok = abilityRepository.findById(11L).get();
        Ability opanowanie = abilityRepository.findById(52L).get();
        Ability widzenieWCiemnosci = abilityRepository.findById(75L).get();
        Ability krasnoludzkiFach = abilityRepository.findById(25L).get();
        Ability krzepki = abilityRepository.findById(27L).get();
        Ability odpNaMagie = abilityRepository.findById(47L).get();
        Ability odwaga = abilityRepository.findById(50L).get();
        Ability zapieklaNienawisc = abilityRepository.findById(81L).get();
        Ability odpornoscNaChoas = abilityRepository.findById(45L).get();
        ArrayList<Ability> basicAbilities = new ArrayList<>();

        if (race == Race.HUMAN) {
            return getBasics(abilities, basicAbilities);
        }
        if (race == Race.HALFLING) {
            List<Ability> basicSkills = Arrays.asList(widzenieWCiemnosci, odpornoscNaChoas, proca);
            return getBasics(abilities, basicSkills);
        }
        if (race == Race.ELF) {
            List<Ability> basicSkills = Arrays.asList(bsDlugiLuk, bystryWzrok, opanowanie, widzenieWCiemnosci);
            return basicSkills;
        }
        if (race == Race.DWARF) {
            List<Ability> basicSkills = Arrays.asList(widzenieWCiemnosci, zapieklaNienawisc, odwaga, odpNaMagie, krzepki, krasnoludzkiFach);
            return getBasics(abilities, basicSkills);
        }
        return null;
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

    public static int generateNumberOfSiblings(Race race) {
        if (race == Race.ELF || race == Race.DWARF)
            return MechanicsUtils.getRandomNumberUsingNextInt(0, 3);
        if (race == Race.HALFLING)
            return MechanicsUtils.getRandomNumberUsingNextInt(0, 6);
        return MechanicsUtils.getRandomNumberUsingNextInt(0, 5);
    }

    public static int generateAge(Race race) {
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

    public static int generateWeight(Race race) {
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

    public static String generateHeirColour(Race race) {
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

    public static String generateEyesColour(Race race) {
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

    public static String generateSpecialMarkings() {
        List<String> markings = Arrays.asList("Bielmo na oku", "Blizna", " Brak brwi", "Brak palca", "Brak zęba",
                "Brodawki", "Blada cera", " Duży nos", "Dziwny zapach ciała", " Duży pieprzyk", "Kolczyk  w nosie",
                "Kolczyk w uchu", "Niewielka łysina", "Oczy różnego koloru", "Piegi", "Poszarpane ucho", "Ślady po ospie",
                "Tatuaż", " Wystające zęby", "Wytrzeszczone oczy", "Złamany nos");

        return getRandomFromList(markings);
    }

    public static String generateStarSign() {
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

    private static <T> T getRandomFromList(List<T> data) {
        int i = MechanicsUtils.drawFromFiltered(data);
        return data.get(i);
    }

}
