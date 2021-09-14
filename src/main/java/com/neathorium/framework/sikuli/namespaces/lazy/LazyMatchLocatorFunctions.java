package com.neathorium.framework.sikuli.namespaces.lazy;

import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;
import org.sikuli.script.Pattern;

public interface LazyMatchLocatorFunctions {
    static String getTextFromLocator(LazyMatchLocator target) {
        return target.LOCATOR;
    }

    static Pattern getPatternFromLocator(LazyMatchLocator target) {
        final var locator = getTextFromLocator(target);
        return new Pattern(locator).similar(target.PRECISION);
    }
}
