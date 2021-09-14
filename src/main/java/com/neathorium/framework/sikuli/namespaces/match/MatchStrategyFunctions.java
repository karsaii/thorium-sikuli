package com.neathorium.framework.sikuli.namespaces.match;

import com.neathorium.framework.sikuli.namespaces.lazy.LazyMatchLocatorFunctions;
import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import java.util.List;
import java.util.function.Function;

public interface MatchStrategyFunctions {
    static Function<Region, List<Match>> getAllByImage(LazyMatchLocator target) {
        return region -> region.findAllList(LazyMatchLocatorFunctions.getPatternFromLocator(target));
    }

    static Function<Region, List<Match>> getAllByText(LazyMatchLocator target) {
        return region -> region.findAllText(LazyMatchLocatorFunctions.getTextFromLocator(target));
    }
}
