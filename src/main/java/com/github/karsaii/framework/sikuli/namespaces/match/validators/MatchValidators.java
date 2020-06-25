package com.github.karsaii.framework.sikuli.namespaces.match.validators;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
import org.sikuli.script.Match;

public interface MatchValidators {
    static boolean isNotNull(Match match) {
        return NullableFunctions.isNotNull(match) && CoreUtilities.isNotEqual(match, SikuliCoreConstants.NULL_MATCH);
    }
}
