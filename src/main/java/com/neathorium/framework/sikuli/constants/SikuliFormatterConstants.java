package com.neathorium.framework.sikuli.constants;

import com.neathorium.core.constants.validators.CoreFormatterConstants;

public abstract class SikuliFormatterConstants {
    public static final String MATCHES = "matches";
    public static final String LAZY_MATCH = "Lazy Match ";
    public static final String REGION_WAS_NULL = "Region " + CoreFormatterConstants.WAS_NULL;

    public static final String NON_FIND_ALL_EXCEPTION = "Non-findAll runtime exception passed - Sikuli conflated Runtime with findAll... " + CoreFormatterConstants.COLON_NEWLINE;

    public static final String FIND_ALL_EXCEPTION_FRAGMENT = "SikuliX: findAll:";
    public static final String LOCATOR_WAS_NULL = "Locator" + CoreFormatterConstants.WAS_NULL;

    public static final String INVALID_FIND_FAILED_MESSAGE = "Invalid find failed exception - some parameter or such is wrong, somewhere" + CoreFormatterConstants.END_LINE;
    public static final String LAZY_MATCH_WAIT_PARAMETERS_WERE_NULL = LAZY_MATCH + " wait parameters" + CoreFormatterConstants.WERE_NULL;
    public static final String LAZY_MATCH_WAS_NULL = LAZY_MATCH + CoreFormatterConstants.WAS_NULL;
    public static final int NULL_MATCH_ID = -2;
}
