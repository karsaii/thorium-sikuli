package com.neathorium.framework.sikuli.namespaces;

import com.neathorium.framework.sikuli.constants.SikuliCoreConstants;
import com.neathorium.framework.sikuli.constants.SikuliFormatterConstants;
import com.neathorium.framework.sikuli.namespaces.extensions.boilers.LazyMatchLocatorList;
import com.neathorium.framework.sikuli.namespaces.lazy.factories.LazyIndexedMatchFactory;
import com.neathorium.framework.sikuli.records.lazy.LazyMatch;
import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;
import com.neathorium.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;
import com.neathorium.framework.sikuli.records.lazy.filtered.MatchFilterData;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.EmptiableFunctions;
import com.neathorium.core.namespaces.StringUtilities;
import com.neathorium.framework.core.records.lazy.LazyLocator;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import static com.neathorium.core.extensions.namespaces.CoreUtilities.areAll;
import static com.neathorium.core.extensions.namespaces.NullableFunctions.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface SikuliUtilities {
    static boolean isFindFailed(Exception ex) {
        return StringUtilities.contains(ex.getLocalizedMessage(), SikuliFormatterConstants.FIND_ALL_EXCEPTION_FRAGMENT);
    }
    static boolean isValidFindFailedException(Exception exception) {
        return CoreUtilities.isException(exception) && CoreUtilities.isNotEqual(exception.getLocalizedMessage(), SikuliCoreConstants.INVALID_FIND_FAILED_EXCEPTION.getLocalizedMessage()) && isFindFailed(exception);
    }


    static boolean isInvalidLazyLocator(LazyLocator data) {
        return isNull(data) || isBlank(data.LOCATOR) || isNull(data.STRATEGY);
    }

    static boolean areNullLazyData(LazyLocator... data) {
        return areAll(SikuliUtilities::isInvalidLazyLocator, data);
    }

    static LazyLocator[] getEmptyLazyLocatorArray() {
        return new LazyLocator[0];
    }

    static boolean areNullLazyData(List<LazyMatchLocator> data) {
        return areNullLazyData(data.toArray(getEmptyLazyLocatorArray()));
    }

    static boolean isNullLazyDataList(LazyMatchLocatorList list) {
        return EmptiableFunctions.isNullOrEmpty(list) || areNullLazyData(list.list);
    }

    static boolean isNotNullLazyData(LazyLocator data) {
        return !isInvalidLazyLocator(data);
    }

    static <T> boolean isNullAbstractLazyElementParametersList(Collection<T> data, Predicate<T> validator) {
        for(T params : data) {
            if (validator.test(params)) {
                return false;
            }
        }

        return true;
    }

    static boolean isNullLazyMatch(LazyMatch element) {
        return (
            isNull(element) ||
            isBlank(element.name) ||
            CoreUtilities.areAnyNull(element.parameters, element.validator) ||
            element.parameters.isEmpty() ||
            isNullAbstractLazyElementParametersList(element.parameters.values(), element.validator)
        );
    }

    static boolean isNotNullLazyElement(LazyMatch element) {
        return !isNullLazyMatch(element);
    }


    static <T> Map<T, LazyFilteredMatchParameters> getParametersCopy(Map<T, LazyFilteredMatchParameters> source) {
        final var keys = source.keySet().iterator();
        final var values = source.values().iterator();

        final var map = Collections.synchronizedMap(new LinkedHashMap<T, LazyFilteredMatchParameters>());
        LazyFilteredMatchParameters lep;
        while(keys.hasNext() && values.hasNext()) {
            lep = values.next();
            map.putIfAbsent(keys.next(), LazyIndexedMatchFactory.getWithFilterDataAndLocatorList((MatchFilterData<?>) lep.ELEMENT_FILTER_DATA, lep.PROBABILITY, lep.LAZY_LOCATORS, lep.GETTER));
        }

        return map;
    }
}
