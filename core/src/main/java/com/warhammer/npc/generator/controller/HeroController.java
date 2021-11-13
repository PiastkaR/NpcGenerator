package com.warhammer.npc.generator.controller;

import com.warhammer.npc.generator.hero.Hero;
import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.service.CharacterCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
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

}
