package com.github.karsaii.framework.sikuli.records.lazy;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;

import java.util.Map;
import java.util.Objects;

public class CachedLazyMatchData {
    public final LazyMatch match;
    public final Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys;

    public CachedLazyMatchData(LazyMatch match, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        this.match = match;
        this.typeKeys = typeKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (CachedLazyMatchData) o;
        return Objects.equals(match, that.match) && Objects.equals(typeKeys, that.typeKeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(match, typeKeys);
    }

    @Override
    public String toString() {
        return "CachedLazyMatchData{" +
                "match=" + match +
                ", typeKeys=" + typeKeys +
                '}';
    }
}
