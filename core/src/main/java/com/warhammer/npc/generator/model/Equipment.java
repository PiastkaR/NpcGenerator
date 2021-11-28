package com.warhammer.npc.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Getter
@RedisHash("equipment")
public class Equipment implements Serializable {

    @Id
    private final Long id;
    private final String name;
    private final int load;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", load=" + load +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return load == equipment.load && Objects.equals(id, equipment.id) && Objects.equals(name, equipment.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, load);
    }
}
