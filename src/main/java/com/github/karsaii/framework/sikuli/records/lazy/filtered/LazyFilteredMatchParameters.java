package com.github.karsaii.framework.sikuli.records.lazy.filtered;

import com.github.karsaii.framework.core.abstracts.lazy.filtered.AbstractLazyFilteredElementParameters;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.sikuli.enums.ManyMatchesGetter;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.records.MatchFilterParameters;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

public class LazyFilteredMatchParameters extends AbstractLazyFilteredElementParameters<Region, ManyMatchesGetter, MatchFilterParameters, MatchList, Match> {
    public LazyFilteredMatchParameters(MatchFilterData<?> elementFilterData, Class clazz, double probability, LazyLocatorList lazyLocators, String getter) {
        super(elementFilterData, clazz, probability, lazyLocators, getter);
    }
}
