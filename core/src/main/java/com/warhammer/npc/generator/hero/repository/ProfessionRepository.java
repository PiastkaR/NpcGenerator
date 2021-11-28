package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.model.Profession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
}
