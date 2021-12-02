package com.warhammer.npc.generator.controller;

import com.warhammer.npc.generator.hero.Hero;
import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.abilities.Ability;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.skills.Skill;
import com.warhammer.npc.generator.model.Equipment;
import com.warhammer.npc.generator.service.CharacterCreatorService;
import com.warhammer.npc.generator.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/heros")
public class HeroController {

    private final CharacterCreatorService creatorService;
    private final HeroService heroService;

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

        heroService.resolveDescriptionModel(model, userName, race, gender, hero);
        heroService.resolveMainCharacteristics(model, hero);
        heroService.resolveSecondaryCharacteristics(model, hero);
        heroService.resolveSpeed(hero.getSecondaryCharacteristics().getSpeed(), model);

        MainCharacteristics mainFromProfession = hero.getDescription().getActualProfession().getMainCharacteristicsDevelopment();
        SecondaryCharacteristics secondaryFromProfession = hero.getDescription().getActualProfession().getSecondaryCharacteristicsDevelopment();
        heroService.resolveMainCharacteristicsFromProfession( model, mainFromProfession);
        heroService.resolveSecondaryCharacteristicsFromProfession(model, secondaryFromProfession);

        String actualProfession = hero.getDescription().getActualProfession().getDescription();
        model.addAttribute("actualProfession", actualProfession);

        List<Equipment> equipment = hero.getDescription().getActualProfession().getEquipment();
        heroService.assignEquipment(equipment, model);

        List<Skill> skills = hero.getDescription().getActualProfession().getSkills();
        heroService.assignSkillNames(skills, model);

        List<Ability> abilities = hero.getDescription().getActualProfession().getAbilities();
        heroService.assignAbilitiesNames(abilities, model);

        return "karta_z_tekstem";
    }

}
