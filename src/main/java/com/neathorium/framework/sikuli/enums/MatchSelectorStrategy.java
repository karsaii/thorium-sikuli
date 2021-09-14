package com.neathorium.framework.sikuli.enums;

import com.neathorium.core.extensions.namespaces.EnumExtensionFunctions;

import java.util.HashMap;
import java.util.Map;

public enum MatchSelectorStrategy {
    TEXT("text"),
    IMAGE("image"),
    DEFAULT("image");

    private static final Map<String, MatchSelectorStrategy> VALUES = new HashMap<>();
    private final String name;

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
