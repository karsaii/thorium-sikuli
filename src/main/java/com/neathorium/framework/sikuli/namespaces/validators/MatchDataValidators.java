package com.neathorium.framework.sikuli.namespaces.validators;

import com.neathorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.core.records.Data;
import org.sikuli.script.Match;

public interface MatchDataValidators {
    static boolean isNull(Data<Match> data) {
        return DataPredicates.isValidNonFalseAndValidContained(data, MatchValidators::isNull);
    }

    static boolean isNotNull(Data<Match> data) {
        return DataPredicates.isValidNonFalseAndValidContained(data, MatchValidators::isNotNull);
    }
}
