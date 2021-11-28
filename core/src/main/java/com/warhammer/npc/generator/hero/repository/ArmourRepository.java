package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.model.Armour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmourRepository extends CrudRepository<Armour, Long> {
}
