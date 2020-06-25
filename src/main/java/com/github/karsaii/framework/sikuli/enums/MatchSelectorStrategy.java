package com.github.karsaii.framework.sikuli.enums;

import com.github.karsaii.core.extensions.namespaces.EnumExtensionFunctions;

import java.util.HashMap;
import java.util.Map;

public enum MatchSelectorStrategy {
    TEXT("text"),
    IMAGE("image"),
    DEFAULT("image");

    private static final Map<String, MatchSelectorStrategy> VALUES = new HashMap<>();
    private String name;

    MatchSelectorStrategy(String name) {
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

    public static MatchSelectorStrategy getValueOf(String name) {
        return EnumExtensionFunctions.getOrDefault(VALUES, name, MatchSelectorStrategy.DEFAULT);
    }

    public static boolean contains(String name) {
        return EnumExtensionFunctions.contains(VALUES, name);
    }
}
