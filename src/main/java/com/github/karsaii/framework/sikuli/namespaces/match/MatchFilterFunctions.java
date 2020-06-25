package com.github.karsaii.framework.sikuli.namespaces.match;

import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.sikuli.constants.RegionDataConstants;
import com.github.karsaii.framework.sikuli.enums.SingleMatchGetter;
import com.github.karsaii.framework.sikuli.namespaces.SikuliFunctions;
import com.github.karsaii.framework.sikuli.namespaces.region.RegionFunctionFactory;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.github.karsaii.framework.sikuli.namespaces.match.validators.MatchFilterParametersValidators;
import com.github.karsaii.framework.sikuli.records.MatchFilterParameters;

import org.sikuli.script.Match;
import org.sikuli.script.Region;

import java.util.Map;
import java.util.function.Function;

import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.validChain;

public interface MatchFilterFunctions {
    private static <T> Function<T, Function<Region, Data<Match>>> getFilteredElement(String nameof, MatchFilterParameters data, Function<T, Function<Data<MatchList>, Data<Match>>> filterFunction, Function<T, String> valueGuard) {
        return value -> ifDependency(
                nameof,
                MatchFilterParametersValidators.isInvalidElementFilterParametersMessage(data) + valueGuard.apply(value),
                validChain(data.getterMap.get(data.getter).apply(data.locators), filterFunction.apply(value), RegionDataConstants.NULL_REGION_ALL),
                RegionDataConstants.NULL_REGION_ALL
        );
    }

    static Function<Integer, Function<Region, Data<Match>>> getIndexedElement(MatchFilterParameters data) {
        return getFilteredElement("getIndexedElement", data, SikuliFunctions::getElementByIndex, CoreFormatter::isNegativeMessage);
    }

    static Function<String, Function<Region, Data<Match>>> getContainedTextElement(MatchFilterParameters data) {
        return getFilteredElement("getContainedTextElement", data, SikuliFunctions::getElementByContainedText, CoreFormatter::isBlankMessage);
    }

    static RegionFunction<Match> getElement(LazyLocatorList locators, Map<SingleMatchGetter, Function<LazyLocatorList, Function<Region, Data<Match>>>> getterMap, SingleMatchGetter getter) {
        return RegionFunctionFactory.get(
            ifDependency(
                "getElement via LazyElement parameters",
                MatchFilterParametersValidators.getSingleMatchGetterErrorMessage(getterMap, getter),
                getterMap.get(getter).apply(locators),
                RegionDataConstants.NULL_REGION_ALL
            )
        );
    }
}