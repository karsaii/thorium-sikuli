package com.github.karsaii.framework.sikuli.constants;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.namespaces.factories.LazyMatchFactory;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatch;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.getIncrementalUUID;

public abstract class SikuliCoreConstants {
    public static AtomicInteger ATOMIC_COUNT = new AtomicInteger();
    public static final String EMPTY_LOCATOR_STRING = CoreFormatterConstants.EMPTY;
    public static final LazyMatch NULL_LAZY_MATCH = LazyMatchFactory.getWithDefaultLocatorsAndValidator("Null Lazy Match " + getIncrementalUUID(ATOMIC_COUNT));
    public static final FindFailed NULL_EXCEPTION = new FindFailed("Null find failed exception - some parameter or such is wrong, somewhere" + CoreFormatterConstants.END_LINE);
    public static final List<Match> NULL_MATCH_LIST = new ArrayList<>();
    public static final Match NULL_MATCH = new Match();
    public static final Iterator<Match> NULL_MATCH_ITERATOR = NULL_MATCH_LIST.iterator();

    public static final MatchList NULL_MATCH_MATCHLIST = new MatchList(NULL_MATCH_LIST);
}
