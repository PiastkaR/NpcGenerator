package com.warhammer.npc.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("birthplace")
@AllArgsConstructor
@Getter
public class Birthplace {

    @Id
    private Long id;
    private Region region;
    private String place;

    @AllArgsConstructor
    private enum Region {
        AVERLAND(0),
        HOCHLAND(1),
        NORDLAND(2),
        OSTLAND(3),
        REIKLAND(4),
        WISSENLAND(5),
        STIRLAND(6),
        OSTERMARK(7),
        MIDDENLAND(8);

        private final int option;
    }
}
