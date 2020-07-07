package com.github.karsaii.framework.sikuli.namespaces.factories;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.framework.core.namespaces.validators.Invalids;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
import com.github.karsaii.framework.sikuli.enums.ManyMatchesGetter;
import com.github.karsaii.framework.sikuli.enums.SingleMatchGetter;
import com.github.karsaii.framework.sikuli.namespaces.lazy.LazyIndexedMatchFactory;
import com.github.karsaii.framework.sikuli.namespaces.match.validators.MatchParameters;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatch;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.MatchFilterData;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

import static com.github.karsaii.framework.sikuli.namespaces.SikuliUtilities.getEntry;
import static com.github.karsaii.framework.sikuli.namespaces.SikuliUtilities.getEntryIndexed;
import static java.util.Map.entry;

public interface LazyMatchFactory {
    static <T> LazyMatch getWith(String name, Map<String, LazyFilteredMatchParameters> parameters, Predicate<LazyFilteredMatchParameters> validator) {
        return new LazyMatch(name, parameters, validator);
    }

    static <T> LazyMatch getWithDefaultValidator(String name, Map<String, LazyFilteredMatchParameters> parameters) {
        return getWith(name, parameters, Invalids::defaultFalseValidator);
    }
    
    static <T> LazyMatch getWithDefaultLocatorsAndValidator(String name) {
        return getWith(name, new HashMap<>(), Invalids::defaultFalseValidator);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isIndexed, int index, LazyLocator locator, String getter) {
        final var lep = LazyIndexedMatchFactory.getWithFilterParametersAndLocator(isIndexed, index, locator, getter);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(entry(locator.strategy, lep)));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return getWith(name, synchronizedMap, MatchParameters::isValidLazyFilteredElement);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isFiltered, String message, LazyLocator locator, String getter) {
        final var lep = LazyIndexedMatchFactory.getWithFilterParametersAndLocator(isFiltered, message, locator, getter);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(entry(locator.strategy, lep)));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return getWith(name, synchronizedMap, MatchParameters::isValidLazyFilteredElement);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isFiltered, double similarity, LazyLocator locator, String getter) {
        final var lep = LazyIndexedMatchFactory.getWithFilterParametersAndLocator(isFiltered, similarity, locator, getter);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(entry(locator.strategy, lep)));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return getWith(name, synchronizedMap, MatchParameters::isValidLazyFilteredElement);
    }

    static LazyMatch getWithFilterParameters(String locator, SingleMatchGetter getter) {
        final var lep = getEntry(LazyIndexedMatchFactory::getWithFilterParametersAndLocator, locator, getter.getName(), false);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(lep));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return getWith(CoreUtilities.getIncrementalUUID(SikuliCoreConstants.ATOMIC_COUNT), synchronizedMap, MatchParameters::isValidLazyFilteredElement);
    }

    static LazyMatch getWithFilterParameters(MatchFilterData<Integer> elementFilterData, String locator, ManyMatchesGetter getter) {
        final var lep = getEntryIndexed(LazyIndexedMatchFactory::getWithFilterDataAndLocator, elementFilterData, locator, getter.getName());
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(lep));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return getWith(CoreUtilities.getIncrementalUUID(SikuliCoreConstants.ATOMIC_COUNT), synchronizedMap, MatchParameters::isValidLazyFilteredElement);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isIndexed, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, isIndexed, 0, locator, getter);
    }

    static LazyMatch getWithFilterParameters(String name, int index, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, true, index, locator, getter);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isIndexed, int index, LazyLocator locator) {
        return getWithFilterParameters(name, isIndexed, index, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, boolean isIndexed, LazyLocator locator) {
        return getWithFilterParameters(name, isIndexed, 0, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, int index, LazyLocator locator) {
        return getWithFilterParameters(name, true, index, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, LazyLocator locator) {
        return getWithFilterParameters(name, true, 0, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String locator) {
        return getWithFilterParameters(locator, SingleMatchGetter.DEFAULT);
    }

    static LazyMatch getWithFilterParameters(String name, String message, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, true, message, locator, getter);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isFiltered, String message, LazyLocator locator) {
        return getWithFilterParameters(name, isFiltered, message, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, String message, LazyLocator locator) {
        return getWithFilterParameters(name, true, message, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, double similarity, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, true, similarity, locator, getter);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isFiltered, double similarity, LazyLocator locator) {
        return getWithFilterParameters(name, isFiltered, similarity, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, double similarity, LazyLocator locator) {
        return getWithFilterParameters(name, true, similarity, locator, SingleMatchGetter.DEFAULT.getName());
    }
}
