package com.github.karsaii.framework.sikuli.namespaces;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;

public interface SikuliFormatters {
    static String getFindAllMessage(int size, boolean status) {
        return (status ? "No Exception occurred, Found " + size + " matches" : "An exception has occurred during finding matches") + CoreFormatterConstants.END_LINE;
    }


}
