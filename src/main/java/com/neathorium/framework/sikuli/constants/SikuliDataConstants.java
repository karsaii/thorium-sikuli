package com.neathorium.framework.sikuli.constants;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.neathorium.framework.sikuli.namespaces.repositories.MatchRepository;
import com.neathorium.framework.sikuli.records.ExternalMatchData;
import com.neathorium.framework.sikuli.records.lazy.CachedLazyMatchData;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.selector.records.SelectorKeySpecificityData;
import org.sikuli.script.Match;

import java.util.Map;


public abstract class SikuliDataConstants {
    public static final Data<String> NULL_LOCATOR = DataFactoryFunctions.getInvalidWith(SikuliCoreConstants.EMPTY_LOCATOR_STRING, "defaultLocatorData", "Internal null locator string" + CoreFormatterConstants.END_LINE);
    public static final Data<Match> NULL_MATCH = DataFactoryFunctions.getWith(SikuliCoreConstants.STOCK_MATCH, false, "defaultNullMatchData", "Internal null match" + CoreFormatterConstants.END_LINE);
    public static final Data<MatchList> NULL_MATCH_LIST = DataFactoryFunctions.getWith(SikuliCoreConstants.INVALID_MATCHLIST, false, "nullList", "nullList data" + CoreFormatterConstants.END_LINE);
    public static final Data<Integer> NULL_INTEGER_NULL_REGION = DataFactoryFunctions.getWith(0, false, "nullIntegerNullRegion", SikuliFormatterConstants.REGION_WAS_NULL);
    public static final Map<String, DecoratedList<SelectorKeySpecificityData>> NULL_CACHED_KEYS = MatchRepository.getInitializedTypeKeysMap();
    public static final ExternalMatchData NULL_EXTERNAL_ELEMENT_DATA = new ExternalMatchData(NULL_CACHED_KEYS, RegionDataConstants.NULL_REGION_ALL);
    public static final Data<ExternalMatchData> NULL_EXTERNAL_MATCH = DataFactoryFunctions.getWith(NULL_EXTERNAL_ELEMENT_DATA, false, "nullExternalElement", "nullExternalElement data" + CoreFormatterConstants.END_LINE);
    public static final CachedLazyMatchData NULL_CACHED_LAZY_MATCH_DATA = new CachedLazyMatchData(SikuliCoreConstants.INVALID_LAZY_MATCH, NULL_CACHED_KEYS);
    public static final Data<CachedLazyMatchData> NULL_CACHED_LAZY_MATCH = DataFactoryFunctions.getWith(NULL_CACHED_LAZY_MATCH_DATA, false, "nullCachedLazyMatch", "nullCachedLazyMatch data" + CoreFormatterConstants.END_LINE);
    public static final Data<CachedLazyMatchData> MATCH_WAS_NOT_CACHED = DataFactoryFunctions.getWith(NULL_CACHED_LAZY_MATCH_DATA, false, "getIfContains", "Match wasn't cached" + CoreFormatterConstants.END_LINE);

    public static final Data<Boolean> LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL = DataFactoryFunctions.getBoolean(false, SikuliFormatterConstants.LAZY_MATCH_WAIT_PARAMETERS_WERE_NULL);
    public static final Data<Boolean> LAZY_ELEMENT_WAS_NULL = DataFactoryFunctions.getBoolean(false, SikuliFormatterConstants.LAZY_MATCH_WAS_NULL);
}
