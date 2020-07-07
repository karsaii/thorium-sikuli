package com.github.karsaii.framework.sikuli.namespaces.predicates;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.namespaces.StringUtilities;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliFormatterConstants;
import org.sikuli.script.FindFailed;

public interface FindFailedPredicates {
    static boolean isValidFindFailedException(FindFailed exception) {
        return CoreUtilities.isException(exception) && CoreUtilities.isNotEqual(exception, SikuliCoreConstants.INVALID_FIND_FAILED_EXCEPTION);
    }

    static boolean isFindFailed(Exception ex) {
        return StringUtilities.contains(ex.getLocalizedMessage(), SikuliFormatterConstants.FIND_ALL_EXCEPTION_FRAGMENT);
    }

    static boolean isNonFindFailedException(Exception ex) {
        final var message = ex.getLocalizedMessage();
        return (
                StringUtilities.contains(message, SikuliFormatterConstants.NON_FIND_ALL_EXCEPTION) ||
                StringUtilities.uncontains(message, SikuliFormatterConstants.FIND_ALL_EXCEPTION_FRAGMENT)
        );
    }
}
