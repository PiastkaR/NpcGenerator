package com.warhammer.npc.generator.hero.repository;

import com.warhammer.npc.generator.model.Nickname;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NicknameRepository extends CrudRepository<Nickname, Integer> {
}
