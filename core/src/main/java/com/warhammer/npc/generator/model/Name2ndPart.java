package com.warhammer.npc.generator.model;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("name2ndPart")
@AllArgsConstructor
@Getter
public class Name2ndPart implements Serializable {

    @Id
    private Long id;
    private String name2ndPart;
    private Gender gender;
    private Race race;

    @Override
    public String toString() {
        return '\n' + "Name{" +
                "id=" + id +
                ", name=" + name2ndPart +
                ", gender=" + gender +
                ", race=" + race +
                '}';
    }
}
