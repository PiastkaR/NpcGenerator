package com.warhammer.npc.generator.model;

import com.warhammer.npc.generator.hero.description.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("nickname")
@AllArgsConstructor
@Getter
public class Nickname implements Serializable {
    private static final long serialVersionUID = 3907764199588983014L;

    @Id
    private Long id;
    private String nickname;
    private Race raceNickname;

    @Override
    public String toString() {
        return '\n' + "Nickname{" +
                "id=" + id +
                ", nickname=" + nickname  +
                ", raceNickname=" + raceNickname  +
                '}';
    }

}
