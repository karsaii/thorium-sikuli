package com.github.karsaii.framework.sikuli.namespaces.match;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.FileUtilities;
import com.github.karsaii.framework.sikuli.namespaces.validators.MatchLocatorValidators;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface MatchStrategyFunctions {
    static String getImageLocator(String locator) {
        final var errorMessage = MatchLocatorValidators.isValidImageLocator(locator);
        if (isNotBlank(errorMessage)) {
            return CoreFormatterConstants.EMPTY;
        }

        final var trimmed = locator.trim();
        return FileUtilities.isExisting(trimmed) ? trimmed : CoreFormatterConstants.EMPTY;
    }

    static String getTextLocator(String locator) {
        final var errorMessage = MatchLocatorValidators.isImageLocator(locator);
        if (isBlank(errorMessage)) {
            return CoreFormatterConstants.EMPTY;
        }

        final var status = MatchLocatorValidators.isTabPaddedLocator(locator);
        return isBlank(status) ? locator : "\t" + locator + "\t";
    }
}
