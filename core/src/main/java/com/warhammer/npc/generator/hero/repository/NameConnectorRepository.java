package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.model.NameConnector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameConnectorRepository extends CrudRepository<NameConnector,Long> {
}
