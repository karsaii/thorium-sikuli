package com.neathorium.framework.sikuli.namespaces.match.validators;

import com.neathorium.framework.sikuli.constants.RegionDataConstants;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.core.records.Data;
import org.sikuli.script.Match;

import java.util.Objects;

public interface MatchNullnessValidators {
    static boolean isNullMatch(Match match) {
        return (NullableFunctions.isNull(match) || Objects.equals(RegionDataConstants.NULL_REGION_ALL.object, match));
    }

    static boolean isNotNullMatch(Match match) {
        return !isNullMatch(match);
    }

    static boolean isNullMatch(Data<Match> match) {
        return DataPredicates.isInvalidOrFalse(match) || Objects.equals(RegionDataConstants.NULL_REGION_ALL, match) || isNullMatch(match.object);
    }

    static boolean isNotNullMatch(Data<Match> element) {
        return !isNullMatch(element);
    }
}
