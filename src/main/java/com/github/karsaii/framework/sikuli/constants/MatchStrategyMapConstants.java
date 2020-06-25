package com.github.karsaii.framework.sikuli.constants;

import com.github.karsaii.framework.sikuli.enums.MatchSelectorStrategy;
import com.github.karsaii.framework.sikuli.namespaces.match.MatchStrategyFunctions;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.Map.entry;

public abstract class MatchStrategyMapConstants {
    public static final Map<MatchSelectorStrategy, Function<String, String>> STRATEGY_MAP = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(MatchSelectorStrategy.TEXT, MatchStrategyFunctions::getTextLocator),
                entry(MatchSelectorStrategy.IMAGE, MatchStrategyFunctions::getImageLocator)
            )
        )
    );

    public static final Set<MatchSelectorStrategy> STRATEGY_MAP_KEY_SET = EnumSet.allOf(MatchSelectorStrategy.class);

}
