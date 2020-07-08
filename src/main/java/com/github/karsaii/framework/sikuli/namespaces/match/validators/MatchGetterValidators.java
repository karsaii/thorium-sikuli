package com.github.karsaii.framework.sikuli.namespaces.match.validators;

import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.namespaces.validators.DataValidators;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;

public interface MatchGetterValidators {
    static String isInvalidElementByTextParameters(Data<MatchList> data, String text) {
        return getNamedErrorMessageOrEmpty("getElementByTextParametersInvalidMessage", DataValidators.isInvalidOrFalseMessage(data) + CoreFormatter.isBlankMessageWithName(text, "Text"));
    }
}
