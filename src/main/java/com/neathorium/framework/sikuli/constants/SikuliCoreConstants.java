package com.neathorium.framework.sikuli.constants;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.neathorium.framework.sikuli.namespaces.lazy.factories.LazyMatchFactory;
import com.neathorium.framework.sikuli.namespaces.utilities.BasicSikuliTypeUtilities;
import com.neathorium.framework.sikuli.records.lazy.LazyMatch;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.namespaces.FrameworkFunctions;
import org.sikuli.script.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static com.neathorium.core.extensions.namespaces.CoreUtilities.getIncrementalUUID;

public abstract class SikuliCoreConstants {
    public static AtomicInteger ATOMIC_COUNT = new AtomicInteger();
    public static final String EMPTY_LOCATOR_STRING = CoreFormatterConstants.EMPTY;

    public static final RuntimeException INVALID_FIND_FAILED_EXCEPTION = new RuntimeException(SikuliFormatterConstants.INVALID_FIND_FAILED_MESSAGE);
    public static final Match NULL_MATCH = new Match();
    public static final Match STOCK_MATCH = BasicSikuliTypeUtilities.getStock();
    public static final List<Match> EMPTY_LIST_OF_MATCH = new ArrayList<>();
    public static final MatchList INVALID_MATCHLIST = new MatchList(EMPTY_LIST_OF_MATCH);
    public static final LazyMatch INVALID_LAZY_MATCH = LazyMatchFactory.getWithDefaultLocatorsAndValidator("Null Lazy Match " + getIncrementalUUID(ATOMIC_COUNT));
    public static final Function<Data<DecoratedList<?>>, String> MATCH_LIST_VALIDATOR = CoreFormatter.isValidTypedNonEmptyListMessage(Match.class);
    public static final Function<Data<DecoratedList<?>>, Data<Integer>> MATCH_COUNT = FrameworkFunctions.getCountOfElements("Match");
}
