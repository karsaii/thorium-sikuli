package com.github.karsaii.framework.sikuli.namespaces.factories;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.sikuli.constants.factories.MatchLazyLocatorFactoryConstants;
import com.github.karsaii.framework.sikuli.enums.MatchSelectorStrategy;
import com.github.karsaii.framework.sikuli.namespaces.SikuliFormatters;
import com.github.karsaii.framework.sikuli.namespaces.match.MatchStrategyFunctions;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatchLocator;

import static com.github.karsaii.framework.sikuli.constants.factories.MatchLazyLocatorFactoryConstants.DEFAULT_PRECISION;
import static com.github.karsaii.framework.sikuli.constants.factories.MatchLazyLocatorFactoryConstants.EXACT_PRECISION;
import static com.github.karsaii.framework.sikuli.constants.factories.MatchLazyLocatorFactoryConstants.NEAR_DEFAULT_PRECISION;
import static com.github.karsaii.framework.sikuli.constants.factories.MatchLazyLocatorFactoryConstants.NON_IMAGE_PRECISION;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface MatchLazyLocatorFactory {
    static LazyMatchLocator getWith(String locator, MatchSelectorStrategy strategy, double precision) {
        var errorMessage = CoreFormatter.isBlankMessageWithName(locator, "Locator");
        errorMessage += SikuliFormatters.getPrecisionLessThanExpectedMessage(precision, DEFAULT_PRECISION);
        if (isNotBlank(errorMessage)) {
            throw new IllegalArgumentException(errorMessage);
        }

        return new LazyMatchLocator(locator, strategy.getName(), precision);
    }

    static LazyMatchLocator getWith(String locator, MatchSelectorStrategy strategy) {
        final var precision = CoreUtilities.isEqual(strategy, MatchSelectorStrategy.TEXT) ? NON_IMAGE_PRECISION : DEFAULT_PRECISION;
        return getWith(locator, strategy, precision);
    }

    static LazyMatchLocator getWithDefaultStrategy(String locator) {
        return getWith(locator, MatchSelectorStrategy.DEFAULT);
    }

    static LazyMatchLocator getImageLocator(String locator, double precision) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, precision);
    }

    static LazyMatchLocator getImageLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, DEFAULT_PRECISION);
    }

    static LazyMatchLocator getNearDefaultImageLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, NEAR_DEFAULT_PRECISION);
    }

    static LazyMatchLocator getExactImageLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, EXACT_PRECISION);
    }

    static LazyMatchLocator getNearExactImageLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, MatchLazyLocatorFactoryConstants.NEAR_EXACT_PRECISION);
    }

    static LazyMatchLocator getTextLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.TEXT);
    }

    static LazyMatchLocator get(String locator) {
        var localLocator = MatchStrategyFunctions.getTextLocator(locator);
        if (isNotBlank(localLocator)) {
            return getTextLocator(localLocator);
        }

        localLocator = MatchStrategyFunctions.getImageLocator(locator);
        return getImageLocator(localLocator);
    }
}
