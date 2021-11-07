package com.warhammer.npc.generator.controller;

import com.warhammer.npc.generator.model.FullName;
import com.warhammer.npc.generator.service.CharacterCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroController {

    private final CharacterCreatorService creatorService;

    @GetMapping(path = "/generate")
    @ResponseBody
    public FullName generateHero(@RequestParam String race, @RequestParam String gender) {
        return creatorService.getFullName(race, gender);
    }

}
