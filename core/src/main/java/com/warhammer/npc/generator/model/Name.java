package com.warhammer.npc.generator.model;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("name")
@AllArgsConstructor
@Getter
public class Name implements Serializable {
    private static final long serialVersionUID = -7817224776021728682L;

    @Id
    @Indexed
    private Long id;
    private String name;
    private Gender gender;
    private Race race;

    @Override
    public String toString() {
        return '\n' + "Name{" +
                "id=" + id +
                ", name=" + name +
                ", gender=" + gender +
                ", race=" + race +
                '}';
    }
}
