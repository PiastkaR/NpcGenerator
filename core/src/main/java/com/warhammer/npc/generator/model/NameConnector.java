package com.warhammer.npc.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("nameConnector")
@AllArgsConstructor
@Getter
public class NameConnector implements Serializable {
    private static final long serialVersionUID = 3664975818184007096L;

    @Id
    private Long id;
    private String connector;

    @Override
    public String toString() {
        return '\n' + "Connector{" +
                "id=" + id +
                ", connector=" + connector  +
                '}';
    }
}
