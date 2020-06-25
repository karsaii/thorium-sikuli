package com.github.karsaii.framework.sikuli.namespaces.factories;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorFactory;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.sikuli.enums.MatchSelectorStrategy;
import com.github.karsaii.framework.sikuli.namespaces.match.MatchStrategyFunctions;
import com.github.karsaii.framework.sikuli.namespaces.validators.MatchLocatorValidators;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface MatchLazyLocatorFactory {
    static LazyLocator getWith(String locator, MatchSelectorStrategy strategy) {
        return LazyLocatorFactory.get(locator, strategy.getName());
    }

    static LazyLocator getWithDefaultStrategy(String locator) {
        return getWith(locator, MatchSelectorStrategy.DEFAULT);
    }

    static LazyLocator getWithDefaults() {
        return getWithDefaultStrategy(CoreFormatterConstants.EMPTY);
    }

    static LazyLocator get(String locator, String strategy) {
        final var errorMessage = MatchLocatorValidators.isValidLocatorAndStrategy(locator, strategy);
        return isBlank(errorMessage) ? LazyLocatorFactory.get(locator, strategy) : getWithDefaults();
    }

    static LazyLocator get(String locator) {
        var localLocator = MatchStrategyFunctions.getTextLocator(locator);
        if (isNotBlank(localLocator)) {
            return getWith(localLocator, MatchSelectorStrategy.TEXT);
        }

        localLocator = MatchStrategyFunctions.getImageLocator(locator);
        return isNotBlank(localLocator) ? getWith(localLocator, MatchSelectorStrategy.IMAGE) : getWithDefaults();
    }
}
