package com.github.karsaii.framework.sikuli.records;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.records.element.finder.BaseFilterParameters;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.sikuli.enums.ManyMatchesGetter;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import org.sikuli.script.Region;

import java.util.Map;
import java.util.function.Function;

public class MatchFilterParameters extends BaseFilterParameters<Region, ManyMatchesGetter, MatchList> {
    public MatchFilterParameters(LazyLocatorList locators, Map<ManyMatchesGetter, Function<LazyLocatorList, Function<Region, Data<MatchList>>>> getterMap, ManyMatchesGetter getter) {
        super(locators, getterMap, getter);
    }
}
