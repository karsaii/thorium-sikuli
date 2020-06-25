package com.github.karsaii.framework.sikuli.enums;

import java.util.HashMap;
import java.util.Map;

public enum ManyMatchesGetter {
    GET_ELEMENTS("getElements"),
    GET_NESTED_ELEMENTS_FROM_LAST("getNestedElementsFromLast"),
    DEFAULT("getElements");

    private static final Map<String, ManyMatchesGetter> VALUES = new HashMap<>();
    private final String name;

    static {
        for(ManyMatchesGetter getter : values()) {
            VALUES.putIfAbsent(getter.name, getter);
        }
    }

    ManyMatchesGetter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ManyMatchesGetter getValueOf(String name) {
        return VALUES.getOrDefault(name, ManyMatchesGetter.GET_ELEMENTS);
    }
}
