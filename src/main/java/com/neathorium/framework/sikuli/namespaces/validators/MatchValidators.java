package com.neathorium.framework.sikuli.namespaces.validators;

import com.neathorium.framework.sikuli.constants.SikuliCoreConstants;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import org.sikuli.script.Match;

import java.util.Objects;

public interface MatchValidators {
    static boolean isNull(Match match) {
        return NullableFunctions.isNull(match) || Objects.equals(match, SikuliCoreConstants.STOCK_MATCH);
    }

    static boolean isNotNull(Match match) {
        return NullableFunctions.isNotNull(match) && CoreUtilities.isNotEqual(match, SikuliCoreConstants.STOCK_MATCH);
    }
}
