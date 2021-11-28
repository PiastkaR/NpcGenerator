package com.warhammer.npc.generator.hero.skills;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@RedisHash("skill")
@RequiredArgsConstructor
@Setter
@Getter
public class Skill {

    @Id
    private final Long id;
    private final String name;
    private final String description;

    @Override
    public String toString() {
        return '\n' + "Skill{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) && Objects.equals(name, skill.name) && Objects.equals(description, skill.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
