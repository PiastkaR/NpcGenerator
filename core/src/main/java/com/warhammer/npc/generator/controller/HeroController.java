package com.warhammer.npc.generator.controller;

import com.warhammer.npc.generator.hero.Hero;
import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.abilities.Ability;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.skills.Skill;
import com.warhammer.npc.generator.model.Armour;
import com.warhammer.npc.generator.model.Characteristic;
import com.warhammer.npc.generator.model.Equipment;
import com.warhammer.npc.generator.model.Weapon;
import com.warhammer.npc.generator.service.CharacterCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/heros")
public class HeroController {

    private final CharacterCreatorService creatorService;

    @GetMapping(path = "/heroDesc")
    @ResponseBody
    public HeroDescription generateHeroDescription(@RequestParam String race, @RequestParam String gender, @RequestParam String charactersClass) {
        return creatorService.getHeroDescription(race, gender, charactersClass);
    }

    @GetMapping(path = "/hero")
    @ResponseBody
    public Hero generateHero(@RequestParam String race, @RequestParam String gender, @RequestParam String charactersClass) {
        return creatorService.getHero(race, gender, charactersClass);
    }

    @GetMapping(path = "/card")
    public String getCard(@RequestParam String race, @RequestParam String gender, @RequestParam String charactersClass,
                          @RequestParam String userName, Model model) {
        Hero hero = creatorService.getHero(race, gender, charactersClass);
        model.addAttribute("userName", userName);
        model.addAttribute("race", race);
        model.addAttribute("gender", gender);
        model.addAttribute("name", hero.getDescription().getName().getCorrectName(Race.valueOf(race)));
        model.addAttribute("age", hero.getDescription().getAge());
        model.addAttribute("eyes", hero.getDescription().getEyesColour());
        model.addAttribute("hair", hero.getDescription().getHairColour());
        model.addAttribute("birthplace", hero.getDescription().getBirthPlace());
        model.addAttribute("weight", hero.getDescription().getWeight());
        model.addAttribute("height", hero.getDescription().getHeight());
        model.addAttribute("star", hero.getDescription().getStarSign());
        model.addAttribute("siblings", hero.getDescription().getSiblings());
        model.addAttribute("markings", hero.getDescription().getSpecialFeatures());

        model.addAttribute("ww", hero.getMainCharacteristics().getCombatSkills());
        model.addAttribute("us", hero.getMainCharacteristics().getArcherySkills());
        model.addAttribute("k", hero.getMainCharacteristics().getVim());
        model.addAttribute("odp", hero.getMainCharacteristics().getHardiness());
        model.addAttribute("zr", hero.getMainCharacteristics().getAgility());
        model.addAttribute("int", hero.getMainCharacteristics().getIntelligence());
        model.addAttribute("sw", hero.getMainCharacteristics().getWillpower());
        model.addAttribute("ogd", hero.getMainCharacteristics().getPolish());

        model.addAttribute("a", hero.getSecondaryCharacteristics().getAttacks());
        model.addAttribute("zyw", hero.getSecondaryCharacteristics().getVitality());
        model.addAttribute("s", hero.getSecondaryCharacteristics().getStrength());
        model.addAttribute("wt", hero.getSecondaryCharacteristics().getDurability());
        model.addAttribute("sz", hero.getSecondaryCharacteristics().getSpeed());
        model.addAttribute("mag", hero.getSecondaryCharacteristics().getMagic());
        model.addAttribute("po", hero.getSecondaryCharacteristics().getInsanityPoints());
        model.addAttribute("pp", hero.getSecondaryCharacteristics().getDestinyPoints());

        MainCharacteristics mainFromProfession = hero.getDescription().getActualProfession().getMainCharacteristicsDevelopment();
        model.addAttribute("mww", mainFromProfession.getCombatSkills());
        model.addAttribute("mus", mainFromProfession.getArcherySkills());
        model.addAttribute("mk", mainFromProfession.getVim());
        model.addAttribute("modp", mainFromProfession.getHardiness());
        model.addAttribute("mzr", mainFromProfession.getAgility());
        model.addAttribute("mint", mainFromProfession.getIntelligence());
        model.addAttribute("msw", mainFromProfession.getWillpower());
        model.addAttribute("mogd", mainFromProfession.getPolish());

        SecondaryCharacteristics secondaryFromProfession = hero.getDescription().getActualProfession().getSecondaryCharacteristicsDevelopment();
        model.addAttribute("sa", secondaryFromProfession.getAttacks());
        model.addAttribute("szyw", secondaryFromProfession.getVitality());
        model.addAttribute("ss", secondaryFromProfession.getStrength());
        model.addAttribute("swt", secondaryFromProfession.getDurability());
        model.addAttribute("ssz", secondaryFromProfession.getSpeed());
        model.addAttribute("smag", secondaryFromProfession.getMagic());
        model.addAttribute("spo", secondaryFromProfession.getInsanityPoints());
        model.addAttribute("spp", secondaryFromProfession.getDestinyPoints());

        String actualProfession = hero.getDescription().getActualProfession().getDescription();
        model.addAttribute("actualProfession", actualProfession);

        List<Equipment> equipment = hero.getDescription().getActualProfession().getEquipment();
        assignEquipment(equipment, model);
        List<Skill> skills = hero.getDescription().getActualProfession().getSkills();
        assignSkillNames(skills, model);
        List<Ability> abilities = hero.getDescription().getActualProfession().getAbilities();

        return "karta_z_tekstem";
    }

    private Model assignSkillNames(List<Skill> skills, Model model) {
        int counter = 0;
        for (Skill skill: skills) {
            skill.getName();
            skill.getDescription();
            model.addAttribute("skillName" + counter, skill);
            model.addAttribute("skillDescription" + counter, skill);
            counter++;
        }

        return model;
    }

    private Model assignEquipment(List<Equipment> equipment, Model model) {
        int counter = 0;
        int totalArmour = 0;
        for (Equipment eq: equipment) {
            if (eq instanceof Weapon) {
                String name = eq.getName();
                int load = eq.getLoad();
                String category = ((Weapon) eq).getCategory();
                List<Characteristic> characteristics = ((Weapon) eq).getCharacteristics();
                String range = ((Weapon) eq).getRange();
                String strength = ((Weapon) eq).getStrength();
                String reload = ((Weapon) eq).getReload();
                model.addAttribute("weaponName" + counter, name);
                model.addAttribute("weaponLoad" + counter, load);
                model.addAttribute("weaponCategory" + counter, category);
                model.addAttribute("weaponCharacteristics" + counter, Optional.ofNullable(characteristics.stream().map(Characteristic::getDescription)));
                model.addAttribute("weaponRange" + counter, range);
                model.addAttribute("weaponStrength" + counter, strength);
                model.addAttribute("weaponReload" + counter, reload);
                counter++;
            }
            if (eq instanceof Armour) {
                String name = eq.getName();
                int load = eq.getLoad();
                int defense = ((Armour) eq).getDefense();
                String location = ((Armour) eq).getLocation();
                model.addAttribute("armourName" + counter, name);
                model.addAttribute("armourLoad" + counter, load);
                model.addAttribute("armourDefense" + counter, defense);
                model.addAttribute("armourlocation" + counter, location);
                counter++;
                totalArmour = totalArmour+defense;
            }
            model.addAttribute("totalArmour", totalArmour);
        }

        return model;
    }

}
