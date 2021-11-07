package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.model.Name;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends CrudRepository <Name, Long> {

//     void saveAll(Map<Integer,Name> map);

//     @Override
//     void saveAll(Name... name);
}
