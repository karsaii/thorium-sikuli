package com.github.karsaii.framework.sikuli.namespaces.validators;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.FileUtilities;
import com.github.karsaii.core.extensions.namespaces.predicates.SizablePredicates;
import com.github.karsaii.core.namespaces.StringUtilities;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.sikuli.constants.MatchStrategyFunctionsConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
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

        return getNamedErrorMessageOrEmpty("isValidStrategy", message);
    }

    static String isTabPaddedLocator(String locator) {
        return CoreFormatter.isPadded(locator, "Locator", MatchStrategyFunctionsConstants.TEXT_INDICATOR);
    }

    static String isValidImageLocator(String locator) {
        final var nameof = "isValidImageLocator";
        var message = CoreFormatter.isBlankMessageWithName(locator, "Locator");
        if (isNotBlank(message)) {
            return getNamedErrorMessageOrEmpty(nameof, message);
        }

        var filePath = locator;
        if (StringUtilities.uncontains(filePath, SikuliCoreConstants.SIMILARITY_SEPARATOR)) {
            message += isBlank(isTabPaddedLocator(filePath)) ? "Locator is a Text Locator" + CoreFormatterConstants.END_LINE : CoreFormatterConstants.EMPTY;
        }

        if (isBlank(message)) {
            if (StringUtilities.contains(filePath, SikuliCoreConstants.SIMILARITY_SEPARATOR)) {
                final var splits = filePath.split(SikuliCoreConstants.SIMILARITY_SEPARATOR);
                filePath = splits[0];
                if (SizablePredicates.isSizeEqualTo(splits.length, 2)) {
                    final var similarity = Double.parseDouble(splits[1]);
                    message += (
                            (isBlank(isTabPaddedLocator(filePath)) ? "Locator is a Text Locator" + CoreFormatterConstants.END_LINE : CoreFormatterConstants.EMPTY) +
                                    ((Math.abs(similarity) < 0.001) ? "Similarity score is zero(ish)(\"" + similarity + "\")" + CoreFormatterConstants.END_LINE : CoreFormatterConstants.EMPTY)
                    );
                } else {
                    message += "Too many separators in locator" + CoreFormatterConstants.END_LINE;
                }
            }
        }

        if (isBlank(message)) {
            message += isEqualMessage(filePath.trim().length(), "Trimmed image locator", filePath.length(), "Actual image locator length");
        }

        filePath = filePath.trim();
        if (!FileUtilities.isExisting(filePath)) {
            message += "Image file doesn't exist" + CoreFormatterConstants.END_LINE;
        }

        return getNamedErrorMessageOrEmpty(nameof, message);
    }

    static String isImageLocator(String locator) {
        var message = CoreFormatter.isBlankMessageWithName(locator, "Locator");
        if (isNotBlank(message)) {
            return getNamedErrorMessageOrEmpty("isImageLocator", message);
        }

        var filePath = locator;
        if (filePath.contains(SikuliCoreConstants.SIMILARITY_SEPARATOR)) {
            final var splits = filePath.split(SikuliCoreConstants.SIMILARITY_SEPARATOR);
            filePath = splits[0];
            if (SizablePredicates.isSizeEqualTo(splits.length, 2)) {
                final var similarity = Double.parseDouble(splits[1]);
                if ((Math.abs(similarity) < 0.001)) {
                    message += "Similarity score is zero(ish)(\"" + similarity + "\")" + CoreFormatterConstants.END_LINE;
                }
            }
        }

        if (isBlank(message) && !FileUtilities.isExisting(filePath)) {
            message += "Image file doesn't exist" + CoreFormatterConstants.END_LINE;
        }

        return getNamedErrorMessageOrEmpty("isImageLocator", message);
    }

    static String isValidTextLocator(String locator) {
        return getNamedErrorMessageOrEmpty("isValidTextLocator", isTabPaddedLocator(locator));
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

        return getNamedErrorMessageOrEmpty("isValidLocator", message);
    }

    static String isValidLocatorAndStrategy(String locator, String strategy) {
        var message = isValidLocator(locator) + isValidStrategy(strategy);
        return getNamedErrorMessageOrEmpty("isValidLocatorAndStrategy", message);
    }
}
