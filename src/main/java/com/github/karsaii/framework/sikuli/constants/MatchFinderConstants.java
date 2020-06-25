package com.github.karsaii.framework.sikuli.constants;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.sikuli.enums.ManyMatchesGetter;
import com.github.karsaii.framework.sikuli.enums.SingleMatchGetter;
import com.github.karsaii.framework.sikuli.namespaces.SikuliFunctions;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Map.entry;

public abstract class MatchFinderConstants {
    public static final Map<SingleMatchGetter, Function<LazyLocatorList, Function<Region, Data<Match>>>> singleGetterMap = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(SingleMatchGetter.GET_ELEMENT, SikuliFunctions::getElementFromSingle)
            )
        )
    );

    public static final Map<ManyMatchesGetter, Function<LazyLocatorList, Function<Region, Data<MatchList>>>> manyGetterMap = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(ManyMatchesGetter.GET_ELEMENTS, SikuliFunctions::getElements)
            )
        )
    );
}