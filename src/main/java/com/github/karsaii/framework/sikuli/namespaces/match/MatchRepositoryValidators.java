package com.github.karsaii.framework.sikuli.namespaces.match;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.records.lazy.CachedLazyMatchData;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface MatchRepositoryValidators {
    static <T> String isInvalidContainsMatchMessage(Map<String, CachedLazyMatchData> matchRepository, String name, Data<?> defaultValue) {
        var message = (
            CoreFormatter.isNullMessageWithName(matchRepository, "Match Repository") +
            CoreFormatter.isBlankMessageWithName(name, "Name") +
            CoreFormatter.isNullMessageWithName(defaultValue, "Default Data Value")
        );
        return isNotBlank(message) ? "isInvalidContainsMatchMessage: " + CoreFormatterConstants.PARAMETER_ISSUES_LINE + message : CoreFormatterConstants.EMPTY;
    }
}
