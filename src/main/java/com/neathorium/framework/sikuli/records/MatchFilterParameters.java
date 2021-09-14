package com.neathorium.framework.sikuli.records;

import com.neathorium.core.records.Data;
import com.neathorium.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.neathorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import org.sikuli.script.Region;

import java.util.Map;
import java.util.function.Function;

public class MatchFilterParameters<T, U> extends BaseFilterParameters<Region, T, U> {
    public MatchFilterParameters(LazyLocatorList locators, Map<T, Function<LazyLocatorList, Function<Region, Data<U>>>> getterMap, T getter) {
        super(locators, getterMap, getter);
    }
}
