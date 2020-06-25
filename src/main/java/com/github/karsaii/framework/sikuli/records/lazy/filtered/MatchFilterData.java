package com.github.karsaii.framework.sikuli.records.lazy.filtered;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.github.karsaii.framework.core.records.element.finder.BaseFilterParameters;
import com.github.karsaii.framework.sikuli.enums.ManyMatchesGetter;
import com.github.karsaii.framework.sikuli.namespaces.region.RegionFunctionFactory;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.github.karsaii.framework.sikuli.records.MatchFilterParameters;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import java.util.function.Function;

public class MatchFilterData<T> extends BaseFilterData<Region, ManyMatchesGetter, T, MatchFilterParameters, MatchList, Match> {
    public MatchFilterData(boolean isFiltered, Function<MatchFilterParameters, Function<T, Function<Region, Data<Match>>>> handler, T filterParameter) {
        super(isFiltered, handler, filterParameter);
    }


    public RegionFunction<Match> apply(MatchFilterParameters parameters) {
        return RegionFunctionFactory.get(handler.apply(parameters).apply(filterParameter));
    }

    @Override
    public Function<Region, Data<Match>> apply(BaseFilterParameters<Region, ManyMatchesGetter, MatchList> parameters) {
        return handler.apply((MatchFilterParameters)parameters).apply(filterParameter);
    }
}
