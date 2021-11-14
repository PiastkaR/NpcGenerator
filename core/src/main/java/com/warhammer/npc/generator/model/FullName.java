package com.warhammer.npc.generator.model;

import com.warhammer.npc.generator.hero.description.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.util.annotation.Nullable;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class FullName implements Serializable {

    private String baseName;
    private String nickname;
    @Nullable
    private String connector;

    public String getCorrectName(Race race) {
        if (race == Race.ELF) {
            return baseName + " " + connector + " " + nickname;
        } else
            return baseName + " " + nickname;
    }

    @Override
    public String toString() {
        return '\n' + "FullName {" +
                ", name=" + baseName +
                ", name=" + nickname +
                ", name=" + connector +
                '}';
    }
}
