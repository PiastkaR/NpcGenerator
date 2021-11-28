package com.warhammer.npc.generator.model;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.Objects;

@RedisHash("weapon")
@Getter
public class Weapon extends Equipment {

    private final String category;
    private final String strength;
    private final String range;
    private final String reload;
    private final List<Characteristic> characteristics;

    public Weapon(Long id, String name, int load, String category, String strength, String range, String reload, List<Characteristic> characteristics) {
        super(id, name, load);
        this.category = category;
        this.strength = strength;
        this.range = range;
        this.reload = reload;
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "category='" + category + '\'' +
                ", strength='" + strength + '\'' +
                ", range='" + range + '\'' +
                ", reload='" + reload + '\'' +
                ", characteristics=" + characteristics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weapon)) return false;
        if (!super.equals(o)) return false;
        Weapon weapon = (Weapon) o;
        return Objects.equals(category, weapon.category) && Objects.equals(strength, weapon.strength) && Objects.equals(range, weapon.range) && Objects.equals(reload, weapon.reload) && Objects.equals(characteristics, weapon.characteristics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, strength, range, reload, characteristics);
    }
}
