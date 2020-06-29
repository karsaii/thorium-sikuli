package com.github.karsaii.framework.sikuli.namespaces;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.factories.DecoratedListFactory;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicateFunctions;
import com.github.karsaii.core.extensions.namespaces.predicates.CollectionPredicateFunctions;
import com.github.karsaii.core.namespaces.BaseExecutionFunctions;
import com.github.karsaii.core.namespaces.DataExecutionFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.StringUtilities;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.FrameworkCoreUtilities;
import com.github.karsaii.framework.core.namespaces.FrameworkFunctions;
import com.github.karsaii.framework.sikuli.namespaces.factories.SikuliDataFactory;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.core.records.ProbabilityData;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;
import com.github.karsaii.framework.core.constants.AdjusterConstants;
import com.github.karsaii.framework.core.namespaces.Adjuster;
import com.github.karsaii.framework.sikuli.constants.MatchFinderConstants;
import com.github.karsaii.framework.sikuli.constants.RegionDataConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliDataConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliFormatterConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliGetOrderConstants;
import com.github.karsaii.framework.sikuli.enums.ManyMatchesGetter;
import com.github.karsaii.framework.sikuli.enums.SingleMatchGetter;
import com.github.karsaii.framework.sikuli.namespaces.factories.MatchLazyLocatorFactory;
import com.github.karsaii.framework.sikuli.namespaces.factories.MatchListFactory;
import com.github.karsaii.framework.sikuli.namespaces.match.MatchFilterFunctions;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.github.karsaii.framework.sikuli.namespaces.factories.LazyMatchWithOptionsDataFactory;
import com.github.karsaii.framework.sikuli.namespaces.lazy.LazyIndexedMatchFactory;
import com.github.karsaii.framework.sikuli.namespaces.region.RegionExecutionFunctions;
import com.github.karsaii.framework.sikuli.namespaces.region.RegionFunctionFactory;
import com.github.karsaii.framework.sikuli.namespaces.repositories.MatchRepository;
import com.github.karsaii.framework.sikuli.namespaces.match.validators.MatchGetterValidators;
import com.github.karsaii.framework.sikuli.namespaces.match.validators.MatchNullnessValidators;
import com.github.karsaii.framework.sikuli.namespaces.match.validators.MatchValidators;
import com.github.karsaii.framework.sikuli.namespaces.validators.SikuliFormatter;
import com.github.karsaii.framework.sikuli.records.ExternalMatchData;
import com.github.karsaii.framework.sikuli.records.ExternalSikuliSelectorData;
import com.github.karsaii.framework.sikuli.records.GetWithRegionData;
import com.github.karsaii.framework.sikuli.records.MatchFilterParameters;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatch;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatchWithOptionsData;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAnyNull;
import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areNotNull;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNull;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.appendMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.prependMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isInvalidOrFalseMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNegativeMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNullOrEmptyMessage;
import static com.github.karsaii.core.namespaces.validators.DataValidators.isInvalidOrFalse;
import static com.github.karsaii.core.namespaces.validators.DataValidators.isValidNonFalse;
import static com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter.isNullLazyElementMessage;
import static com.github.karsaii.framework.sikuli.constants.RegionDataConstants.NULL_REGION_ALL;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SikuliFunctions {
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

    static Data<Integer> getCountOfElements(Data<MatchList> data) {
        return FrameworkFunctions.getCountOfElements(SikuliDataFactory.getUnwrapped(data), SikuliFormatterConstants.MATCHES);
    }

    static RegionFunction<Integer> getCountOfElements(RegionFunction<MatchList> getter) {
        return RegionFunctionFactory.get(ifDependency(
            "getCountOfElements",
            isNotNull(getter),
            FrameworkCoreFormatter.isValidTypedNonEmptyListMessage(Match.class),
            getter.andThen(SikuliDataFactory::getUnwrapped),
            FrameworkFunctions.getCountOfElements("Match"),
            SikuliDataConstants.NULL_INTEGER_NULL_REGION
        ));
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

    private static Data<MatchList> getElementsCore(Data<Region> contextData, LazyLocator locator) {
        final var nameof = "getElementsCore";
        final var negative = SikuliCoreConstants.NULL_MATCH_MATCHLIST;
        var errorMessage = FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SikuliUtilities::getLocator) + isInvalidOrFalseMessage(contextData);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(negative, nameof, errorMessage);
        }

        final var context = contextData.object;
        errorMessage = CoreFormatter.isNullMessageWithName(context, "Context");
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(negative, nameof, errorMessage);
        }

        final var data = SikuliUtilities.getLocator(locator);
        errorMessage = isInvalidOrFalseMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(negative, nameof, errorMessage);
        }

        return getElements(context, data.object);
    }

    private static Function<Data<Region>, Data<MatchList>> getElementsCore(LazyLocator locator) {
        return context -> getElementsCore(context, locator);
    }

    private static RegionFunction<MatchList> getElementsIf(String errorMessage, RegionFunction<MatchList> positive) {
        return RegionExecutionFunctions.ifRegion("getElements", errorMessage, positive, SikuliDataConstants.NULL_LIST);
    }

    private static Data<MatchList> getElements(Region region, LazyLocatorList locators, Function<LazyLocator, RegionFunction<MatchList>> getter) {
        final var elementList = MatchListFactory.getWithEmptyList();
        final var length = locators.size();
        Data<MatchList> data;
        LazyLocator locator;
        MatchList list;
        var message = new StringBuilder();
        var index = 0;
        for (; index < length; ++index) {
            locator = locators.get(index);
            if (isNull(locator)) {
                break;
            }

            data = getter.apply(locator).apply(region);
            message.append(index + data.message.toString() + CoreFormatterConstants.END_LINE);
            if (isInvalidOrFalse(data)) {
                continue;
            }

            list = data.object;
            if (Objects.equals(locator.strategy, "id") && (list.isMany())) {
                message.append("There's more than one element with id(\"" + locator.locator + "\") - amount(\"" + list.size() + "\"). Returning" + CoreFormatterConstants.END_LINE);
                break;
            }

            elementList.addAllNullSafe(list);
        }

        return DataFactoryFunctions.getWithMessage(elementList, elementList.isNotNullAndNonEmpty() && (index == length), message.toString());
    }

    private static RegionFunction<MatchList> getElements(RegionFunction<Region> getter, LazyLocator locator) {
        return RegionFunctionFactory.get(DataExecutionFunctions.validChain(getter, getElementsCore(locator), SikuliDataConstants.NULL_LIST));
    }

    static Data<Region> getSearchContext(Region region) {
        final var status = isNotNull(region);
        final var message = "Region was" + (status ? "" : "n't") + " null" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getWithNameAndMessage(region, status, "getSearchContext", message);
    }

    static RegionFunction<MatchList> getElements(LazyLocator locator) {
        return getElementsIf(FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SikuliUtilities::getLocator), getElements(SikuliFunctions::getSearchContext, locator));
    }

    static RegionFunction<MatchList> getElements(LazyLocatorList locators, Function<LazyLocator, RegionFunction<MatchList>> getter) {
        return getElementsIf(SikuliFormatter.getElementsParametersMessage(locators, getter), driver -> getElements(driver, locators, getter));
    }

    static RegionFunction<MatchList> getElements(LazyLocatorList locators) {
        return getElementsIf(SikuliFormatter.getElementsParametersMessage(locators), driver -> getElements(driver, locators, SikuliFunctions::getElements));
    }

    static Data<Match> getElementByIndex(Data<MatchList> data, int index) {
        final var nameof = "getElementByIndex";
        if (isInvalidOrFalse(data) || BasicPredicateFunctions.isNegative(index)) {
            return prependMessage(SikuliDataConstants.NULL_MATCH, nameof, "Data or index was null. Index: " + index + " Data: " + data.toString());
        }

        final var object = data.object;
        if (object.isNullOrEmpty()) {
            return prependMessage(SikuliDataConstants.NULL_MATCH, nameof, "List " + CoreFormatterConstants.WAS_NULL);
        }

        final var size = object.size();
        final var status = (size > index);
        final var message = "Element was" + (status ? "" : "n't") + " found by index(\"" + index + "\"), list size: " + size + CoreFormatterConstants.END_LINE + data.message;
        return DataFactoryFunctions.getWithNameAndMessage(object.get(index), status, nameof, message);
    }

    static Function<Data<MatchList>, Data<Match>> getElementByIndex(int index) {
        return data -> getElementByIndex(data, index);
    }

    static RegionFunction<Match> getElementByIndex(RegionFunction<MatchList> getter, int index) {
        return RegionFunctionFactory.get(ifDependency(
                "getElementByIndexFrom",
                isNotNull(getter) && BasicPredicateFunctions.isNonNegative(index),
                DataExecutionFunctions.validChain(getter, getElementByIndex(index), SikuliDataConstants.NULL_MATCH),
                SikuliDataConstants.NULL_MATCH
        ));
    }

    static Data<Match> getElementByContainedText(Data<MatchList> data, String text) {
        final var nameof = "getElementByContainedText";
        final var errorMessage = MatchGetterValidators.isInvalidElementByTextParameters(data, text);
        if (isNotBlank(errorMessage)) {
            return prependMessage(SikuliDataConstants.NULL_MATCH, nameof, errorMessage);
        }

        final var object = data.object;
        if (object.isNullOrEmpty()) {
            return prependMessage(SikuliDataConstants.NULL_MATCH, nameof, "List " + CoreFormatterConstants.WAS_NULL);
        }

        final var length = object.size();
        var current = SikuliCoreConstants.NULL_MATCH;
        var index = 0;
        for (; (index < length); ++index) {
            current = object.get(index);
            if (StringUtilities.contains(current.getText(), text)) {
                break;
            }
        }

        final var status = MatchValidators.isNotNull(current) && (index < length);
        final var message = "Element was" + (status ? "" : "n't") + " found by text(\"" + text + "\"), list size: " + length + CoreFormatterConstants.END_LINE + data.message;
        return DataFactoryFunctions.getWithNameAndMessage(current, status, nameof, message);
    }

    static Function<Data<MatchList>, Data<Match>> getElementByContainedText(String message) {
        return data -> getElementByContainedText(data, message);
    }

    static RegionFunction<Match> getElementByContainedText(RegionFunction<MatchList> getter, String message) {
        return RegionFunctionFactory.get(ifDependency(
                "getElementByContainedText",
                isNotNull(getter) && isNotBlank(message),
                DataExecutionFunctions.validChain(getter, getElementByContainedText(message), SikuliDataConstants.NULL_MATCH),
                SikuliDataConstants.NULL_MATCH
        ));
    }

    static Data<Match> getElementFromSingle(Data<MatchList> data) {
        final var nameof = "getElementFromSingle";
        return isValidNonFalse(data) ? getElementByIndex(data, 0) : prependMessage(SikuliDataConstants.NULL_MATCH, nameof, "Data or index" + CoreFormatterConstants.WAS_NULL + data.toString());
    }

    static RegionFunction<Match> getElementFromSingle(RegionFunction<MatchList> getter) {
        return RegionFunctionFactory.get(ifDependency("getElementFromSingle", isNotNull(getter), DataExecutionFunctions.validChain(getter, getElementByIndex(0), SikuliDataConstants.NULL_MATCH), SikuliDataConstants.NULL_MATCH));
    }
    static <T> RegionFunction<T> getWithLazyLocator(GetWithRegionData<LazyLocatorList, LazyLocator, String, T> data) {
        return RegionFunctionFactory.get(DataExecutionFunctions.ifDependency(
            "getWithLazyLocator",
            areNotNull(data.locatorGetter, data.getter),
            data.getter.apply(SikuliUtilities.getLocator(data.locatorGetter.apply(data.locators)).object),
            data.guardData
        ));
    }

    static <WE, BY, BYY extends String, W extends DecoratedList<BY>> RegionFunction<WE> getFromSingle(
            Function<GetWithRegionData<W, BY, BYY, WE>, RegionFunction<WE>> getter,
            GetWithRegionData<W, BY, BYY, WE> data,
            String nameof
    ) {
        return RegionFunctionFactory.get(ifDependency(nameof, data.locators.isSingle(), getter.apply(data), data.guardData));
    }

    static RegionFunction<Match> getElementFromSingle(LazyLocatorList locator) {
        return getFromSingle(
            SikuliFunctions::getWithLazyLocator,
            new GetWithRegionData<LazyLocatorList, LazyLocator, String, Match>(locator, LazyLocatorList::first, SikuliFunctions::getElement, SikuliDataConstants.NULL_MATCH),
            "getElementFromSingle"
        );
    }


    private static Data<MatchList> getElementsAmountCore(Data<MatchList> data, String locator, int expected) {
        final var nameof = "getElementsAmountCore";
        var errorMessage = isInvalidOrFalseMessage(data) + CoreFormatter.isNullMessageWithName(locator, "Locator") + isNegativeMessage(expected);
        if (isNotBlank(errorMessage)) {
            return replaceMessage(SikuliDataConstants.NULL_LIST, nameof, errorMessage);
        }

        final var object = data.object;
        errorMessage = isNullOrEmptyMessage(object);
        if (isNotBlank(errorMessage)) {
            return appendMessage(data, errorMessage);
        }

        final var size = (
            data.status &&
            CollectionPredicateFunctions.isNonEmptyAndOfType(object, Match.class) &&
            CoreUtilities.isNotEqual(SikuliDataConstants.NULL_MATCH.object, object.first())
        ) ? object.size() : 0;
        final var status = size == expected;
        return DataFactoryFunctions.getWithNameAndMessage(object, status, nameof, FrameworkCoreFormatter.getElementsAmountMessage(locator, status, expected, size), data.exception);
    }

    static Function<Data<MatchList>, Data<MatchList>> getElementsAmountCore(String locator, int expected) {
        return data -> getElementsAmountCore(data, locator, expected);
    }

    static RegionFunction<MatchList> getElementsAmount(RegionFunction<MatchList> getter, LazyLocator locator, int expected) {
        return RegionFunctionFactory.get(ifDependency(
                "getElementsAmount",
                isNotNull(getter) && isNotBlank(FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SikuliUtilities::getLocator)) && BasicPredicateFunctions.isNonNegative(expected),
                DataExecutionFunctions.validChain(getter, getElementsAmountCore(SikuliUtilities.getLocator(locator).object, expected), SikuliDataConstants.NULL_LIST),
                SikuliDataConstants.NULL_LIST
        ));
    }

    static RegionFunction<MatchList> getElementsAmount(LazyLocator locator, int expected) {
        return RegionFunctionFactory.get(ifDependency(
                "getElementsAmount",
                isNotBlank(FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SikuliUtilities::getLocator)) && BasicPredicateFunctions.isNonNegative(expected),
                getElementsAmount(getElements(locator), locator, expected),
                SikuliDataConstants.NULL_LIST
        ));
    }

    static RegionFunction<MatchList> getSingleElementList(LazyLocator locator) {
        return getElementsAmount(locator, 1);
    }

    static RegionFunction<Match> getElement(LazyLocator locator) {
        return RegionFunctionFactory.get(ifDependency("getElement", FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SikuliUtilities::getLocator), getElementFromSingle(getSingleElementList(locator)), SikuliDataConstants.NULL_MATCH));
    }

    static RegionFunction<Match> getElement(String locator) {
        return RegionFunctionFactory.get(ifDependency("getElement", FrameworkCoreFormatter.isInvalidLocatorMessage(locator, MatchLazyLocatorFactory::get), getElementFromSingle(getSingleElementList(MatchLazyLocatorFactory.get(locator))), SikuliDataConstants.NULL_MATCH));
    }

    static <T> RegionFunction<ExternalMatchData> getLazyMatchByExternal(LazyMatch element, ExternalSikuliSelectorData externalData, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        //TODO: Validate all the parameters and provide overloads for defaults.
        final var nameof = "getLazyMatchByExternal";
        final var defaultValue = SikuliDataConstants.NULL_EXTERNAL_MATCH;
        return RegionFunctionFactory.get(BaseExecutionFunctions.ifDependency(
                nameof,
                FrameworkCoreFormatter.getExternalSelectorDataErrorMessage(element, externalData, nameof),
                (Region region) -> {
                    final var types = new ArrayList<>(typeKeys.keySet());
                    LazyLocator locator;
                    var selector = externalData.defaultSelector;
                    var parameterKey = "";
                    var selectorType = externalData.selectorType;
                    var currentElement = RegionDataConstants.NULL_REGION_ALL;

                    final var failedSelectors = DecoratedListFactory.getWith(String.class);
                    final var length = externalData.limit;
                    var index = 0;
                    var message = replaceMessage(CoreDataConstants.NULL_STRING, nameof, "");
                    var lep = LazyIndexedMatchFactory.getWithFilterParametersAndLocator(false, 0, MatchLazyLocatorFactory.getWithDefaults());
                    var getSelector = externalData.getSelector;
                    for(; index < length; ++index) {
                        selector = getSelector.apply(externalData.preferredProperties, failedSelectors.list).apply(region);
                        if (isInvalidOrFalse(selector)) {
                            continue;
                        }

                        if (isBlank(selector.object)) {
                            appendMessage(message, "Empty selector returned, attempt: " + index + CoreFormatterConstants.END_LINE);
                            continue;
                        }

                        locator = MatchLazyLocatorFactory.get(selector.object, selectorType);
                        parameterKey = FrameworkCoreFormatter.getUniqueGeneratedName(selectorType, SikuliCoreConstants.ATOMIC_COUNT);
                        lep = LazyIndexedMatchFactory.getWithFilterParametersAndLocator(false, 0, locator);
                        currentElement = MatchFilterFunctions.getElement(lep.lazyLocators, MatchFinderConstants.singleGetterMap, SingleMatchGetter.DEFAULT).apply(region);
                        if (MatchNullnessValidators.isNullMatch(currentElement)) {
                            break;
                        }

                        failedSelectors.addNullSafe(selector.object);
                    }

                    element.parameters.putIfAbsent(parameterKey, lep);
                    final var update = MatchRepository.updateTypeKeys(lep.lazyLocators, typeKeys, types, parameterKey);
                    return MatchNullnessValidators.isNotNullMatch(currentElement) ? (
                        DataFactoryFunctions.getWithNameAndMessage(new ExternalMatchData(typeKeys, currentElement), true, nameof, "External function yielded a match" + CoreFormatterConstants.END_LINE)
                    ) : replaceMessage(defaultValue, nameof, "All(\"" + length + "\") approaches were tried" + CoreFormatterConstants.END_LINE + currentElement.message.toString());
                },
                defaultValue
        ));
    }

    static Data<Match> cacheNonNullAndNotFalseLazyMatch(LazyMatch element, Data<ExternalMatchData> regular, Data<ExternalMatchData> external) {
        final var nameof = "cacheNonNullAndNotFalseLazyMatch";
        if (isNotBlank(isNullLazyElementMessage(element)) || areAnyNull(regular, external)) {
            return replaceMessage(NULL_REGION_ALL, nameof, CoreFormatterConstants.PARAMETER_ISSUES + CoreFormatterConstants.WAS_NULL);
        }

        final var regularStatus = isValidNonFalse(regular);
        final var externalStatus = isValidNonFalse(external);
        if (!(regularStatus || externalStatus)) {
            return NULL_REGION_ALL;
        }

        final var externalElement = (externalStatus ? external : regular).object;
        final var currentElement = externalElement.found;
        return MatchNullnessValidators.isNotNullMatch(currentElement) ? (
                appendMessage(currentElement, MatchRepository.cacheIfAbsent(element, FrameworkCoreUtilities.getKeysCopy(externalElement.typeKeys)))
        ) : prependMessage(currentElement, "All approaches were tried" + CoreFormatterConstants.END_LINE);
    }

    static <T> Data<Integer> getNextCachedKey(Map<String, T> parameterMap, Iterator<String> getOrder, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, int parameterIndex) {
        final var nameof = "getNextCachedKey";
        var type = typeKeys.getOrDefault(getOrder.next(), null);
        if (isNull(type)) {
            return replaceMessage(CoreDataConstants.NULL_INTEGER, nameof, "Type" + CoreFormatterConstants.WAS_NULL);
        }

        var index = parameterIndex;
        if (!type.hasIndex(index)) {
            if (!getOrder.hasNext()) {
                return replaceMessage(CoreDataConstants.NULL_INTEGER, nameof, "GetOrder doesn't have more entries" + CoreFormatterConstants.END_LINE);
            }

            type = typeKeys.get(getOrder.next());
            index = 0;
        }
        final var key = type.get(index).selectorKey;
        return (isNotNull(key) && parameterMap.containsKey(key) ? (
                DataFactoryFunctions.getWithMessage(index, true, key)
        ) : replaceMessage(CoreDataConstants.NULL_INTEGER, nameof, "The parameter map didn't contain an indexed com.github.karsaii.framework.core.selector-type it should have" + CoreFormatterConstants.END_LINE));
    }

    static Data<Integer> getNextKey(DecoratedList<String> keys, int parameterIndex) {
        return isNotNull(keys) && BasicPredicateFunctions.isNonNegative(parameterIndex) && keys.hasIndex(parameterIndex) ? (
                DataFactoryFunctions.getWithMessage(0, true, keys.get(parameterIndex))
        ) : replaceMessage(CoreDataConstants.NULL_INTEGER, "getNextKey", "The parameter map didn't contain an indexed com.github.karsaii.framework.core.selector-type it should have" + CoreFormatterConstants.END_LINE);
    }

    static <T> RegionFunction<Match> getLazyMatch(LazyMatchWithOptionsData data) {
        final var nameof = "getLazyMatch";
        return RegionFunctionFactory.get(BaseExecutionFunctions.ifDependency(
            nameof,
            FrameworkCoreFormatter.getLazyResultWithOptionsMessage(data, nameof),
            (Region region) -> {
                final var getOrder = data.getOrder.iterator();
                final var dataElement = data.element;
                final var name = dataElement.name;
                final var cached2 = MatchRepository.containsElement(dataElement.name);
                var currentElement = RegionDataConstants.NULL_REGION_ALL;
                if (isInvalidOrFalse(cached2)) {
                    return currentElement;
                }

                final var isCached = cached2.object;
                final var getResult = MatchRepository.getIfContains(dataElement);
                final var localElement = isCached ? getResult.object.match : dataElement;
                final var typeKeys = isCached ? getResult.object.typeKeys : MatchRepository.getInitializedTypeKeysMap();
                final var parameterMap = localElement.parameters;
                final var parameterKeys = DecoratedListFactory.getWith(parameterMap.keySet());
                final var types = DecoratedListFactory.getWith(typeKeys.keySet());
                var message = CoreDataConstants.NULL_STRING;
                var parameterIndex = 0;
                var index = 0;
                final var length = data.internalData.limit;
                for (; MatchNullnessValidators.isNullMatch(currentElement) && (index < length); ++index, ++parameterIndex) {
                    var keyData = isCached ? getNextCachedKey(parameterMap, getOrder, typeKeys, parameterIndex) : getNextKey(parameterKeys, parameterIndex);
                    if (isInvalidOrFalse(keyData)) {
                        return replaceMessage(currentElement, nameof, "Parameter key wasn't found in " + (isCached ? "cached" : "") + " keys" + CoreFormatterConstants.END_LINE);
                    }
                    parameterIndex = isCached ? keyData.object : parameterIndex;
                    var key = keyData.message.message;
                    var parameters = parameterMap.get(key);
                    if (isNull(parameters) || parameters.lazyLocators.isNullOrEmpty()) {
                        continue;
                    }

                    var locators = parameters.lazyLocators;
                    var update = MatchRepository.updateTypeKeys(name, locators, typeKeys, types, key);
                    if (isInvalidOrFalse(update)) {
                        continue;
                    }

                    var getter = parameters.getter;
                    var indexData = parameters.elementFilterData;
                    currentElement = (
                            indexData.isFiltered ? (
                                indexData.apply(new MatchFilterParameters<>(locators, MatchFinderConstants.manyGetterMap, ManyMatchesGetter.getValueOf(getter)))
                            ) : MatchFilterFunctions.getElement(locators, MatchFinderConstants.singleGetterMap, SingleMatchGetter.getValueOf(getter))
                    ).apply(region);
                    message = appendMessage(message, currentElement.message.toString());
                    message = appendMessage(message, Adjuster.adjustProbability(parameters, typeKeys, key, isValidNonFalse(currentElement), data.probabilityData).message.toString());

                }

                if (MatchNullnessValidators.isNullMatch(currentElement)) {
                    currentElement = NULL_REGION_ALL;
                }

                final var externalData = data.externalData;
                return cacheNonNullAndNotFalseLazyMatch(
                        dataElement,
                        DataFactoryFunctions.getWithMessage(new ExternalMatchData(typeKeys, currentElement), isValidNonFalse(currentElement), message.message.toString()),
                        isBlank(FrameworkCoreFormatter.getExternalSelectorDataMessage(externalData)) ? getLazyMatchByExternal(dataElement, externalData, typeKeys).apply(region) : SikuliDataConstants.NULL_EXTERNAL_MATCH
                );
            },
            RegionDataConstants.NULL_REGION_ALL
        ));
    }

    static RegionFunction<Match> getLazyMatch(LazyMatch element, InternalSelectorData internalData, ExternalSikuliSelectorData externalData, DecoratedList<String> getOrder, ProbabilityData probabilityData) {
        return getLazyMatch(new LazyMatchWithOptionsData(element, internalData, externalData, getOrder, probabilityData));
    }

    static RegionFunction<Match> getLazyMatch(LazyMatch element, InternalSelectorData internalData, ExternalSikuliSelectorData externalData, DecoratedList<String> getOrder) {
        return getLazyMatch(new LazyMatchWithOptionsData(element, internalData, externalData, getOrder, AdjusterConstants.PROBABILITY_DATA));
    }

    static RegionFunction<Match> getLazyMatch(LazyMatch element, InternalSelectorData internalData, ExternalSikuliSelectorData externalData, ProbabilityData probabilityData) {
        return getLazyMatch(new LazyMatchWithOptionsData(element, internalData, externalData, SikuliGetOrderConstants.DEFAULT, probabilityData));
    }

    static RegionFunction<Match> getLazyMatch(LazyMatch element, InternalSelectorData internalData, ExternalSikuliSelectorData externalData) {
        return getLazyMatch(new LazyMatchWithOptionsData(element, internalData, externalData, SikuliGetOrderConstants.DEFAULT, AdjusterConstants.PROBABILITY_DATA));
    }

    static RegionFunction<Match> getLazyMatch(LazyMatch element) {
        return getLazyMatch(LazyMatchWithOptionsDataFactory.getWithSpecificLazyMatch(element));
    }
}
