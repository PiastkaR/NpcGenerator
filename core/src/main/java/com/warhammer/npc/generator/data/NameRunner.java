package com.warhammer.npc.generator.data;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.NameRepository;
import com.warhammer.npc.generator.model.Name;
import com.warhammer.npc.generator.model.Nickname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class NameRunner implements CommandLineRunner {

    @Autowired
    private NameRepository nameRepository;

    @Override
    public void run(String... args) throws Exception {
        Name johan = new Name(1, "Johan", new Nickname(1, "Albreht"), null, Gender.MALE, Race.HUMAN);
        Name adam = new Name(2, "Adam", new Nickname(2, "Zawadzki"), null, Gender.MALE, Race.HUMAN);
        Name ewa = new Name(3, "Ewa", new Nickname(3, "Sikora"), null, Gender.FEMALE, Race.HUMAN);
        List<Name> names = Arrays.asList(johan, adam, ewa);
        nameRepository.saveAll(names);

        System.out.println("All male names: " + nameRepository.findAll());
    }

}
