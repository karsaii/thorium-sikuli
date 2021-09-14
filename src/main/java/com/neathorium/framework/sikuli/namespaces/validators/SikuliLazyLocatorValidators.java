package com.neathorium.framework.sikuli.namespaces.validators;

import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.framework.core.namespaces.validators.LazyLocatorValidators;
import com.neathorium.framework.core.records.lazy.LazyLocator;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface SikuliLazyLocatorValidators {
    static String isInvalidLazyLocator(LazyLocator data) {
        var message = LazyLocatorValidators.isInvalidLazyLocatorCommon(data);
        if (isBlank(message)) {
            message += (
                MatchLocatorValidators.isValidStrategy(data.STRATEGY)
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isInvalidLazyLocator", message);
    }
}
