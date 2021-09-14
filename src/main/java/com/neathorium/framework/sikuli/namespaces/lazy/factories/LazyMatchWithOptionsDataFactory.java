package com.neathorium.framework.sikuli.namespaces.lazy.factories;

import com.neathorium.framework.sikuli.constants.SikuliGetOrderConstants;
import com.neathorium.framework.sikuli.constants.SikuliCoreConstants;
import com.neathorium.framework.sikuli.records.ExternalSikuliSelectorData;
import com.neathorium.framework.sikuli.records.lazy.LazyMatch;
import com.neathorium.framework.sikuli.records.lazy.LazyMatchWithOptionsData;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.framework.core.constants.AdjusterConstants;
import com.neathorium.framework.core.constants.SelectorDataConstants;
import com.neathorium.framework.core.records.InternalSelectorData;
import com.neathorium.framework.core.records.ProbabilityData;

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
        return getWithSpecificLazyMatch(SikuliCoreConstants.INVALID_LAZY_MATCH);
    }
}
