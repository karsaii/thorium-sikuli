package com.neathorium.framework.sikuli.environment.enums;

import com.neathorium.framework.sikuli.environment.constants.ScreenKeyConstants;

import java.util.HashMap;
import java.util.Map;

public enum ScreenKey {
    ALL(ScreenKeyConstants.ALL),
    FIRST(ScreenKeyConstants.FIRST),
    DEFAULT(ScreenKeyConstants.ALL);

    private static final Map<String, ScreenKey> VALUES = new HashMap<>();
    private final String name;

    static {
        for(ScreenKey getter : values()) {
            VALUES.putIfAbsent(getter.name, getter);
        }
    }

    ScreenKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ScreenKey getValueOf(String name) {
        return VALUES.getOrDefault(name, ScreenKey.ALL);
    }
}
