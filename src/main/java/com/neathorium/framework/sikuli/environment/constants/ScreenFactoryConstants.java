package com.neathorium.framework.sikuli.environment.constants;

import com.neathorium.framework.sikuli.environment.enums.ScreenKey;
import com.neathorium.framework.sikuli.environment.namespaces.ScreenFactory;
import org.sikuli.script.Region;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.Map.entry;

public abstract class ScreenFactoryConstants {
    public static final int CONTINUOUS_SCANNING_VALUE = 101;
    public static final Map<ScreenKey, Supplier<Region>> SCREEN_BUILD_MAP = Collections.unmodifiableMap(
        new EnumMap<ScreenKey, Supplier<Region>>(
            Map.ofEntries(
                entry(ScreenKey.ALL, ScreenFactory::getAllScreens),
                entry(ScreenKey.FIRST, ScreenFactory::getFirstScreen),
                entry(ScreenKey.DEFAULT, ScreenFactory::getAllScreens)
            )
        )
    );
}
