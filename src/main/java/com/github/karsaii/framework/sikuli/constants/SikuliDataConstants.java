package com.github.karsaii.framework.sikuli.constants;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.namespaces.repositories.MatchRepository;
import com.github.karsaii.framework.sikuli.records.ExternalMatchData;
import com.github.karsaii.framework.sikuli.records.lazy.CachedLazyMatchData;
import org.sikuli.script.Match;

import java.util.Map;

public abstract class SikuliDataConstants {
    public static final Data<String> NULL_LOCATOR = DataFactoryFunctions.getInvalidWithNameAndMessage(SikuliCoreConstants.EMPTY_LOCATOR_STRING, "defaultLocatorData", "Internal null locator string" + CoreFormatterConstants.END_LINE);
    public static final Data<Match> NULL_MATCH = DataFactoryFunctions.getWithNameAndMessage(SikuliCoreConstants.NULL_MATCH, false, "defaultNullMatchData", "Internal null match" + CoreFormatterConstants.END_LINE);
    public static final Data<MatchList> NULL_LIST = DataFactoryFunctions.getWithNameAndMessage(SikuliCoreConstants.NULL_MATCH_MATCHLIST, false, "nullList", "nullList data" + CoreFormatterConstants.END_LINE);
    public static final Data<Integer> NULL_INTEGER_NULL_REGION = DataFactoryFunctions.getWithNameAndMessage(0, false, "nullIntegerNullRegion", SikuliFormatterConstants.REGION_WAS_NULL);
    public static final Map<String, DecoratedList<SelectorKeySpecificityData>> NULL_CACHED_KEYS = MatchRepository.getInitializedTypeKeysMap();
    public static final ExternalMatchData NULL_EXTERNAL_ELEMENT_DATA = new ExternalMatchData(NULL_CACHED_KEYS, RegionDataConstants.NULL_REGION_ALL);
    public static final Data<ExternalMatchData> NULL_EXTERNAL_MATCH = DataFactoryFunctions.getWithNameAndMessage(NULL_EXTERNAL_ELEMENT_DATA, false, "nullExternalElement", "nullExternalElement data" + CoreFormatterConstants.END_LINE);
    public static final CachedLazyMatchData NULL_CACHED_LAZY_MATCH_DATA = new CachedLazyMatchData(SikuliCoreConstants.NULL_LAZY_MATCH, NULL_CACHED_KEYS);
    public static final Data<CachedLazyMatchData> NULL_CACHED_LAZY_MATCH = DataFactoryFunctions.getWithNameAndMessage(NULL_CACHED_LAZY_MATCH_DATA, false, "nullCachedLazyMatch", "nullCachedLazyMatch data" + CoreFormatterConstants.END_LINE);
    public static final Data<CachedLazyMatchData> MATCH_WAS_NOT_CACHED = DataFactoryFunctions.getWithNameAndMessage(NULL_CACHED_LAZY_MATCH_DATA, false, "getIfContains", "Match wasn't cached" + CoreFormatterConstants.END_LINE);
}
