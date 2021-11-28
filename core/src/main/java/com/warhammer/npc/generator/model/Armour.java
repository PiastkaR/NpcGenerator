package com.warhammer.npc.generator.model;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@Getter
@RedisHash("armour")
public class Armour extends Equipment {

    private final String location;
    private final int defense;

    public Armour(Long id, String name, int load, String location, int defense) {
        super(id, name, load);
        this.location = location;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Armour{" +
                "location='" + location + '\'' +
                ", defense=" + defense +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Armour)) return false;
        if (!super.equals(o)) return false;
        Armour armour = (Armour) o;
        return defense == armour.defense && Objects.equals(location, armour.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), location, defense);
    }
}
