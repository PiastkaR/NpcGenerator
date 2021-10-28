package com.warhammer.npc.generator.model;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("nickname")
@AllArgsConstructor
public class Nickname implements Serializable {
    private static final long serialVersionUID = 3907764199588983014L;

    private Integer id;
    private String nickname;

    @Override
    public String toString() {
        return '\n' + "Name{" +
                "id='" + id +
                ", nickname=" + nickname  +
                '}';
    }
}
