package com.github.karsaii.framework.sikuli.namespaces.validators;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.FileUtilities;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.sikuli.constants.MatchStrategyFunctionsConstants;
import com.github.karsaii.framework.sikuli.enums.MatchSelectorStrategy;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isEqualMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface MatchLocatorValidators {
    static String isValidStrategy(String value) {
        var message = CoreFormatter.isBlankMessageWithName(value, "Strategy value");
        if (isBlank(message)) {
            final var status = !MatchSelectorStrategy.contains(value);
            if (status) {
                message += "Match selector strategies didn't contain \"" + value + "\"";
            }
        }

        return getNamedErrorMessageOrEmpty("isValidStrategy: ", message);
    }

    static String isTabPaddedLocator(String locator) {
        return CoreFormatter.isPadded(locator, "Locator", MatchStrategyFunctionsConstants.TEXT_INDICATOR);
    }

    static String isValidImageLocator(String locator) {
        var message = CoreFormatter.isBlankMessageWithName(locator, "Locator");

        if (isBlank(message)) {
            message += isNotBlank(isTabPaddedLocator(locator)) ? CoreFormatterConstants.EMPTY : "Locator is a Text Locator" + CoreFormatterConstants.END_LINE;
        }

        if (isBlank(message)) {
            message += isEqualMessage(locator.trim().length(), "Trimmed image locator", locator.length(), "Actual image locator length");
        }

        return getNamedErrorMessageOrEmpty("isValidImageLocator: ", message);
    }

    static String isImageLocator(String locator) {
        var message = CoreFormatter.isBlankMessageWithName(locator, "Locator");
        if (isBlank(message)) {
            if (!FileUtilities.isExisting(locator)) {
                message += "Image file doesn't exist" + CoreFormatterConstants.END_LINE;
            }
        }
        return getNamedErrorMessageOrEmpty("isImageLocator: ", message);
    }

    static String isValidTextLocator(String locator) {
        return getNamedErrorMessageOrEmpty("isValidTextLocator: ", isTabPaddedLocator(locator));
    }

    static String isValidLocator(String locator) {
        var message = isBlankMessageWithName(locator, "Locator");
        if (isBlank(message)) {
            final var validTextLocator = isValidTextLocator(locator);
            final var validImageLocator = isValidImageLocator(locator);
            if (isNotBlank(validTextLocator) && isNotBlank(validImageLocator)) {
                message += validTextLocator + validImageLocator;
            }
        }

        return getNamedErrorMessageOrEmpty("isValidLocator: ", message);
    }

    static String isValidLocatorAndStrategy(String locator, String strategy) {
        var message = isValidLocator(locator) + isValidStrategy(strategy);
        return getNamedErrorMessageOrEmpty("isValidLocatorAndStrategy: ", message);
    }
}
