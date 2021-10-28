package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.NameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class CharacterCreator {

    private final NameRepository nameRepository;

    public void createHero() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj rasę: " + Arrays.toString(Race.values()));
        int userRace = sc.nextInt();
        System.out.println("Podaj płeć: " + Arrays.toString(Gender.values()));
        int userGender = sc.nextInt();

//        return;
    }
}
