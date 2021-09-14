package com.neathorium.framework.sikuli.constants;

import com.neathorium.framework.sikuli.enums.MatchSelectorStrategy;
import com.neathorium.framework.sikuli.namespaces.match.MatchStrategyFunctions;
import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.Map.entry;

public abstract class MatchStrategyMapConstants {
    public static final Map<MatchSelectorStrategy, Function<LazyMatchLocator, Function<Region, List<Match>>>> STRATEGY_MAP = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(MatchSelectorStrategy.TEXT, MatchStrategyFunctions::getAllByText),
                entry(MatchSelectorStrategy.IMAGE, MatchStrategyFunctions::getAllByImage)
            )
        )
    );

    public static final Set<String> STRATEGY_MAP_KEY_SET = new HashSet<>(Arrays.asList(
        MatchSelectorStrategy.TEXT.getName(),
        MatchSelectorStrategy.IMAGE.getName()
    ));
}
