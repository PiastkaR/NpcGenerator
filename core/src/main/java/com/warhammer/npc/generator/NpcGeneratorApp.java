package com.warhammer.npc.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NpcGeneratorApp {

    public static void main(String[] args) {
        SpringApplication.run(NpcGeneratorApp.class, args);
//        ConfigurableApplicationContext run = SpringApplication.run(NpcGeneratorApp.class, args);
//
//        run.close();
    }
}
