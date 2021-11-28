package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.model.Name2ndPart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Name2ndPartRepository extends CrudRepository<Name2ndPart, Long> {
}
