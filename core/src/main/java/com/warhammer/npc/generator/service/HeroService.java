package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.Hero;
import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.abilities.Ability;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.skills.Skill;
import com.warhammer.npc.generator.mechanics.DiceThrowGenerator;
import com.warhammer.npc.generator.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final DiceThrowGenerator diceThrowGenerator;

    static private final List<BasicSkill> basicSkills = Arrays.asList(
            new BasicSkill("Charakteryzacja", 0),
            new BasicSkill("Dowodzenie", 1),
            new BasicSkill("Hazard", 2),
            new BasicSkill("Jeśdziectwo", 3),
            new BasicSkill("Mocna głowa", 4),
            new BasicSkill("Opieka nad zwierzętami", 5),
            new BasicSkill("Plotkowanie", 6),
            new BasicSkill("Pływanie", 7),
            new BasicSkill("Powożenie", 8),
            new BasicSkill("Przekonywanie", 9),
            new BasicSkill("Przeszukiwanie", 10),
            new BasicSkill("Skradanie się", 11),
            new BasicSkill("Spostrzegawczość", 12),
            new BasicSkill("Sztuka przetrwania", 13),
            new BasicSkill("Targowanie", 14),
            new BasicSkill("Ukrywanie się", 15),
            new BasicSkill("Wioślarstwo", 16),
            new BasicSkill("Wspinaczka", 17),
            new BasicSkill("Wycena", 18),
            new BasicSkill("Zastraszanie", 19)
    );

    public Model assignSkillNames(List<Skill> skills, Model model) {
        int counter = 0;
        for (Skill skill : skills) {
            boolean basicSkill = isBasicSkills(model, skill);
            if (!basicSkill) {
                skill.getName();
                skill.getDescription();
                model.addAttribute("skillName" + counter, skill.getName());
                model.addAttribute("skillDescription" + counter, skill.getDescription());
                counter++;
            }
        }

        return model;
    }

    public Model getMoney(Model model) {
        int goldCrowns = diceThrowGenerator.k10Throw();
        int silver = diceThrowGenerator.k10Throw();
        int penses = diceThrowGenerator.k100Throw();

        model.addAttribute("goldCrowns", goldCrowns);
        model.addAttribute("silver", silver);
        model.addAttribute("penses", penses);

        return model;
    }

    public Model assignAbilitiesNames(List<Ability> abilities, Model model) {
        int counter = 0;
        for (Ability ability : abilities) {
            ability.getName();
            ability.getDescription();
            model.addAttribute("abilityName" + counter, ability.getName());
            model.addAttribute("abilityDescription" + counter, ability.getDescription());
            counter++;

        }

        return model;
    }

    private boolean isBasicSkills(Model model, Skill skill) {
        boolean isBasic = false;
        for (BasicSkill basicSkill : basicSkills) {
            if (basicSkill.getName().equals(skill.getName())) {
                model.addAttribute("basicSkillName" + basicSkill.getNumber(), skill.getName());
                model.addAttribute("basicSkillDescription" + basicSkill.getNumber(), skill.getDescription());

                isBasic = true;
            }
        }

        return isBasic;
    }

    public Model assignEquipment(List<Equipment> equipment, Model model) {
        int weaponCounter = 0;
        int armourCounter = 0;
        int totalArmour = 0;
        int headArmour = 0;
        int bodyArmour = 0;
        int leftHandArmour = 0;
        int rightHandArmour = 0;
        int legsArmour = 0;
        int otherEquipment = 0;
        for (Equipment eq : equipment) {
            if (eq instanceof Weapon) {
                if (eq.getName().equals("Puklerz") || eq.getName().equals("Tarcza")) {
                    leftHandArmour++;
                }
                String name = eq.getName();
                int load = eq.getLoad();
                String category = ((Weapon) eq).getCategory();
                List<Characteristic> characteristics = ((Weapon) eq).getCharacteristics();
                String range = ((Weapon) eq).getRange();
                String strength = ((Weapon) eq).getStrength();
                String reload = ((Weapon) eq).getReload();
                model.addAttribute("weaponName" + weaponCounter, name);
                model.addAttribute("weaponLoad" + weaponCounter, load);
                model.addAttribute("weaponCategory" + weaponCounter, category);
                model.addAttribute("weaponCharacteristics" + weaponCounter, getCharacteristics(characteristics));
                model.addAttribute("weaponRange" + weaponCounter, range);
                model.addAttribute("weaponStrength" + weaponCounter, strength);
                model.addAttribute("weaponReload" + weaponCounter, reload);
                weaponCounter++;
            }
            if (eq instanceof Armour) {
                String name = eq.getName();
                int load = eq.getLoad();
                int defense = ((Armour) eq).getDefense();
                String location = ((Armour) eq).getLocation();
                model.addAttribute("armourName" + armourCounter, name);
                model.addAttribute("armourLoad" + armourCounter, load);
                model.addAttribute("armourDefense" + armourCounter, defense);
                model.addAttribute("armourLocation" + armourCounter, location);
                if (location.contains("ręce")) {
                    leftHandArmour++;
                    rightHandArmour++;
                }
                if (location.contains("nogi")) {
                    legsArmour++;
                }
                if (location.contains("korpus")) {
                    bodyArmour++;
                }
                if (location.contains("głowa")) {
                    headArmour++;
                }
                armourCounter++;
            }
            if (!(eq instanceof Armour) && !(eq instanceof Weapon) && !eq.getName().equals("Miecz jednoręczny")) {
                String name = eq.getName();
                int load = eq.getLoad();
                model.addAttribute("eqName" + otherEquipment, name);
                model.addAttribute("eqLoad" + otherEquipment, load);
                otherEquipment++;
            }
            totalArmour = leftHandArmour + rightHandArmour + legsArmour + bodyArmour + headArmour;
            model.addAttribute("totalArmour", totalArmour);
            model.addAttribute("leftHandArmour", leftHandArmour);
            model.addAttribute("rightHandArmour", rightHandArmour);
            model.addAttribute("legsArmour", legsArmour);
            model.addAttribute("bodyArmour", bodyArmour);
            model.addAttribute("headArmour", headArmour);
        }

        return model;
    }

    public Model resolveSpeed(int speed, Model model) {
        if (speed == 3) {
            model.addAttribute("movement", 6);
            model.addAttribute("charge", 12);
            model.addAttribute("run", 18);
        }
        if (speed == 4) {
            model.addAttribute("movement", 8);
            model.addAttribute("charge", 16);
            model.addAttribute("run", 25);
        }
        if (speed == 5) {
            model.addAttribute("movement", 10);
            model.addAttribute("charge", 20);
            model.addAttribute("run", 30);
        }
        return model;
    }

    public List<String> getCharacteristics(List<Characteristic> characteristics) {
        if (characteristics == null)
            return Collections.emptyList();
        return characteristics.stream().map(Characteristic::getDescription).collect(Collectors.toList());
    }

    public Model resolveDescriptionModel(Model model, String userName, String race, String gender, Hero hero) {
        HeroDescription description = hero.getDescription();
        model.addAttribute("userName", userName);
        model.addAttribute("race", race);
        model.addAttribute("gender", gender);
        model.addAttribute("name", description.getName().getCorrectName(Race.valueOf(race)));
        model.addAttribute("age", description.getAge());
        model.addAttribute("eyes", description.getEyesColour());
        model.addAttribute("hair", description.getHairColour());
        model.addAttribute("birthplace", description.getBirthPlace());
        model.addAttribute("weight", description.getWeight());
        model.addAttribute("height", description.getHeight());
        model.addAttribute("star", description.getStarSign());
        model.addAttribute("siblings", description.getSiblings());
        model.addAttribute("markings", description.getSpecialFeatures());

        return model;
    }

    public Model resolveMainCharacteristics(Model model, Hero hero) {
        MainCharacteristics mainCharacteristics = hero.getMainCharacteristics();
        model.addAttribute("ww", mainCharacteristics.getCombatSkills());
        model.addAttribute("us", mainCharacteristics.getArcherySkills());
        model.addAttribute("k", mainCharacteristics.getVim());
        model.addAttribute("odp", mainCharacteristics.getHardiness());
        model.addAttribute("zr", mainCharacteristics.getAgility());
        model.addAttribute("int", mainCharacteristics.getIntelligence());
        model.addAttribute("sw", mainCharacteristics.getWillpower());
        model.addAttribute("ogd", mainCharacteristics.getPolish());

        return model;
    }

    public Model resolveSecondaryCharacteristics(Model model, Hero hero) {
        SecondaryCharacteristics secondaryCharacteristics = hero.getSecondaryCharacteristics();
        model.addAttribute("a", secondaryCharacteristics.getAttacks());
        model.addAttribute("zyw", secondaryCharacteristics.getVitality());
        model.addAttribute("s", secondaryCharacteristics.getStrength());
        model.addAttribute("wt", secondaryCharacteristics.getDurability());
        model.addAttribute("sz", secondaryCharacteristics.getSpeed());
        model.addAttribute("mag", secondaryCharacteristics.getMagic());
        model.addAttribute("po", secondaryCharacteristics.getInsanityPoints());
        model.addAttribute("pp", secondaryCharacteristics.getDestinyPoints());

        return model;
    }

    public Model resolveMainCharacteristicsFromProfession(Model model, MainCharacteristics mainFromProfession) {
        model.addAttribute("mww", mainFromProfession.getCombatSkills());
        model.addAttribute("mus", mainFromProfession.getArcherySkills());
        model.addAttribute("mk", mainFromProfession.getVim());
        model.addAttribute("modp", mainFromProfession.getHardiness());
        model.addAttribute("mzr", mainFromProfession.getAgility());
        model.addAttribute("mint", mainFromProfession.getIntelligence());
        model.addAttribute("msw", mainFromProfession.getWillpower());
        model.addAttribute("mogd", mainFromProfession.getPolish());

        return model;
    }

    public Model resolveSecondaryCharacteristicsFromProfession(Model model, SecondaryCharacteristics secondaryFromProfession) {
        model.addAttribute("sa", secondaryFromProfession.getAttacks());
        model.addAttribute("szyw", secondaryFromProfession.getVitality());
        model.addAttribute("ss", secondaryFromProfession.getStrength());
        model.addAttribute("swt", secondaryFromProfession.getDurability());
        model.addAttribute("ssz", secondaryFromProfession.getSpeed());
        model.addAttribute("smag", secondaryFromProfession.getMagic());
        model.addAttribute("spo", secondaryFromProfession.getInsanityPoints());
        model.addAttribute("spp", secondaryFromProfession.getDestinyPoints());

        return model;
    }

}
