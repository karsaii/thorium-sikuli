package com.neathorium.framework.sikuli.namespaces.lazy.factories;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.LazyMatchLocatorList;
import com.neathorium.framework.sikuli.namespaces.match.MatchFilterFunctions;
import com.neathorium.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;
import com.neathorium.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.neathorium.framework.sikuli.records.lazy.filtered.MatchFilterData;
import com.neathorium.core.extensions.constants.IExtendedListConstants;
import org.sikuli.script.Match;

import static com.neathorium.framework.sikuli.constants.LazyIndexedMatchFactoryConstants.GETTER;
import static com.neathorium.framework.core.constants.lazy.CommonLazyIndexedFactoryConstants.PROBABILITY;

public interface LazyIndexedMatchFactory {
    static LazyFilteredMatchParameters getWithFilterDataAndLocatorList(MatchFilterData<?> data, double probability, LazyMatchLocatorList lazyLocators, String getter) {
        return new LazyFilteredMatchParameters(data, data.isFiltered ? Match.class : MatchList.class, probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, double probability, LazyMatchLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(new MatchFilterData<>(isFiltered, MatchFilterFunctions::getContainedTextElement, message), probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, double probability, LazyMatchLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(new MatchFilterData<>(isFiltered, MatchFilterFunctions::getIndexedElement, index), probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocatorList(MatchFilterData<?> data, double probability, LazyMatchLocatorList lazyLocators) {
        return getWithFilterDataAndLocatorList(data, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocatorList(MatchFilterData<?> data, LazyMatchLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(data, PROBABILITY, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocatorList(MatchFilterData<?> data, LazyMatchLocatorList lazyLocators) {
        return getWithFilterDataAndLocatorList(data, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocator(MatchFilterData<?> data, double probability, LazyMatchLocator lazyLocator) {
        return getWithFilterDataAndLocatorList(data, probability, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocator(MatchFilterData<?> data, double probability, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterDataAndLocatorList(data, probability, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocator(MatchFilterData<?> data, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterDataAndLocatorList(data, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterDataAndLocator(MatchFilterData<?> data, LazyMatchLocator lazyLocator) {
        return getWithFilterDataAndLocatorList(data, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, double probability, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(int index, double probability, LazyMatchLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, index, probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, double probability, LazyMatchLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, IExtendedListConstants.FIRST_INDEX, probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, double probability, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, IExtendedListConstants.FIRST_INDEX, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(int index, double probability, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, index, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, IExtendedListConstants.FIRST_INDEX, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(int index, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, index, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, double probability, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, IExtendedListConstants.FIRST_INDEX, PROBABILITY, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, double probability, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(int index, double probability, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(index, probability, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, double probability, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, probability, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(int index, double probability, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocator(index, probability, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, double probability, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, IExtendedListConstants.FIRST_INDEX, probability, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(int index, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(index, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, IExtendedListConstants.FIRST_INDEX, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(int index, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocator(index, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, double probability, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(String message, double probability, LazyMatchLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, message, probability, lazyLocators, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(String message, double probability, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, message, probability, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocatorList(String message, LazyMatchLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, message, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, double probability, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, LazyMatchLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(isFiltered, message, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, double probability, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, message, probability, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, message, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(String message, double probability, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(true, message, probability, lazyLocator, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(String message, double probability, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocator(message, probability, lazyLocator, GETTER);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(String message, LazyMatchLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(message, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredMatchParameters getWithFilterParametersAndLocator(String message, LazyMatchLocator lazyLocator) {
        return getWithFilterParametersAndLocator(message, PROBABILITY, lazyLocator, GETTER);
    }
}
