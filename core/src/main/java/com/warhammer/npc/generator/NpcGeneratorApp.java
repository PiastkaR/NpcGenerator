package com.warhammer.npc.generator;

import com.warhammer.npc.generator.service.CharacterCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NpcGeneratorApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NpcGeneratorApp.class, args);

        CharacterCreator cc = new CharacterCreator();
        cc.createHero();
        run.close();
    }
}
