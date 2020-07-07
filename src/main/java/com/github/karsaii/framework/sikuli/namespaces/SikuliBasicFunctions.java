package com.github.karsaii.framework.sikuli.namespaces;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicates;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.predicates.DataPredicates;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.FrameworkFunctions;
import com.github.karsaii.framework.sikuli.constants.GetElementsConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliCoreConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliDataConstants;
import com.github.karsaii.framework.sikuli.constants.SikuliFormatterConstants;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.github.karsaii.framework.sikuli.namespaces.factories.MatchListFactory;
import com.github.karsaii.framework.sikuli.namespaces.factories.SikuliDataFactory;
import com.github.karsaii.framework.sikuli.namespaces.match.MatchStrategyFunctions;
import com.github.karsaii.framework.sikuli.namespaces.predicates.FindFailedPredicates;
import com.github.karsaii.framework.sikuli.namespaces.region.RegionExecutor;
import com.github.karsaii.framework.sikuli.namespaces.region.RegionFunctionFactory;
import com.github.karsaii.framework.sikuli.namespaces.validators.GetElementsValidators;
import com.github.karsaii.framework.sikuli.namespaces.validators.MatchLocatorValidators;
import com.github.karsaii.framework.sikuli.namespaces.validators.MatchValidators;
import com.github.karsaii.framework.sikuli.namespaces.validators.PasteValidators;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.util.Iterator;
import java.util.function.Function;

import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.validChain;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceMessage;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SikuliBasicFunctions {
    private static <T> Iterator<Match> findAllNormalizedExceptions(Region region, T target) throws FindFailed {
        var exception = SikuliCoreConstants.INVALID_FIND_FAILED_EXCEPTION;
        var matches = SikuliCoreConstants.NULL_MATCH_ITERATOR;
        try {
            matches = region.findAll(target);
        } catch (RuntimeException ex) {
            if (!FindFailedPredicates.isFindFailed(ex)) {
                throw ex;
            }

            exception = SikuliUtilities.toFindFailed(ex);
        } catch (FindFailed ex) {
            exception = ex;
        }

        if (FindFailedPredicates.isValidFindFailedException(exception)) {
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
            SikuliCoreConstants.MATCH_LIST_VALIDATOR,
            getter.andThen(SikuliDataFactory::getUnwrapped),
            SikuliCoreConstants.MATCH_COUNT,
            SikuliDataConstants.NULL_INTEGER_NULL_REGION
        ));
    }

    private static <T> Data<MatchList> getElementsByCore(Region region, T locator) {
        var exception = SikuliCoreConstants.INVALID_FIND_FAILED_EXCEPTION;
        var matches = SikuliCoreConstants.NULL_MATCH_ITERATOR;
        try {
            matches = findAllNormalizedExceptions(region, locator);
        } catch (FindFailed ex) {
            exception = ex;
        }

        final var list = SikuliUtilities.iteratorToList(matches);
        final var status = !FindFailedPredicates.isValidFindFailedException(exception);
        final var object = status ? MatchListFactory.getWith(list) : SikuliCoreConstants.INVALID_MATCHLIST;
        return DataFactoryFunctions.getWithNameAndMessage(object, status, "getElements", SikuliFormatters.getFindAllMessage(list.size(), status), exception);
    }

    private static Data<MatchList> getElementsCore(Region region, Pattern pattern) {
        return getElementsByCore(region, pattern);
    }

    private static Data<MatchList> getElementsByTextCore(Region region, String text) {
        return getElementsByCore(region, text);
    }

    private static Data<Boolean> clickCore(Data<Match> data) {
        final var returnCode = data.object.click();
        final var status = BasicPredicates.isNonZero(returnCode);
        final var message = "Click was " + (status ? "" : "not ") + "successful" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "clickCore", message);
    }

    private static Data<Boolean> pasteCore(Data<Match> data, String text) {
        final var returnCode = data.object.paste(text);
        final var status = BasicPredicates.isNonZero(returnCode);
        final var message = "Paste of (\""  + text + "\") was " + (status ? "" : "not ") + "successful" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "pasteCore", message);
    }

    private static Data<Boolean> pasteCore(Region region, String text) {
        final var returnCode = region.paste(text);
        final var status = BasicPredicates.isNonZero(returnCode);
        final var message = "Paste of (\""  + text + "\") was " + (status ? "" : "not ") + "successful" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "pasteCore", message);
    }

    private static Data<Boolean> typeCore(Data<Match> data, String text, int modifiers, String modifiersDescription) {
        final var returnCode = data.object.type(text, modifiers);
        final var status = BasicPredicates.isNonZero(returnCode);
        final var message = "Typing of (\""  + text + "\") with modifiers(\"" + modifiersDescription + "\") was " + (status ? "" : "not ") + "successful" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "typeCore", message);
    }

    private static Data<Boolean> typeCore(Data<Match> data, String text, int modifiers) {
        return typeCore(data, text, modifiers, "Some modifiers");
    }

    private static Data<Boolean> typeCore(Data<Match> data, String text) {
        final var returnCode = data.object.type(text);
        final var status = BasicPredicates.isNonZero(returnCode);
        final var message = "Typing of (\""  + text + "\") was " + (status ? "" : "not ") + "successful" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "typeCore", message);
    }



    private static Data<Match> getElementCore(Region region, Pattern pattern, int index) {
        final var nameof = "getElement";
        final var matches = getElementsCore(region, pattern);
        final var errorMessage = CoreFormatter.getValidNonFalseAndValidContainedMessage(matches, CoreFormatter::isNullOrEmptyListMessage);
        if (isNotBlank(errorMessage)) {
            return replaceMessage(SikuliDataConstants.NULL_MATCH, nameof, errorMessage);
        }

        final var list = matches.object;
        final var object = list.get(index);
        final var status = MatchValidators.isNotNull(object);
        final var message = "Element was " + (status ? "" : "not ") + "found" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getWithNameAndMessage(object, status, nameof, message);
    }

    private static Data<Match> getElementCore(Region region, Pattern pattern) {
        return getElementCore(region, pattern, 0);
    }

    private static Data<MatchList> getElementsCore(Region region, String target, double percentage) {
        return getElementsCore(region, new Pattern(target).similar(percentage));
    }

    private static Data<Match> getElementCore(Region region, String target, double percentage, int index) {
        return getElementCore(region, new Pattern(target).similar(percentage), index);
    }

    private static Data<Match> getElementCore(Region region, String target, double percentage) {
        return getElementCore(region, new Pattern(target).similar(percentage), 0);
    }

    private static Data<MatchList> getElementsCore(Region region, String target) {
        return getElementsCore(region, target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    private static Data<Match> getElementCore(Region region, String target, int index) {
        return getElementCore(region, target, GetElementsConstants.BASE_MATCH_PERCENT, index);
    }

    private static Data<Match> getElementCore(Region region, String target) {
        return getElementCore(region, target, GetElementsConstants.BASE_MATCH_PERCENT, 0);
    }

    private static Data<Match> getElementByTextCore(Region region, String text) {
        final var locator = MatchStrategyFunctions.getTextLocator(text);
        return isNotBlank(locator) ? getElementCore(region, locator) : replaceMessage(SikuliDataConstants.NULL_MATCH, "getElementByText", MatchLocatorValidators.isImageLocator(text));
    }

    private static Data<Boolean> isDisplayedCore(Data<Match> data) {
        final var status = DataPredicates.isValidNonFalse(data);
        final var message = "Element was " + (status ? "": "not ") + "displayed" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isDisplayed", message);
    }

    private static Data<Boolean> areDisplayedCore(Data<MatchList> data) {
        final var status = DataPredicates.isValidNonFalse(data);
        final var message = "Element was " + (status ? "": "not ") + "displayed" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isDisplayed", message);
    }

    private static Data<Boolean> isAbsentCore(Data<Match> data) {
        final var status = DataPredicates.isInvalidOrFalse(data);
        final var message = "Element was " + (status ? "not ": "") + "absent" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isDisplayed", message);
    }

    private static Data<Boolean> areAbsentCore(Data<MatchList> data) {
        final var status = DataPredicates.isInvalidOrFalse(data);
        final var message = "Element was " + (status ? "not ": "") + "absent" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isDisplayed", message);
    }

    private static Function<Region, Data<MatchList>> getElementsCore(String target, double percentage) {
        return region -> getElementsCore(region, target, percentage);
    }

    private static Function<Region, Data<Match>> getElementCore(String target, double percentage, int index) {
        return region -> getElementCore(region, target, percentage, index);
    }

    private static Function<Region, Data<Match>> getElementCore(String target, double percentage) {
        return region -> getElementCore(region, target, percentage);
    }

    private static Function<Region, Data<MatchList>> getElementsCore(String target) {
        return region -> getElementsCore(region, target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    private static Function<Region, Data<Match>> getElementCore(String target, int index) {
        return region -> getElementCore(region, target, GetElementsConstants.BASE_MATCH_PERCENT, index);
    }

    private static Function<Region, Data<Match>> getElementCore(String target) {
        return region -> getElementCore(region, target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    private static Function<Region, Data<Match>> getElementByTextCore(String target) {
        return region -> getElementByTextCore(region, target);
    }

    private static Function<Region, Data<MatchList>> getElementsByTextCore(String target) {
        return region -> getElementsByCore(region, target);
    }

    private static Function<Data<Match>, Data<Boolean>> pasteCore(String text) {
        return data -> pasteCore(data, text);
    }

    private static Function<Region, Data<Boolean>> pasteCoreR(String text) {
        return data -> pasteCore(data, text);
    }

    private static Function<Data<Match>, Data<Boolean>> typeCore(String text, int modifiers, String modifierDescription) {
        return data -> typeCore(data, text, modifiers, modifierDescription);
    }

    private static Function<Data<Match>, Data<Boolean>> typeCore(String text, int modifiers) {
        return data -> typeCore(data, text, modifiers);
    }

    private static Function<Data<Match>, Data<Boolean>> typeCore(String text) {
        return data -> typeCore(data, text);
    }

    static RegionFunction<MatchList> getElements(String target, double percentage) {
        return RegionFunctionFactory.get(ifDependency("getElements", GetElementsValidators.getValidLocatorMessage(target), getElementsCore(target, percentage), SikuliDataConstants.NULL_LIST));
    }

    static RegionFunction<Match> getElement(String target, double percentage, int index) {
        return RegionFunctionFactory.get(ifDependency("getElement", GetElementsValidators.getElementParametersValidMessage(target, index), getElementCore(target, percentage, index), SikuliDataConstants.NULL_MATCH));
    }

    static RegionFunction<Match> getElement(String target, double percentage) {
        return RegionFunctionFactory.get(ifDependency("getElement", GetElementsValidators.getValidLocatorMessage(target), getElementCore(target, percentage), SikuliDataConstants.NULL_MATCH));
    }

    static RegionFunction<Match> getElementByText(String target) {
        return RegionFunctionFactory.get(ifDependency("getElementByText", GetElementsValidators.getValidTextLocatorMessage(target), getElementByTextCore(target), SikuliDataConstants.NULL_MATCH));
    }

    static RegionFunction<MatchList> getElementsByText(String target) {
        return RegionFunctionFactory.get(ifDependency("getElementsByText", GetElementsValidators.getValidTextLocatorMessage(target), getElementsByTextCore(target), SikuliDataConstants.NULL_LIST));
    }

    static RegionFunction<Boolean> isCondition(String name, String status, Function<Region, Data<Boolean>> function) {
        final var nameof = isNotBlank(name) ? name : "isCondition";
        return RegionFunctionFactory.get(ifDependency(nameof, status + CoreFormatter.isNullMessageWithName(function, "function"), function, CoreDataConstants.NULL_BOOLEAN));
    }

    static RegionFunction<Boolean> isDisplayed(String target, double percentage) {
        return isCondition(
            "isDisplayed",
            GetElementsValidators.getValidLocatorMessage(target),
            validChain(getElementCore(target, percentage), SikuliBasicFunctions::isDisplayedCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> isDisplayed(String target, double percentage, int index) {
        return isCondition(
            "isDisplayed",
            GetElementsValidators.getElementParametersValidMessage(target, index),
            validChain(getElementCore(target, percentage, index), SikuliBasicFunctions::isDisplayedCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> areDisplayed(String target, double percentage) {
        return isCondition(
            "areDisplayed",
            GetElementsValidators.getValidLocatorMessage(target),
            validChain(getElementsCore(target, percentage), SikuliBasicFunctions::areDisplayedCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> isTextDisplayed(String text) {
        return isCondition(
            "isTextDisplayed",
            GetElementsValidators.getValidTextLocatorMessage(text),
            validChain(getElementByText(text), SikuliBasicFunctions::isDisplayedCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> areTextDisplayed(String text) {
        return isCondition(
            "isTextDisplayed",
            GetElementsValidators.getValidTextLocatorMessage(text),
            validChain(getElementsByText(text), SikuliBasicFunctions::areDisplayedCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> isAbsent(String target, double percentage) {
        return isCondition(
            "isAbsent",
            GetElementsValidators.getValidLocatorMessage(target),
            validChain(getElementCore(target, percentage), SikuliBasicFunctions::isAbsentCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> isAbsent(String target, double percentage, int index) {
        return isCondition(
            "isAbsent",
            GetElementsValidators.getElementParametersValidMessage(target, index),
            validChain(getElementCore(target, percentage, index), SikuliBasicFunctions::isAbsentCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> areAbsent(String target, double percentage) {
        return isCondition(
            "areAbsent",
            GetElementsValidators.getValidLocatorMessage(target),
            validChain(getElementsCore(target, percentage), SikuliBasicFunctions::areAbsentCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> isTextAbsent(String text) {
        return isCondition(
            "isTextAbsent",
            GetElementsValidators.getValidTextLocatorMessage(text),
            validChain(getElementByText(text), SikuliBasicFunctions::isAbsentCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> areTextAbsent(String text) {
        return isCondition(
            "areTextAbsent",
            GetElementsValidators.getValidTextLocatorMessage(text),
            validChain(getElementsByText(text), SikuliBasicFunctions::areAbsentCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> click(String target, double percentage) {
        return isCondition(
            "click",
            GetElementsValidators.getValidLocatorMessage(target),
            validChain(getElementCore(target, percentage), SikuliBasicFunctions::clickCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> click(String target, double percentage, int index) {
        return isCondition(
            "click",
            GetElementsValidators.getElementParametersValidMessage(target, index),
            validChain(getElementCore(target, percentage, index), SikuliBasicFunctions::clickCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> clickByText(String text) {
        return isCondition(
            "clickByText",
            GetElementsValidators.getValidTextLocatorMessage(text),
            validChain(getElementByText(text), SikuliBasicFunctions::clickCore, CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> paste(String target, double percentage, String input) {
        return isCondition(
            "paste",
            PasteValidators.getPasteValidParametersMessage(target, input),
            validChain(getElementCore(target, percentage), SikuliBasicFunctions.pasteCore(input), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> paste(String target, double percentage, int index, String input) {
        return isCondition(
            "paste",
            PasteValidators.getPasteValidParametersMessage(target, index, input),
            validChain(getElementCore(target, percentage, index), SikuliBasicFunctions.pasteCore(input), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> pasteByText(String text, String input) {
        return isCondition(
            "pasteByText",
            PasteValidators.getPasteValidParametersMessageText(text, input),
            validChain(getElementByText(text), SikuliBasicFunctions.pasteCore(input), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> type(String target, double percentage, String input) {
        return isCondition(
            "type",
            PasteValidators.getPasteValidParametersMessage(target, input),
            validChain(getElementCore(target, percentage), SikuliBasicFunctions.typeCore(input), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> type(String target, double percentage, int index, String input) {
        return isCondition(
            "type",
            PasteValidators.getPasteValidParametersMessage(target, index, input),
            validChain(getElementCore(target, percentage, index), SikuliBasicFunctions.typeCore(input), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> typeByText(String text, String input) {
        return isCondition(
            "typeByText",
            PasteValidators.getPasteValidParametersMessageText(text, input),
            validChain(getElementByText(text), SikuliBasicFunctions.typeCore(input), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> type(String target, double percentage, String input, int modifiers) {
        return isCondition(
            "type",
            PasteValidators.getPasteValidParametersMessage(target, input),
            validChain(getElementCore(target, percentage), SikuliBasicFunctions.typeCore(input, modifiers), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> type(String target, double percentage, int index, String input, int modifiers) {
        return isCondition(
            "type",
            PasteValidators.getPasteValidParametersMessage(target, index, input),
            validChain(getElementCore(target, percentage, index), SikuliBasicFunctions.typeCore(input, modifiers), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> typeByText(String text, String input, int modifiers) {
        return isCondition(
            "typeByText",
            PasteValidators.getPasteValidParametersMessageText(text, input),
            validChain(getElementByText(text), SikuliBasicFunctions.typeCore(input, modifiers), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> type(String target, double percentage, String input, int modifiers, String modifiersDescription) {
        return isCondition(
            "type",
            PasteValidators.getPasteValidParametersMessage(target, input),
            validChain(getElementCore(target, percentage), SikuliBasicFunctions.typeCore(input, modifiers, modifiersDescription), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> type(String target, double percentage, int index, String input, int modifiers, String modifiersDescription) {
        return isCondition(
            "type",
            PasteValidators.getPasteValidParametersMessage(target, index, input),
            validChain(getElementCore(target, percentage, index), SikuliBasicFunctions.typeCore(input, modifiers, modifiersDescription), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<Boolean> typeByText(String text, String input, int modifiers, String modifiersDescription) {
        return isCondition(
            "typeByText",
            PasteValidators.getPasteValidParametersMessageText(text, input),
            validChain(getElementByText(text), SikuliBasicFunctions.typeCore(input, modifiers, modifiersDescription), CoreDataConstants.NULL_BOOLEAN)
        );
    }

    static RegionFunction<MatchList> getElements(String target) {
        return getElements(target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    static RegionFunction<Match> getElement(String target, int index) {
        return getElement(target, GetElementsConstants.BASE_MATCH_PERCENT, index);
    }

    static RegionFunction<Match> getElement(String target) {
        return getElement(target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isDisplayed(String target) {
        return isDisplayed(target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isDisplayed(String target, int index) {
        return isDisplayed(target, GetElementsConstants.BASE_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> areDisplayed(String target) {
        return areDisplayed(target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isAbsent(String target) {
        return isAbsent(target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isAbsent(String target, int index) {
        return isAbsent(target, GetElementsConstants.BASE_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> areAbsent(String target) {
        return areAbsent(target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> click(String target) {
        return isDisplayed(target, GetElementsConstants.BASE_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> click(String target, int index) {
        return isDisplayed(target, GetElementsConstants.BASE_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> paste(String target, String input) {
        return paste(target, GetElementsConstants.BASE_MATCH_PERCENT, input);
    }

    static RegionFunction<Boolean> paste(String target, int index, String input) {
        return paste(target, GetElementsConstants.BASE_MATCH_PERCENT, index, input);
    }

    static RegionFunction<Boolean> type(String target, String input) {
        return type(target, GetElementsConstants.BASE_MATCH_PERCENT, input);
    }

    static RegionFunction<Boolean> type(String target, int index, String input) {
        return type(target, GetElementsConstants.BASE_MATCH_PERCENT, index, input);
    }

    static RegionFunction<Boolean> type(String target, String input, int modifiers) {
        return type(target, GetElementsConstants.BASE_MATCH_PERCENT, input, modifiers);
    }

    static RegionFunction<Boolean> type(String target, int index, String input, int modifiers) {
        return type(target, GetElementsConstants.BASE_MATCH_PERCENT, index, input, modifiers);
    }

    static RegionFunction<Boolean> type(String target, String input, int modifiers, String modifiersDescription) {
        return type(target, GetElementsConstants.BASE_MATCH_PERCENT, input, modifiers, modifiersDescription);
    }

    static RegionFunction<Boolean> type(String target, int index, String input, int modifiers, String modifiersDescription) {
        return type(target, GetElementsConstants.BASE_MATCH_PERCENT, index, input, modifiers, modifiersDescription);
    }

    static RegionFunction<MatchList> getElementsNearExact(String target) {
        return getElements(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Match> getElementNearExact(String target, int index) {
        return getElement(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index);
    }

    static RegionFunction<Match> getElementNearExact(String target) {
        return getElement(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isDisplayedNearExact(String target) {
        return isDisplayed(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isDisplayedNearExact(String target, int index) {
        return isDisplayed(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> areDisplayedNearExact(String target) {
        return areDisplayed(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isAbsentNearExact(String target) {
        return isAbsent(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isAbsentNearExact(String target, int index) {
        return isAbsent(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> areAbsentNearExact(String target) {
        return areAbsent(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> clickNearExact(String target) {
        return isDisplayed(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> clickNearExact(String target, int index) {
        return isDisplayed(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> pasteNearExact(String target, String input) {
        return paste(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, input);
    }

    static RegionFunction<Boolean> pasteNearExact(String target, int index, String input) {
        return paste(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index, input);
    }

    static RegionFunction<Boolean> typeNearExact(String target, String input) {
        return type(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, input);
    }

    static RegionFunction<Boolean> typeNearExact(String target, int index, String input) {
        return type(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index, input);
    }

    static RegionFunction<Boolean> typeNearExact(String target, String input, int modifiers) {
        return type(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, input, modifiers);
    }

    static RegionFunction<Boolean> typeNearExact(String target, int index, String input, int modifiers) {
        return type(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index, input, modifiers);
    }

    static RegionFunction<Boolean> typeNearExact(String target, String input, int modifiers, String modifiersDescription) {
        return type(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, input, modifiers, modifiersDescription);
    }

    static RegionFunction<Boolean> typeNearExact(String target, int index, String input, int modifiers, String modifiersDescription) {
        return type(target, GetElementsConstants.NEAR_EXACT_MATCH_PERCENT, index, input, modifiers, modifiersDescription);
    }

    static RegionFunction<MatchList> getElementsExact(String target) {
        return getElements(target, GetElementsConstants.EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Match> getElementExact(String target, int index) {
        return getElement(target, GetElementsConstants.EXACT_MATCH_PERCENT, index);
    }

    static RegionFunction<Match> getElementExact(String target) {
        return getElement(target, GetElementsConstants.EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isDisplayedExact(String target) {
        return isDisplayed(target, GetElementsConstants.EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isDisplayedExact(String target, int index) {
        return isDisplayed(target, GetElementsConstants.EXACT_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> areDisplayedExact(String target) {
        return areDisplayed(target, GetElementsConstants.EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isAbsentExact(String target) {
        return isAbsent(target, GetElementsConstants.EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> isAbsentExact(String target, int index) {
        return isAbsent(target, GetElementsConstants.EXACT_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> areAbsentExact(String target) {
        return areAbsent(target, GetElementsConstants.EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> clickExact(String target) {
        return isDisplayed(target, GetElementsConstants.EXACT_MATCH_PERCENT);
    }

    static RegionFunction<Boolean> clickExact(String target, int index) {
        return isDisplayed(target, GetElementsConstants.EXACT_MATCH_PERCENT, index);
    }

    static RegionFunction<Boolean> pasteExact(String target, String input) {
        return paste(target, GetElementsConstants.EXACT_MATCH_PERCENT, input);
    }

    static RegionFunction<Boolean> pasteExact(String target, int index, String input) {
        return paste(target, GetElementsConstants.EXACT_MATCH_PERCENT, index, input);
    }

    static RegionFunction<Boolean> typeExact(String target, String input) {
        return type(target, GetElementsConstants.EXACT_MATCH_PERCENT, input);
    }

    static RegionFunction<Boolean> typeExact(String target, int index, String input) {
        return type(target, GetElementsConstants.EXACT_MATCH_PERCENT, index, input);
    }

    static RegionFunction<Boolean> typeExact(String target, String input, int modifiers) {
        return type(target, GetElementsConstants.EXACT_MATCH_PERCENT, input, modifiers);
    }

    static RegionFunction<Boolean> typeExact(String target, int index, String input, int modifiers) {
        return type(target, GetElementsConstants.EXACT_MATCH_PERCENT, index, input, modifiers);
    }

    static RegionFunction<Boolean> typeExact(String target, String input, int modifiers, String modifiersDescription) {
        return type(target, GetElementsConstants.EXACT_MATCH_PERCENT, input, modifiers, modifiersDescription);
    }

    static RegionFunction<Boolean> typeExact(String target, int index, String input, int modifiers, String modifiersDescription) {
        return type(target, GetElementsConstants.EXACT_MATCH_PERCENT, index, input, modifiers, modifiersDescription);
    }

    static RegionFunction<Boolean> pasteCoreRF(String input) {
        return RegionFunctionFactory.get(pasteCoreR(input));
    }

    static RegionFunction<Boolean> sendKeys(String target, String input) {
        return RegionExecutor.execute("sendKeys", click(target), pasteCoreRF(input));
    }
}
