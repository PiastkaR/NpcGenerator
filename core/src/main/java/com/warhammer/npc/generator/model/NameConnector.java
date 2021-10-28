package com.warhammer.npc.generator.model;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("nameConnector")
@AllArgsConstructor
public class NameConnector implements Serializable {
    private static final long serialVersionUID = 3664975818184007096L;

    private Integer id;
    private String connector;

    @Override
    public String toString() {
        return '\n' + "Name{" +
                "id='" + id +
                ", connector=" + connector  +
                '}';
    }
}
