package com.github.karsaii.framework.sikuli.namespaces.validators;

import com.github.karsaii.core.namespaces.predicates.DataPredicates;
import com.github.karsaii.core.namespaces.validators.DataValidators;
import com.github.karsaii.core.records.Data;
import org.sikuli.script.Match;

public interface MatchDataValidators {
    static boolean isNull(Data<Match> data) {
        return DataPredicates.isValidNonFalseAndValidContained(data, MatchValidators::isNull);
    }

    static boolean isNotNull(Data<Match> data) {
        return DataPredicates.isValidNonFalseAndValidContained(data, MatchValidators::isNotNull);
    }
}
