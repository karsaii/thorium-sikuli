package com.neathorium.framework.sikuli.namespaces.lazy.factories;

import com.neathorium.framework.sikuli.constants.factories.MatchLazyLocatorFactoryConstants;
import com.neathorium.framework.sikuli.enums.MatchSelectorStrategy;
import com.neathorium.framework.sikuli.namespaces.SikuliFormatters;
import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.namespaces.validators.CoreFormatter;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface LazyMatchLocatorFactory {
    static LazyMatchLocator getWith(String locator, MatchSelectorStrategy strategy, double precision) {
        var errorMessage = CoreFormatter.isBlankMessageWithName(locator, "Locator");
        errorMessage += SikuliFormatters.getPrecisionLessThanExpectedMessage(precision, MatchLazyLocatorFactoryConstants.DEFAULT_PRECISION);
        if (isNotBlank(errorMessage) && !Objects.equals(MatchSelectorStrategy.TEXT, strategy)) {
            throw new IllegalArgumentException(errorMessage);
        }

        return new LazyMatchLocator(locator, strategy.getName(), precision);
    }

    static LazyMatchLocator getWith(String locator, MatchSelectorStrategy strategy) {
        final var precision = CoreUtilities.isEqual(strategy, MatchSelectorStrategy.TEXT) ? MatchLazyLocatorFactoryConstants.NON_IMAGE_PRECISION : MatchLazyLocatorFactoryConstants.DEFAULT_PRECISION;
        return getWith(locator, strategy, precision);
    }

    static LazyMatchLocator getWithDefaultStrategy(String locator) {
        return getWith(locator, MatchSelectorStrategy.DEFAULT);
    }

    static LazyMatchLocator getImageLocator(String locator, double precision) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, precision);
    }

    static LazyMatchLocator getNearDefaultImageLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, MatchLazyLocatorFactoryConstants.NEAR_DEFAULT_PRECISION);
    }

    static LazyMatchLocator getExactImageLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, MatchLazyLocatorFactoryConstants.EXACT_PRECISION);
    }

    static LazyMatchLocator getNearExactImageLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, MatchLazyLocatorFactoryConstants.NEAR_EXACT_PRECISION);
    }

    static LazyMatchLocator getImageLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.IMAGE, MatchLazyLocatorFactoryConstants.DEFAULT_PRECISION);
    }

    static LazyMatchLocator getTextLocator(String locator) {
        return getWith(locator, MatchSelectorStrategy.TEXT);
    }
}
