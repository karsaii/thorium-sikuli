package com.neathorium.framework.sikuli.namespaces.validators;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.neathorium.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.framework.core.records.lazy.LazyLocator;
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
