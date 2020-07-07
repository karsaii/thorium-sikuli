package com.github.karsaii.framework.sikuli.namespaces.validators;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;

public interface GetElementsValidators {
    static String isValidPercentage(double percentage) {
        var message = percentage < 0.001 ? "Percentage was below 0.001, is invalid" + CoreFormatterConstants.END_LINE : CoreFormatterConstants.EMPTY;
        return getNamedErrorMessageOrEmpty("isValidPercentage: ", message);
    }

    static String getElementParametersValidMessage(String locator, double percentage, int index) {
        return getNamedErrorMessageOrEmpty("getElementParametersValidMessage: ", (
            getValidLocatorMessage(locator) +
            isValidPercentage(percentage) +
            CoreFormatter.isLessThanExpected(index, 0, "index")
        ));
    }

    static String getElementParametersValidMessage(String locator, double percentage) {
        return getNamedErrorMessageOrEmpty("getElementParametersValidMessage: ", getValidLocatorMessage(locator) + isValidPercentage(percentage));
    }

    static String getElementParametersValidMessage(String locator, int index) {
        return getNamedErrorMessageOrEmpty("getElementParametersValidMessage: ", getValidLocatorMessage(locator) + CoreFormatter.isLessThanExpected(index, 0, "index"));
    }

    static String getValidLocatorMessageCommon(String locator, String name) {
        return getNamedErrorMessageOrEmpty("getValidLocatorMessageCommon: ", CoreFormatter.isBlankMessageWithName(locator, name));
    }

    static String getValidLocatorMessage(String locator) {
        return getNamedErrorMessageOrEmpty("getValidLocatorMessage: ", getValidLocatorMessageCommon(locator, "Locator"));
    }

    static String getValidTextLocatorMessage(String locator) {
        return getNamedErrorMessageOrEmpty("getValidLocatorMessage: ", getValidLocatorMessageCommon(locator, "Text Locator"));
    }
}
