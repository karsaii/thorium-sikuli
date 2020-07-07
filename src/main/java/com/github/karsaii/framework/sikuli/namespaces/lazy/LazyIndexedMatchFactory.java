package com.github.karsaii.framework.sikuli.namespaces.lazy;

import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorListFactory;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.sikuli.namespaces.match.MatchFilterFunctions;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.MatchFilterData;
import org.sikuli.script.Match;

import static com.github.karsaii.framework.core.constants.lazy.CommonLazyIndexedFactoryConstants.PROBABILITY;
import static com.github.karsaii.framework.core.constants.lazy.CommonLazyIndexedFactoryConstants.FIRST_INDEX;
import static com.github.karsaii.framework.sikuli.constants.LazyIndexedMatchFactoryConstants.GETTER;

public interface LazyIndexedMatchFactory {
    static LazyFilteredMatchParameters getWithFilterDataAndLocatorList(MatchFilterData<?> data, double probability, LazyLocatorList lazyLocators, String getter) {
        return new LazyFilteredMatchParameters(data, data.isFiltered ? Match.class : MatchList.class, probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(new MatchFilterData<>(isFiltered, MatchFilterFunctions::getContainedTextElement, message), probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(new MatchFilterData<>(isFiltered, MatchFilterFunctions::getIndexedElement, index), probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocatorList(MatchFilterData<?> data, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterDataAndLocatorList(data, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocatorList(MatchFilterData<?> data, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(data, PROBABILITY, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocatorList(MatchFilterData<?> data, LazyLocatorList lazyLocators) {
        return getWithFilterDataAndLocatorList(data, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocator(MatchFilterData<?> data, double probability, LazyLocator lazyLocator) {
        return getWithFilterDataAndLocatorList(data, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocator(MatchFilterData<?> data, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterDataAndLocatorList(data, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocator(MatchFilterData<?> data, LazyLocator lazyLocator, String getter) {
        return getWithFilterDataAndLocatorList(data, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocator(MatchFilterData<?> data, LazyLocator lazyLocator) {
        return getWithFilterDataAndLocatorList(data, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(int index, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, index, probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(int index, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, index, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(int index, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, index, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, PROBABILITY, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(int index, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(index, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(int index, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(index, probability, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, FIRST_INDEX, probability, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(int index, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(index, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, FIRST_INDEX, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(int index, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(index, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(String message, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, message, probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(String message, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, message, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(String message, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, message, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(isFiltered, message, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, message, probability, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, message, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(String message, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(true, message, probability, lazyLocator, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(String message, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(message, probability, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(String message, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(message, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(String message, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(message, PROBABILITY, lazyLocator, GETTER);
    }
}
