package com.github.karsaii.framework.sikuli.enums;

import java.util.HashMap;
import java.util.Map;

public enum SingleMatchGetter {
    GET_ELEMENT("getElement"),
    GET_NESTED_ELEMENT("getNestedElement"),
    DEFAULT("getElement");

    private static final Map<String, SingleMatchGetter> VALUES = new HashMap<>();
    private final String name;

    SingleMatchGetter(String name) {
        this.name = name;
    }

    static {
        for(SingleMatchGetter getter : values()) {
            VALUES.putIfAbsent(getter.name, getter);
        }
    }
    public String getName() {
        return name;
    }

    public static SingleMatchGetter getValueOf(String name) {
        return VALUES.getOrDefault(name, SingleMatchGetter.DEFAULT);
    }
}
