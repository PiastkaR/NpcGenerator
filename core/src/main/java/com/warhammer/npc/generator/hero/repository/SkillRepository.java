package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.hero.skills.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
}
