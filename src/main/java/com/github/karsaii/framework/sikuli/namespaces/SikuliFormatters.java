package com.github.karsaii.framework.sikuli.namespaces;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.predicates.DoublePredicates;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;

import static com.github.karsaii.framework.sikuli.constants.factories.MatchLazyLocatorFactoryConstants.DEFAULT_PRECISION;

public interface SikuliFormatters {
    static String getFindAllMessage(int size, boolean status) {
        return (status ? "No Exception occurred, Found " + size + " matches" : "An exception has occurred during finding matches") + CoreFormatterConstants.END_LINE;
    }

    static String getPrecisionLessThanExpectedMessage(double precision, double expected) {
        var message = "";
        if (DoublePredicates.isSmallerThan(precision, expected)) {
            message += "Precision(\"" + precision + "\") was less than default(\"" + DEFAULT_PRECISION + "\")" + CoreFormatterConstants.END_LINE;
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("getPrecisionLessThanExpectedMessage", message);
    }
}
