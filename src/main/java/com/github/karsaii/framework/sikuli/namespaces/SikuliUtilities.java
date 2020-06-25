package com.github.karsaii.framework.sikuli.namespaces;

import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.FrameworkCoreUtilities;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.sikuli.constants.MatchStrategyMapConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliDataConstants;
import com.github.karsaii.framework.sikuli.enums.MatchSelectorStrategy;
import com.github.karsaii.framework.sikuli.namespaces.lazy.LazyIndexedMatchFactory;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.MatchFilterData;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public interface SikuliUtilities {
    static Data<String> getLocator(Map<MatchSelectorStrategy, Function<String, String>> strategyMap, LazyLocator data) {
        if (FrameworkCoreUtilities.isNullLazyLocator(data)) {
            return SikuliDataConstants.NULL_LOCATOR;
        }

        final var locator = data.locator;
        final var strategy = data.strategy;
        return DataFactoryFunctions.getWithMessage(strategyMap.get(MatchSelectorStrategy.getValueOf(strategy)).apply(locator), true, "Locator: By " + strategy + " with locator: " + locator);
    }

    static Data<String> getLocator(LazyLocator data) {
        return getLocator(MatchStrategyMapConstants.STRATEGY_MAP, data);
    }

    static <T> Map<T, LazyFilteredMatchParameters> getParametersCopy(Map<T, LazyFilteredMatchParameters> source) {
        final var keys = source.keySet().iterator();
        final var values = source.values().iterator();

        final var map = Collections.synchronizedMap(new LinkedHashMap<T, LazyFilteredMatchParameters>());
        LazyFilteredMatchParameters lep;
        while(keys.hasNext() && values.hasNext()) {
            lep = values.next();
            map.putIfAbsent(keys.next(), LazyIndexedMatchFactory.getWithFilterDataAndLocatorList((MatchFilterData<?>) lep.elementFilterData, lep.probability, lep.lazyLocators, lep.getter));
        }

        return map;
    }

}
