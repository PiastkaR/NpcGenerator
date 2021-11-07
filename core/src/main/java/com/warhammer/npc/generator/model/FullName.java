package com.warhammer.npc.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.util.annotation.Nullable;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class FullName implements Serializable {

    private String baseName;
    private String nickname;
    @Nullable
    private String connector;

    @Override
    public String toString() {
        return '\n' + "FullName {" +
                ", name=" + baseName +
                ", name=" + nickname +
                ", name=" + connector +
                '}';
    }
}
