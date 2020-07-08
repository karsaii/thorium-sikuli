package com.github.karsaii.framework.sikuli.namespaces.match.validators;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.EmptiableFunctions;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.sikuli.enums.ManyMatchesGetter;
import com.github.karsaii.framework.sikuli.enums.SingleMatchGetter;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import org.sikuli.script.Region;

import java.util.Map;
import java.util.function.Function;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface MatchFilterParametersValidators {
    static <T> String getSingleMatchGetterErrorMessage(Map<SingleMatchGetter, Function<LazyLocatorList, Function<Region, Data<T>>>> getterMap, SingleMatchGetter key) {
        final var nameof = "getSingleMatchGetterErrorMessage";
        final var parameterName = "Getter map";
        var message = CoreFormatter.isNullMessageWithName(getterMap, parameterName);
        if (isNotBlank(message)) {
            return nameof + message;
        }

        if (getterMap.isEmpty()) {
            message += parameterName + " was empty" + CoreFormatterConstants.END_LINE;
        } else {
            if (!getterMap.containsKey(key)) {
                message += "Getter was not found in map with " + key.getName();
            }
        }

        return isNotBlank(message) ? nameof + message : CoreFormatterConstants.EMPTY;
    }

    static <T> String getManyMatchesGetterErrorMessage(Map<ManyMatchesGetter, Function<LazyLocatorList, Function<Region, Data<T>>>> getterMap, ManyMatchesGetter key) {
        final var nameof = "getManyGetterErrorMessage";
        final var parameterName = "Getter map";
        var message = CoreFormatter.isNullMessageWithName(getterMap, parameterName);
        if (isNotBlank(message)) {
            return nameof + message;
        }

        if (getterMap.isEmpty()) {
            message += parameterName + " was empty" + CoreFormatterConstants.END_LINE;
        } else {
            if (!getterMap.containsKey(key)) {
                message += "Getter was not found in map with " + key.getName();
            }
        }

        return isNotBlank(message) ? nameof + message : CoreFormatterConstants.EMPTY;
    }

    static String isNullLazyLocatorListMessage(LazyLocatorList locators) {
        var message = CoreFormatter.isNullMessageWithName(locators, "Locators");
        if (isBlank(message) && EmptiableFunctions.isEmpty(locators)) {
            message += "List was empty" + CoreFormatterConstants.END_LINE;
        }

        return getNamedErrorMessageOrEmpty("isNullLazyLocatorListMessage", message);
    }

    private static <T, U> String isInvalidElementFilterParametersMessageCore(BaseFilterParameters<Region, ManyMatchesGetter, MatchList> data) {
        return isNullLazyLocatorListMessage(data.locators) + getManyMatchesGetterErrorMessage(data.getterMap, data.getter);
    }

    static <T, U> String isInvalidElementFilterParametersMessage(BaseFilterParameters<Region, ManyMatchesGetter, MatchList> data) {
        var message = CoreFormatter.isNullMessageWithName(data, "Element Filter Parameters data");
        if (isBlank(message)) {
            message += isInvalidElementFilterParametersMessageCore(data);
        }

        return getNamedErrorMessageOrEmpty("isInvalidElementIndexFilterParametersMessage", message);
    }
}
