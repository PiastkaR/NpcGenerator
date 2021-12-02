package com.warhammer.npc.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class BasicSkill {

    private final String name;
    private final int number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicSkill)) return false;
        BasicSkill that = (BasicSkill) o;
        return number == that.number && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    @Override
    public String toString() {
        return "BasicSkill{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
