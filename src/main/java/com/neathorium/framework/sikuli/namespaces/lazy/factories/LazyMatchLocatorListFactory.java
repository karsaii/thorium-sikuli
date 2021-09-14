package com.neathorium.framework.sikuli.namespaces.lazy.factories;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.LazyMatchLocatorList;
import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface LazyMatchLocatorListFactory {
    static LazyMatchLocatorList getWith(List<LazyMatchLocator> list) {
        return new LazyMatchLocatorList(list);
    }

    static LazyMatchLocatorList getWith(LazyMatchLocator... locators) {
        return getWith(Arrays.asList(locators));
    }

    static LazyMatchLocatorList getWithEmptyList() {
        return getWith(new ArrayList<>());
    }

    static LazyMatchLocatorList getWithSingleItem(LazyMatchLocator locator) {
        return new LazyMatchLocatorList(List.of(locator));
    }
}
