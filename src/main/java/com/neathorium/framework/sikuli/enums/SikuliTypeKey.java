package com.neathorium.framework.sikuli.enums;

import java.util.HashMap;
import java.util.Map;

public enum SikuliTypeKey {
    BOOLEAN("Boolean"),
    INTEGER("Integer"),
    STRING("String"),
    OBJECT("Object"),
    VOID("Void"),
    MATCH("Match"),
    MATCH_LIST("MatchList"),
    STRING_SET("StringSet"),
    DEFAULT("Void");

    private static final Map<String, SikuliTypeKey> VALUES = new HashMap<>();
    private String name;

    SikuliTypeKey(String name) {
        this.name = name;
    }

    static {
        for(var getter : values()) {
            VALUES.putIfAbsent(getter.name, getter);
        }
    }
    public String getName() {
        return name;
    }

    public static SikuliTypeKey getValueOf(String name) {
        return VALUES.getOrDefault(name, SikuliTypeKey.VOID);
    }
}
