package com.neathorium.framework.sikuli.records.lazy.filtered;

import com.neathorium.framework.sikuli.enums.ManyMatchesGetter;
import com.neathorium.framework.sikuli.namespaces.region.RegionFunctionFactory;
import com.neathorium.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.neathorium.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.neathorium.framework.sikuli.records.MatchFilterParameters;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.neathorium.framework.core.abstracts.lazy.filtered.BaseFilterData;
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
