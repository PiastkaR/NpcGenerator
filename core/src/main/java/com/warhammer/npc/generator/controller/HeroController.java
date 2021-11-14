package com.warhammer.npc.generator.controller;

import com.warhammer.npc.generator.hero.Hero;
import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.service.CharacterCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/heros")
public class HeroController {

    private final CharacterCreatorService creatorService;

    @GetMapping(path = "/heroDesc")
    @ResponseBody
    public HeroDescription generateHeroDescription(@RequestParam String race, @RequestParam String gender) {
        return creatorService.getHeroDescription(race, gender);
    }

    @GetMapping(path = "/hero")
    @ResponseBody
    public Hero generateHero(@RequestParam String race, @RequestParam String gender) {
        return creatorService.getHero(race, gender);
    }

    @GetMapping(path = "/card")
    public String getCard(@RequestParam String race, @RequestParam String gender,@RequestParam String userName, Model model) {
        Hero hero = creatorService.getHero(race, gender);
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

        return "karta_z_tekstem";
    }

}
