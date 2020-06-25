package com.github.karsaii.framework.sikuli.records.lazy;

import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.sikuli.namespaces.SikuliFunctions;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.IRegion;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import org.sikuli.script.Match;

import java.util.Map;
import java.util.function.Predicate;

public class LazyMatch extends AbstractLazyResult<LazyFilteredMatchParameters> implements IRegion {
    public LazyMatch(String name, Map<String, LazyFilteredMatchParameters> parameters, Predicate<LazyFilteredMatchParameters> validator) {
        super(name, parameters, validator);
    }

    @Override
    public RegionFunction<Match> get() {
        return SikuliFunctions.getLazyMatch(this);
    }
}
