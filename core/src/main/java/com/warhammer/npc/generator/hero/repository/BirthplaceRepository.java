package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.model.Birthplace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthplaceRepository extends CrudRepository<Birthplace, Long> {
}
