package com.neathorium.framework.sikuli.namespaces.match.validators;

import com.neathorium.framework.sikuli.constants.SikuliCoreConstants;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import org.sikuli.script.Match;

public interface MatchValidators {
    static boolean isNotNull(Match match) {
        return NullableFunctions.isNotNull(match) && CoreUtilities.isNotEqual(match, SikuliCoreConstants.STOCK_MATCH);
    }
}
