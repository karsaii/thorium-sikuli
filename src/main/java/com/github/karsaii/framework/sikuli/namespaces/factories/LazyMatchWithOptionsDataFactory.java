package com.github.karsaii.framework.sikuli.namespaces.factories;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.constants.AdjusterConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliGetOrderConstants;
import com.github.karsaii.framework.core.constants.SelectorDataConstants;
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.core.records.ProbabilityData;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
import com.github.karsaii.framework.sikuli.records.ExternalSikuliSelectorData;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatch;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatchWithOptionsData;

public interface LazyMatchWithOptionsDataFactory {
    static LazyMatchWithOptionsData get(
            LazyMatch match,
            InternalSelectorData internalData,
            ExternalSikuliSelectorData externalData,
            DecoratedList<String> getOrder,
            ProbabilityData probabilityData
    ) {
        return new LazyMatchWithOptionsData(match, internalData, externalData, getOrder, probabilityData);
    }

    static LazyMatchWithOptionsData getWithDefaultProbabilityData(LazyMatch match, InternalSelectorData internalData, ExternalSikuliSelectorData externalData, DecoratedList<String> getOrder) {
        return get(match, internalData, externalData, getOrder, AdjusterConstants.PROBABILITY_DATA);
    }

    static LazyMatchWithOptionsData getWithDefaultGetOrder(LazyMatch match, InternalSelectorData internalData, ExternalSikuliSelectorData externalData, ProbabilityData probabilityData) {
        return get(match, internalData, externalData, SikuliGetOrderConstants.DEFAULT, probabilityData);
    }

    static LazyMatchWithOptionsData getWithDefaultGetOrderAndProbabilityData(LazyMatch match, InternalSelectorData internalData, ExternalSikuliSelectorData externalData) {
        return getWithDefaultProbabilityData(match, internalData, externalData, SikuliGetOrderConstants.DEFAULT);
    }

    static LazyMatchWithOptionsData getWithDefaultInternalSelectorDataGetOrderAndProbabilityData(LazyMatch match, ExternalSikuliSelectorData externalData) {
        return getWithDefaultGetOrderAndProbabilityData(match, SelectorDataConstants.INTERNAL_SELECTOR_DATA, externalData);
    }

    static LazyMatchWithOptionsData getWithDefaultInternalSelectorDataAndGetOrder(LazyMatch match, ExternalSikuliSelectorData externalData, DecoratedList<String> getOrder) {
        return getWithDefaultProbabilityData(match, SelectorDataConstants.INTERNAL_SELECTOR_DATA, externalData, getOrder);
    }

    static LazyMatchWithOptionsData getWithDefaultInternalSelectorDataAndProbabilityData(LazyMatch match, ExternalSikuliSelectorData externalData, ProbabilityData probabilityData) {
        return getWithDefaultGetOrder(match, SelectorDataConstants.INTERNAL_SELECTOR_DATA, externalData, probabilityData);
    }

    static LazyMatchWithOptionsData getWithSpecificLazyMatch(LazyMatch match) {
        return getWithDefaultInternalSelectorDataGetOrderAndProbabilityData(match, null);
    }

    static LazyMatchWithOptionsData getWithDefaults() {
        return getWithSpecificLazyMatch(SikuliCoreConstants.NULL_LAZY_MATCH);
    }
}
