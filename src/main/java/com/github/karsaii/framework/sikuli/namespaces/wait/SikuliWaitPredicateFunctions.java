package com.github.karsaii.framework.sikuli.namespaces.wait;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.namespaces.SikuliUtilities;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatch;

import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;

public interface SikuliWaitPredicateFunctions {
    static <T> boolean isFalsyData(T object) {
        return (
            ((object instanceof Data) && (isInvalidOrFalse((Data<?>) object))) ||
            ((object instanceof LazyMatch) && (SikuliUtilities.isNullLazyMatch((LazyMatch)object)))
        ) || CoreUtilities.isFalse(object);
    }

    static <T> boolean isTruthyData(T object) {
        return (
            ((object instanceof Data) && (isValidNonFalse((Data<?>) object))) ||
            ((object instanceof LazyMatch) && (SikuliUtilities.isNullLazyMatch((LazyMatch)object)))
        ) || CoreUtilities.isTrue(object);
    }
}
