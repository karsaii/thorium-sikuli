package com.neathorium.framework.sikuli.namespaces.validators;

import com.neathorium.framework.sikuli.constants.MatchStrategyFunctionsConstants;
import com.neathorium.framework.sikuli.enums.MatchSelectorStrategy;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.namespaces.validators.CoreFormatter;

import java.nio.file.Files;
import java.nio.file.Paths;

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

        return CoreFormatter.getNamedErrorMessageOrEmpty("isValidStrategy", message);
    }

    static String isTabPaddedLocator(String locator) {
        return CoreFormatter.isPadded(locator, "Locator", MatchStrategyFunctionsConstants.TEXT_INDICATOR);
    }

    static String isImageLocator(String locator) {
        var message = CoreFormatter.isBlankMessageWithName(locator, "Locator");
        if (isNotBlank(message)) {
            return CoreFormatter.getNamedErrorMessageOrEmpty("isImageLocator", message);
        }

        var path = Paths.get(locator).normalize();
        if (CoreUtilities.isFalse(Files.exists(path))) {
            message += "Image file (\"" + path + "\") doesn't exist" + CoreFormatterConstants.END_LINE;
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isImageLocator", message);
    }

    static String isValidTextLocator(String locator) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("isValidTextLocator", isTabPaddedLocator(locator));
    }

}
