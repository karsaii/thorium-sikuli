package com.github.karsaii.framework.sikuli.namespaces.match.validators;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.namespaces.validators.DataValidators;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface MatchGetterValidators {
    static String isInvalidElementByTextParameters(Data<MatchList> data, String text) {
        var message = DataValidators.isInvalidOrFalseMessage(data) + CoreFormatter.isBlankMessageWithName(text, "Text");
        return isNotBlank(message) ? "getElementByTextParametersInvalidMessage: " + CoreFormatterConstants.PARAMETER_ISSUES_LINE + message : CoreFormatterConstants.EMPTY;
    }
}
