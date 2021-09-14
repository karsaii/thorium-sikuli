package com.neathorium.framework.sikuli.namespaces.match.validators;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.LazyMatchLocatorList;
import com.neathorium.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.neathorium.framework.sikuli.records.lazy.LazyMatch;
import com.neathorium.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.caster.WrappedCastData;
import com.neathorium.core.records.wait.WaitTimeData;
import com.neathorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.framework.core.records.lazy.LazyElementParameters;
import org.apache.commons.lang3.StringUtils;
import org.sikuli.script.Match;

import java.lang.reflect.Method;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.neathorium.framework.sikuli.namespaces.SikuliUtilities.areNullLazyData;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface MatchParameters {
    static String validateWaitTimeData(WaitTimeData timeData) {
        var message = CoreFormatter.isNullMessageWithName(timeData, "TimeData");
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(timeData.clock, "TimeData clock") +
                CoreFormatter.isNullMessageWithName(timeData.interval, "TimeData interval") +
                CoreFormatter.isNullMessageWithName(timeData.duration, "TimeData duration")
            );
        }

        return isBlank(message) ? message : (CoreFormatterConstants.PARAMETER_ISSUES + message);
    }
    static <T, V> String validateUntilParameters(Function<T, V> condition, Predicate<V> continueCondition, WaitTimeData timeData) {
        final var message = (
            CoreFormatter.isNullMessageWithName(condition, "Condition") +
            CoreFormatter.isNullMessageWithName(continueCondition, "ContinueCondition") +
            validateWaitTimeData(timeData)
        );

        return isBlank(message) ? message : ("Wait.until: " + message);
    }

    static <T> boolean isInvalidLazyElementParameters(LazyElementParameters<LazyMatchLocatorList> data) {
        return NullableFunctions.isNull(data) || StringUtils.isBlank(data.GETTER) || areNullLazyData(data.LAZY_LOCATORS.list);
    }

    static boolean isValidLazyElementParameters(LazyElementParameters<LazyMatchLocatorList> data) {
        return !isInvalidLazyElementParameters(data);
    }

    static boolean isValidLazyFilteredElement(LazyFilteredMatchParameters data) {
        return !(isInvalidLazyElementParameters(data) || (NullableFunctions.isNull(data.ELEMENT_FILTER_DATA)) || (NullableFunctions.isNull(data.ELEMENT_FILTER_DATA.filterParameter)));
    }


    static <T> String validateCommonElementMethodParamaters(WrappedCastData<T> castData, BiPredicate<Method, String> condition, String methodName) {
        return (
            CoreFormatter.isNullMessageWithName(castData, "Generic cast type instance") +
            CoreFormatter.isNullMessageWithName(condition, "Condition method") +
            CoreFormatter.isBlankMessageWithName(methodName, "Method name")
        );
    }

    static <T, U> String validateElementMethodParameters(RegionFunction<Match> element, WrappedCastData<T> castData, BiPredicate<Method, String> condition, String methodName) {
        return (
            CoreFormatter.isNullMessageWithName(element, "Element") +
            validateCommonElementMethodParamaters(castData, condition, methodName)
        );
    }

    static <T, U> String validateElementMethodParameters(LazyMatch element, WrappedCastData<T> castData, BiPredicate<Method, String> condition, String methodName) {
        return validateElementMethodParameters(element.get(), castData, condition, methodName);
    }

    static <T, U> String validateElementMethodParameters(AbstractLazyResult<T> element, WrappedCastData<T> castData, BiPredicate<Method, String> condition, String methodName) {
        return (
            FrameworkCoreFormatter.isNullLazyElementMessage(element) +
            validateCommonElementMethodParamaters(castData, condition, methodName)
        );
    }
}
