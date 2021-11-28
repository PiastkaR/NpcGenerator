package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.hero.abilities.Ability;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends CrudRepository<Ability, Long> {
}
