package com.warhammer.npc.generator.model;

import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.description.CharactersClass;
import com.warhammer.npc.generator.hero.description.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;


@AllArgsConstructor
@Getter
@RedisHash("profession")
public class Profession {

    @Id
    private final Long id;
    private final String description;
    private final CharactersClass charactersClass;
    private final List<Race> racesProfession;
    private final MainCharacteristics mainCharacteristicsDevelopment;
    private final SecondaryCharacteristics secondaryCharacteristicsDevelopment;
    private final List<String> equipment;
    private final List<String> skills;
    private final List<String> abilities;

    @Override
    public String toString() {
        return "Profession{" +
                "description='" + description + '\'' +
                ", charactersClass=" + charactersClass +
                ", racesProfession=" + racesProfession +
                ", mainCharacteristicsDevelopment=" + mainCharacteristicsDevelopment +
                ", secondaryCharacteristicsDevelopment=" + secondaryCharacteristicsDevelopment +
                ", equipment=" + equipment +
                ", skills=" + skills +
                ", abilities=" + abilities +
                '}';
    }
}
