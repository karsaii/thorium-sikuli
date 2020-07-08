package com.github.karsaii.framework.sikuli.namespaces.validators;

import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import org.sikuli.script.Region;

import java.util.function.Function;

public interface SikuliFormatter {
    static String getElementsParametersMessage(LazyLocatorList locators, Function<LazyLocator, RegionFunction<MatchList>> getter) {
        return CoreFormatter.getNamedErrorMessageOrEmpty(
            "getElementsParametersMessage",
            CoreFormatter.isNullOrEmptyMessageWithName(locators, "Lazy Locators List") + CoreFormatter.isNullMessageWithName(getter, "Getter")
        );
    }

    static String getElementsParametersMessage(LazyLocatorList locators) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getElementsParametersMessage", CoreFormatter.isNullOrEmptyMessageWithName(locators, "Lazy Locators List"));
    }

    static String getNestedElementsErrorMessage(String locator, Data<Region> context) {
        return getLocatorErrorMessage(locator) + CoreFormatter.isInvalidOrFalseMessageWithName(context, "Search Context");
    }

    static String getLocatorErrorMessage(String locator) {
        return CoreFormatter.isNullMessageWithName(locator, "String locator");
    }
}
