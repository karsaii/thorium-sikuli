package com.neathorium.framework.sikuli.namespaces.wait;

import com.neathorium.framework.sikuli.namespaces.SikuliUtilities;
import com.neathorium.framework.sikuli.records.lazy.LazyMatch;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.core.records.Data;

public interface SikuliWaitPredicateFunctions {
    static <T> boolean isFalsyData(T object) {
        return (
            ((object instanceof Data) && (DataPredicates.isInvalidOrFalse((Data<?>) object))) ||
            ((object instanceof LazyMatch) && (SikuliUtilities.isNullLazyMatch((LazyMatch)object)))
        ) || CoreUtilities.isFalse(object);
    }

    static <T> boolean isTruthyData(T object) {
        return (
            ((object instanceof Data) && (DataPredicates.isValidNonFalse((Data<?>) object))) ||
            ((object instanceof LazyMatch) && (SikuliUtilities.isNullLazyMatch((LazyMatch)object)))
        ) || CoreUtilities.isTrue(object);
    }
}
