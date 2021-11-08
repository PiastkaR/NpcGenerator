package com.warhammer.npc.generator.model;

import com.warhammer.npc.generator.hero.description.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("birthplace")
@AllArgsConstructor
@Getter
public class Birthplace {

    @Id
    private Long id;
    private String place;
    private Race race;

    @Override
    public String toString() {
        return '\n' + "Birthplace{" +
                "id=" + id +
                ", place=" + place +
                ", race=" + race +
                '}';
    }

}
