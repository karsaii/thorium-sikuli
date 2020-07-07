package com.github.karsaii.framework.sikuli.namespaces.validators;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
import org.sikuli.script.Match;

import java.util.Objects;

public interface MatchValidators {
    static boolean isNull(Match match) {
        return NullableFunctions.isNull(match) || Objects.equals(match, SikuliCoreConstants.NULL_MATCH);
    }

    static boolean isNotNull(Match match) {
        return NullableFunctions.isNotNull(match) && CoreUtilities.isNotEqual(match, SikuliCoreConstants.NULL_MATCH);
    }
}
