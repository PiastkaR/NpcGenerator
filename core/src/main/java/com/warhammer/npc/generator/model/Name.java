package com.warhammer.npc.generator.model;

import com.warhammer.npc.generator.hero.description.Gender;
import com.warhammer.npc.generator.hero.description.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import reactor.util.annotation.Nullable;

import java.io.Serializable;

@RedisHash("name")
@AllArgsConstructor
@Getter
public class Name implements Serializable {
    private static final long serialVersionUID = -7817224776021728682L;

    private Integer id;
    private String name;
    private Nickname nickname;
    @Nullable
    private NameConnector connector;
    private Gender gender;
    private Race race;

    @Override
    public String toString() {
        return '\n' + "Name{" +
                "id='" + id +
                ", name=" + name +
                ", name=" + nickname +
                ", name=" + connector +
                ", gender='" + gender +
                ", race=" + race +
                '}';
    }
}
