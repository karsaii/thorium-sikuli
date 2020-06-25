package com.github.karsaii.framework.sikuli.records.lazy;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.abstracts.AbstractLazyElementWithOptionsData;
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.core.records.ProbabilityData;
import com.github.karsaii.framework.sikuli.records.ExternalSikuliSelectorData;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import org.sikuli.script.Region;

public class LazyMatchWithOptionsData extends AbstractLazyElementWithOptionsData<LazyFilteredMatchParameters, LazyMatch, Region, ExternalSikuliSelectorData> {
    public LazyMatchWithOptionsData(
        LazyMatch element,
        InternalSelectorData internalData,
        ExternalSikuliSelectorData externalData,
        DecoratedList<String> getOrder,
        ProbabilityData probabilityData
    ) {
        super(element, internalData, externalData, getOrder, probabilityData);
    }
}
