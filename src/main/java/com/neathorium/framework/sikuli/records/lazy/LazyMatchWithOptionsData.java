package com.neathorium.framework.sikuli.records.lazy;

import com.neathorium.framework.sikuli.records.ExternalSikuliSelectorData;
import com.neathorium.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.framework.core.abstracts.AbstractLazyElementWithOptionsData;
import com.neathorium.framework.core.records.InternalSelectorData;
import com.neathorium.framework.core.records.ProbabilityData;
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
