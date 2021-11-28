package com.warhammer.npc.generator.hero.abilities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@RedisHash("ability")
@RequiredArgsConstructor
@Getter
public class Ability {

    @Id
    private final Long id;
    private final String name;
    private final String description;

    @Override
    public String toString() {
        return '\n' + "Ability{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ability)) return false;
        Ability ability = (Ability) o;
        return Objects.equals(id, ability.id) && Objects.equals(name, ability.name) && Objects.equals(description, ability.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
