package com.github.karsaii.framework.sikuli.namespaces;

import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.extensions.namespaces.EmptiableFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.FrameworkCoreUtilities;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.sikuli.constants.MatchStrategyMapConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliDataConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliFormatterConstants;
import com.github.karsaii.framework.sikuli.enums.MatchSelectorStrategy;
import com.github.karsaii.framework.sikuli.namespaces.factories.MatchLazyLocatorFactory;
import com.github.karsaii.framework.sikuli.namespaces.lazy.LazyIndexedMatchFactory;
import com.github.karsaii.framework.sikuli.namespaces.predicates.FindFailedPredicates;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatch;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.MatchFilterData;
import org.sikuli.script.FindFailed;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAll;
import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAnyNull;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNull;
import static java.util.Map.entry;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface SikuliUtilities {
    static boolean isInvalidLazyLocator(LazyLocator data) {
        return isNull(data) || isBlank(data.locator) || isNull(data.strategy);
    }

    static boolean areNullLazyData(LazyLocator... data) {
        return areAll(SikuliUtilities::isInvalidLazyLocator, data);
    }

    static LazyLocator[] getEmptyLazyLocatorArray() {
        return new LazyLocator[0];
    }

    static boolean areNullLazyData(List<LazyLocator> data) {
        return areNullLazyData(data.toArray(getEmptyLazyLocatorArray()));
    }

    static boolean isNullLazyDataList(LazyLocatorList list) {
        return EmptiableFunctions.isNullOrEmpty(list) || areNullLazyData(list.list);
    }

    static boolean isNotNullLazyData(LazyLocator data) {
        return !isInvalidLazyLocator(data);
    }

    static <T> boolean isNullAbstractLazyElementParametersList(Collection<T> data, Predicate<T> validator) {
        for(T params : data) {
            if (validator.test(params)) {
                return false;
            }
        }

        return true;
    }

    static boolean isNullLazyMatch(LazyMatch element) {
        return (
            isNull(element) ||
            isBlank(element.name) ||
            areAnyNull(element.parameters, element.validator) ||
            element.parameters.isEmpty() ||
            isNullAbstractLazyElementParametersList(element.parameters.values(), element.validator)
        );
    }

    static boolean isNotNullLazyElement(LazyMatch element) {
        return !isNullLazyMatch(element);
    }

    static Data<String> getLocator(Map<MatchSelectorStrategy, Function<String, String>> strategyMap, LazyLocator data) {
        if (FrameworkCoreUtilities.isNullLazyLocator(data)) {
            return SikuliDataConstants.NULL_LOCATOR;
        }

        final var locator = data.locator;
        final var strategy = data.strategy;
        return DataFactoryFunctions.getWith(strategyMap.get(MatchSelectorStrategy.getValueOf(strategy)).apply(locator), true, "Locator: By " + strategy + " with locator: " + locator);
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

    static <T> List<T> iteratorToList(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false).collect(Collectors.toList());
    }

    static FindFailed toFindFailed(RuntimeException ex) {
        final var message = ex.getLocalizedMessage();
        return new FindFailed(FindFailedPredicates.isFindFailed(ex) ? message : SikuliFormatterConstants.NON_FIND_ALL_EXCEPTION + message);
    }

    static <T> Map.Entry<String, T> getEntry(TriFunction<Boolean, LazyLocator, String, T> constructor, String locator, String getter, boolean isIndexed) {
        final var lazyLocator = MatchLazyLocatorFactory.get(locator);
        return entry(lazyLocator.strategy, constructor.apply(isIndexed, lazyLocator, getter));
    }

    static <T, V> Map.Entry<String, LazyFilteredMatchParameters> getEntryIndexed(TriFunction<MatchFilterData, LazyLocator, String, LazyFilteredMatchParameters> constructor, MatchFilterData<?> elementFilterData, String locator, String getter) {
        final var lazyLocator = MatchLazyLocatorFactory.get(locator);
        return entry(lazyLocator.strategy, constructor.apply(elementFilterData, lazyLocator, getter));
    }
}
