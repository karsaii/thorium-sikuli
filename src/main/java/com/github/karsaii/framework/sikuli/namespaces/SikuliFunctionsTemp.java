package com.github.karsaii.framework.sikuli.namespaces;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.StringUtilities;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliFormatterConstants;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface SikuliFunctionsTemp {
    static <T> List<T> iteratorToList(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false).collect(Collectors.toList());
    }

    static boolean isValidFindFailedException(FindFailed exception) {
        return CoreUtilities.isException(exception) && CoreUtilities.isNotEqual(exception, SikuliCoreConstants.NULL_EXCEPTION);
    }

    static boolean isFindFailed(Exception ex) {
        return StringUtilities.contains(ex.getLocalizedMessage(), SikuliFormatterConstants.FIND_ALL_EXCEPTION_FRAGMENT);
    }

    static boolean isNonFindFailedException(Exception ex) {
        final var message = ex.getLocalizedMessage();
        return (
            StringUtilities.contains(message, SikuliFormatterConstants.NON_FIND_ALL_EXCEPTION) ||
            StringUtilities.uncontains(message, SikuliFormatterConstants.FIND_ALL_EXCEPTION_FRAGMENT)
        );
    }

    private static <T> FindFailed toFindFailed(RuntimeException ex) {
        final var message = ex.getLocalizedMessage();
        return new FindFailed(isFindFailed(ex) ? message : SikuliFormatterConstants.NON_FIND_ALL_EXCEPTION + message);
    }

    private static <T> Iterator<Match> findAllNormalizedExceptions(Region region, T target) throws FindFailed {
        var exception = SikuliCoreConstants.NULL_EXCEPTION;
        var matches = SikuliCoreConstants.NULL_MATCH_ITERATOR;
        try {
            matches = region.findAll(target);
        } catch (RuntimeException ex) {
            if (!isFindFailed(ex)) {
                throw ex;
            }

            exception = toFindFailed(ex);
        } catch (FindFailed ex) {
            exception = ex;
        }

        if (isValidFindFailedException(exception)) {
            throw exception;
        }

        return matches;
    }

    static <T> Data<MatchList> getElements(Region region, T target) {
        var exception = SikuliCoreConstants.NULL_EXCEPTION;
        var matches = SikuliCoreConstants.NULL_MATCH_ITERATOR;
        try {
            matches = findAllNormalizedExceptions(region, target);
        } catch (FindFailed ex) {
            exception = ex;
        }

        final var list = iteratorToList(matches);
        final var status = !isValidFindFailedException(exception);
        final var object = status ? new MatchList(list) : SikuliCoreConstants.NULL_MATCH_MATCHLIST;
        return DataFactoryFunctions.getWithNameAndMessage(object, status, "getElements", SikuliFormatters.getFindAllMessage(list.size(), status), exception);
    }
}
