package com.neathorium.framework.sikuli.namespaces.utilities;

import com.neathorium.framework.sikuli.constants.SikuliFormatterConstants;
import org.sikuli.script.Match;

public interface BasicSikuliTypeUtilities {
    static Match getStock() {
        final var match = new Match();
        match.setIndex(SikuliFormatterConstants.NULL_MATCH_ID);

        return match;
    }
}
