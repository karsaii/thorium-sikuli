package com.neathorium.framework.sikuli.namespaces.lazy.factories;

import com.neathorium.framework.sikuli.enums.SingleMatchGetter;
import com.neathorium.framework.sikuli.namespaces.match.validators.MatchParameters;
import com.neathorium.framework.sikuli.records.lazy.LazyMatch;
import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;
import com.neathorium.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.neathorium.framework.core.namespaces.validators.Invalids;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

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

    static LazyMatch getWithFilterParameters(String name, boolean isIndexed, int index, LazyMatchLocator locator, String getter) {
        final var lep = LazyIndexedMatchFactory.getWithFilterParametersAndLocator(isIndexed, index, locator, getter);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(entry(locator.STRATEGY, lep)));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return getWith(name, synchronizedMap, MatchParameters::isValidLazyFilteredElement);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isFiltered, String message, LazyMatchLocator locator, String getter) {
        final var lep = LazyIndexedMatchFactory.getWithFilterParametersAndLocator(isFiltered, message, locator, getter);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(entry(locator.STRATEGY, lep)));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return getWith(name, synchronizedMap, MatchParameters::isValidLazyFilteredElement);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isFiltered, double similarity, LazyMatchLocator locator, String getter) {
        final var lep = LazyIndexedMatchFactory.getWithFilterParametersAndLocator(isFiltered, similarity, locator, getter);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(entry(locator.STRATEGY, lep)));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return getWith(name, synchronizedMap, MatchParameters::isValidLazyFilteredElement);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isIndexed, LazyMatchLocator locator, String getter) {
        return getWithFilterParameters(name, isIndexed, 0, locator, getter);
    }

    static LazyMatch getWithFilterParameters(String name, int index, LazyMatchLocator locator, String getter) {
        return getWithFilterParameters(name, true, index, locator, getter);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isIndexed, int index, LazyMatchLocator locator) {
        return getWithFilterParameters(name, isIndexed, index, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, boolean isIndexed, LazyMatchLocator locator) {
        return getWithFilterParameters(name, isIndexed, 0, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, int index, LazyMatchLocator locator) {
        return getWithFilterParameters(name, true, index, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, LazyMatchLocator locator) {
        return getWithFilterParameters(name, true, 0, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, String message, LazyMatchLocator locator, String getter) {
        return getWithFilterParameters(name, true, message, locator, getter);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isFiltered, String message, LazyMatchLocator locator) {
        return getWithFilterParameters(name, isFiltered, message, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, String message, LazyMatchLocator locator) {
        return getWithFilterParameters(name, true, message, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, double similarity, LazyMatchLocator locator, String getter) {
        return getWithFilterParameters(name, true, similarity, locator, getter);
    }

    static LazyMatch getWithFilterParameters(String name, boolean isFiltered, double similarity, LazyMatchLocator locator) {
        return getWithFilterParameters(name, isFiltered, similarity, locator, SingleMatchGetter.DEFAULT.getName());
    }

    static LazyMatch getWithFilterParameters(String name, double similarity, LazyMatchLocator locator) {
        return getWithFilterParameters(name, true, similarity, locator, SingleMatchGetter.DEFAULT.getName());
    }
}
