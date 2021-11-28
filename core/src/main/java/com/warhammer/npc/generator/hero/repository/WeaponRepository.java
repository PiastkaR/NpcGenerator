package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.model.Weapon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends CrudRepository<Weapon, Long> {
}
