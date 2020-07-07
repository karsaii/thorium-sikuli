package com.github.karsaii.framework.sikuli.namespaces.match.validators;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.constants.RegionDataConstants;
import org.sikuli.script.Match;

import java.util.Objects;

import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNull;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;

public interface MatchNullnessValidators {
    static boolean isNullMatch(Match match) {
        return (isNull(match) || Objects.equals(RegionDataConstants.NULL_REGION_ALL.object, match));
    }

    static boolean isNotNullMatch(Match match) {
        return !isNullMatch(match);
    }

    static boolean isNullMatch(Data<Match> match) {
        return isInvalidOrFalse(match) || Objects.equals(RegionDataConstants.NULL_REGION_ALL, match) || isNullMatch(match.object);
    }

    static boolean isNotNullMatch(Data<Match> element) {
        return !isNullMatch(element);
    }
}
