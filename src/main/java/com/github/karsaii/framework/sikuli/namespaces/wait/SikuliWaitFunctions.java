package com.github.karsaii.framework.sikuli.namespaces.wait;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.wait.Wait;
import com.github.karsaii.core.namespaces.wait.WaitTimeDataFactory;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.WaitData;
import com.github.karsaii.framework.sikuli.constants.GetElementsConstants;
import com.github.karsaii.framework.sikuli.namespaces.SikuliBasicFunctions;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.github.karsaii.framework.sikuli.namespaces.region.RegionFunctionFactory;
import org.sikuli.script.Region;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SikuliWaitFunctions {
    static <T> RegionFunction<T> waitCore(String name, Function<Region, Data<T>> function, Predicate<Data<T>> exitCondition, String conditionMessage, int interval, int timeout) {
        final var nameof = isNotBlank(name) ? name : "waitCore";
        if ((interval < 300) || (timeout < 300) || (timeout < interval)) {
            return region -> DataFactoryFunctions.getWithNameAndMessage(null, false, nameof, CoreFormatterConstants.PARAMETER_ISSUES);
        }

        final var waitData = new WaitData<>(function, exitCondition, conditionMessage, WaitTimeDataFactory.getWithDefaultClock(interval, timeout));
        return RegionFunctionFactory.get(Wait.core(waitData));
    }

    static Data<Boolean> waitAbsentCore(Region region, String locator, double percentage, int index, int interval, int timeout) {
        return waitCore(
                "waitAbsent",
                SikuliBasicFunctions.isAbsent(locator, percentage, index),
                SikuliWaitPredicateFunctions::isTruthyData,
                "Element was absent" + CoreFormatterConstants.END_LINE,
                interval,
                timeout
        ).apply(region);
    }

    static Data<Boolean> waitAbsentCore(Region region, String locator, double percentage, int interval, int timeout) {
        return waitCore(
            "waitAbsent",
            SikuliBasicFunctions.isAbsent(locator, percentage),
            SikuliWaitPredicateFunctions::isTruthyData,
            "Element was absent" + CoreFormatterConstants.END_LINE,
            interval,
            timeout
        ).apply(region);
    }

    static Data<Boolean> waitAreAbsentCore(Region region, String locator, double percentage, int interval, int timeout) {
        return waitCore(
            "waitAreAbsent",
            SikuliBasicFunctions.areAbsent(locator, percentage),
            SikuliWaitPredicateFunctions::isTruthyData,
            "Elements were absent" + CoreFormatterConstants.END_LINE,
            interval,
            timeout
        ).apply(region);
    }

    static Data<Boolean> waitTextAbsentCore(Region region, String locator, int interval, int timeout) {
        return waitCore(
            "waitTextAbsent",
            SikuliBasicFunctions.isTextAbsent(locator),
            SikuliWaitPredicateFunctions::isTruthyData,
            "Text was absent" + CoreFormatterConstants.END_LINE,
            interval,
            timeout
        ).apply(region);
    }

    static Data<Boolean> waitAbsentCore(Region region, String locator, int index, int interval, int timeout) {
        return waitAbsentCore(region, locator, GetElementsConstants.BASE_MATCH_PERCENT, index, interval, timeout);
    }

    static Data<Boolean> waitAbsentCore(Region region, String locator, int interval, int timeout) {
        return waitAbsentCore(region, locator, GetElementsConstants.BASE_MATCH_PERCENT, interval, timeout);
    }

    static Data<Boolean> waitDisplayedCore(Region region, String locator, double percentage, int index, int interval, int timeout) {
        return waitCore(
            "waitDisplayed",
            SikuliBasicFunctions.isDisplayed(locator, percentage, index),
            SikuliWaitPredicateFunctions::isTruthyData,
            "Element was displayed" + CoreFormatterConstants.END_LINE,
            interval,
            timeout
        ).apply(region);
    }

    static Data<Boolean> waitDisplayedCore(Region region, String locator, double percentage, int interval, int timeout) {
        return waitCore(
            "waitDisplayed",
            SikuliBasicFunctions.isDisplayed(locator, percentage),
            SikuliWaitPredicateFunctions::isTruthyData,
            "Element was displayed" + CoreFormatterConstants.END_LINE,
            interval,
            timeout
        ).apply(region);
    }

    static Data<Boolean> waitAreDisplayedCore(Region region, String locator, double percentage, int interval, int timeout) {
        return waitCore(
            "waitDisplayed",
            SikuliBasicFunctions.areDisplayed(locator, percentage),
            SikuliWaitPredicateFunctions::isTruthyData,
            "Elements were displayed" + CoreFormatterConstants.END_LINE,
            interval,
            timeout
        ).apply(region);
    }

    static Data<Boolean> waitTextDisplayedCore(Region region, String locator, int interval, int timeout) {
        return waitCore(
            "waitTextDisplayed",
            SikuliBasicFunctions.isTextDisplayed(locator),
            SikuliWaitPredicateFunctions::isTruthyData,
            "Text was displayed" + CoreFormatterConstants.END_LINE,
            interval,
            timeout
        ).apply(region);
    }

    static Data<Boolean> waitDisplayedCore(Region region, String locator, int interval, int timeout) {
        return waitDisplayedCore(region, locator, GetElementsConstants.BASE_MATCH_PERCENT, interval, timeout);
    }

    static Data<Boolean> waitDisplayedCore(Region region, String locator, int index, int interval, int timeout) {
        return waitDisplayedCore(region, locator, GetElementsConstants.BASE_MATCH_PERCENT, index, interval, timeout);
    }

    static Data<Boolean> waitAbsentNearExactCore(Region region, String locator, int index, int interval, int timeout) {
        return waitAbsentCore(region, locator, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index, interval, timeout);
    }

    static Data<Boolean> waitAbsentNearExact(Region region, String locator, int interval, int timeout) {
        return waitAbsentCore(region, locator, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, interval, timeout);
    }

    static Data<Boolean> waitDisplayedNearExactCore(Region region, String locator, int interval, int timeout) {
        return waitDisplayedCore(region, locator, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, interval, timeout);
    }

    static Data<Boolean> waitDisplayedNearExactCore(Region region, String locator, int index, int interval, int timeout) {
        return waitDisplayedCore(region, locator, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index, interval, timeout);
    }

    static Data<Boolean> waitAbsentExactCore(Region region, String locator, int index, int interval, int timeout) {
        return waitAbsentCore(region, locator, GetElementsConstants.EXACT_MATCH_PERCENT, index, interval, timeout);
    }

    static Data<Boolean> waitAbsentExactCore(Region region, String locator, int interval, int timeout) {
        return waitAbsentCore(region, locator, GetElementsConstants.EXACT_MATCH_PERCENT, interval, timeout);
    }

    static Data<Boolean> waitDisplayedExactCore(Region region, String locator, int interval, int timeout) {
        return waitDisplayedCore(region, locator, GetElementsConstants.EXACT_MATCH_PERCENT, interval, timeout);
    }

    static Data<Boolean> waitDisplayedExactCore(Region region, String locator, int index, int interval, int timeout) {
        return waitDisplayedCore(region, locator, GetElementsConstants.EXACT_MATCH_PERCENT, index, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAbsentCore(String locator, double percentage, int index, int interval, int timeout) {
        return region -> waitAbsentCore(region, locator, percentage, index, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAbsentCore(String locator, double percentage, int interval, int timeout) {
        return region -> waitAbsentCore(region, locator, percentage, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAreAbsentCore(String locator, double percentage, int interval, int timeout) {
        return region -> waitAreAbsentCore(region, locator, percentage, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitTextAbsentCore(String locator, int interval, int timeout) {
        return region -> waitTextAbsentCore(region, locator, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAbsentCore(String locator, int index, int interval, int timeout) {
        return region -> waitAbsentCore(region, locator, GetElementsConstants.BASE_MATCH_PERCENT, index, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAbsentCore(String locator, int interval, int timeout) {
        return region -> waitAbsentCore(region, locator, GetElementsConstants.BASE_MATCH_PERCENT, interval, timeout);
    }

    private static Function<Region, Data<Boolean>>  waitDisplayedCore(String locator, double percentage, int index, int interval, int timeout) {
        return region -> waitDisplayedCore(region, locator, percentage, index, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitDisplayedCore(String locator, double percentage, int interval, int timeout) {
        return region -> waitDisplayedCore(region, locator, percentage, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAreDisplayedCore(String locator, double percentage, int interval, int timeout) {
        return region -> waitAreDisplayedCore(region, locator, percentage, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitTextDisplayedCore(String locator, int interval, int timeout) {
        return region -> waitTextDisplayedCore(region, locator, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitDisplayedCore(String locator, int interval, int timeout) {
        return region -> waitDisplayedCore(region, locator, GetElementsConstants.BASE_MATCH_PERCENT, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitDisplayedCore(String locator, int index, int interval, int timeout) {
        return region -> waitDisplayedCore(region, locator, GetElementsConstants.BASE_MATCH_PERCENT, index, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAbsentNearExactCore(String locator, int index, int interval, int timeout) {
        return region -> waitAbsentCore(region, locator, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAbsentNearExactCore(String locator, int interval, int timeout) {
        return region -> waitAbsentCore(region, locator, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitDisplayedNearExactCore(String locator, int interval, int timeout) {
        return region -> waitDisplayedCore(region, locator, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitDisplayedNearExactCore(String locator, int index, int interval, int timeout) {
        return region -> waitDisplayedCore(region, locator, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAbsentExactCore(String locator, int index, int interval, int timeout) {
        return region -> waitAbsentCore(region, locator, GetElementsConstants.EXACT_MATCH_PERCENT, index, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitAbsentExactCore(String locator, int interval, int timeout) {
        return region -> waitAbsentCore(region, locator, GetElementsConstants.EXACT_MATCH_PERCENT, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitDisplayedExactCore(String locator, int interval, int timeout) {
        return region -> waitDisplayedCore(region, locator, GetElementsConstants.EXACT_MATCH_PERCENT, interval, timeout);
    }

    private static Function<Region, Data<Boolean>> waitDisplayedExactCore(String locator, int index, int interval, int timeout) {
        return region -> waitDisplayedCore(region, locator, GetElementsConstants.EXACT_MATCH_PERCENT, index, interval, timeout);
    }

    static RegionFunction<Boolean> waitAbsent(String locator, double percentage, int index, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitAbsent",
            isNotBlank(locator) && (percentage > 0.0) && (index > -1) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitAbsentCore(locator, percentage, index, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAbsent(String locator, double percentage, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitAbsent",
            isNotBlank(locator) && (percentage > 0.0) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitAbsentCore(locator, percentage, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAreAbsent(String locator, double percentage, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitAreAbsent",
            isNotBlank(locator) && (percentage > 0.0) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitAreAbsentCore(locator, percentage, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitTextAbsent(String locator, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitTextAbsent",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitTextAbsentCore(locator, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAbsent(String locator, int index, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitAbsent",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitAbsentCore(locator, index, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAbsent(String locator, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitAbsent",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitAbsentCore(locator, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitDisplayed(String locator, double percentage, int index, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitDisplayed",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitDisplayedCore(locator, percentage, index, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitDisplayed(String locator, double percentage, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitDisplayed",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitDisplayedCore(locator, percentage, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAreDisplayed(String locator, double percentage, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitAreDisplayed",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitAreDisplayedCore(locator, percentage, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitTextDisplayed(String locator, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitTextDisplayed",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitTextDisplayedCore(locator, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitDisplayed(String locator, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitDisplayed",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitDisplayedCore(locator, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitDisplayed(String locator, int index, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitDisplayed",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitDisplayedCore(locator, index, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAbsentNearExact(String locator, int index, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitAbsentNearExact",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitAbsentNearExactCore(locator, index, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAbsentNearExact(String locator, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
            "waitAbsentNearExact",
            isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
            waitAbsentNearExactCore(locator, interval, timeout),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitDisplayedNearExact(String locator, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
                "waitDisplayedNearExact",
                isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
                waitDisplayedNearExactCore(locator, interval, timeout),
                CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitDisplayedNearExact(String locator, int index, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
                "waitDisplayedNearExact",
                isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
                waitDisplayedNearExactCore(locator, index, interval, timeout),
                CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAbsentExact(String locator, int index, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
                "waitAbsentExact",
                isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
                waitAbsentExactCore(locator, index, interval, timeout),
                CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitAbsentExact(String locator, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
                "waitAbsentExact",
                isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
                waitAbsentExactCore(locator, interval, timeout),
                CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitDisplayedExact(String locator, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
                "waitDisplayedExact",
                isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
                waitDisplayedExactCore(locator, interval, timeout),
                CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static RegionFunction<Boolean> waitDisplayedExact(String locator, int index, int interval, int timeout) {
        return RegionFunctionFactory.get(ifDependency(
                "waitDisplayedExact",
                isNotBlank(locator) && (interval > 299) && (timeout > 299) && (timeout > interval),
                waitDisplayedExactCore(locator, index, interval, timeout),
                CoreDataConstants.NULL_BOOLEAN
        ));
    }
}
