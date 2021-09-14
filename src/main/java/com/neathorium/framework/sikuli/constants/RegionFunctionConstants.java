package com.neathorium.framework.sikuli.constants;

import com.neathorium.framework.sikuli.enums.SikuliTypeKey;
import com.neathorium.framework.sikuli.namespaces.extensions.boilers.MatchList;
import com.neathorium.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.neathorium.framework.sikuli.namespaces.region.RegionFunctionFactory;
import com.neathorium.framework.sikuli.records.SikuliTypedEnumKeyData;
import com.neathorium.core.constants.CoreDataConstants;
import com.neathorium.core.extensions.boilers.StringSet;
import com.neathorium.core.namespaces.StringUtilities;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.framework.core.namespaces.validators.GetterValidators;
import com.neathorium.framework.core.records.GetElementByData;
import org.sikuli.script.Match;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public abstract class RegionFunctionConstants {
    public static final RegionFunction<Void> NULL_VOID = RegionFunctionFactory.get(CoreDataConstants.NULL_VOID);
    public static final RegionFunction<Object> NULL_OBJECT = RegionFunctionFactory.get(CoreDataConstants.NULL_OBJECT);
    public static final RegionFunction<Boolean> NULL_BOOLEAN = RegionFunctionFactory.get(CoreDataConstants.NULL_BOOLEAN);
    public static final RegionFunction<String> NULL_STRING = RegionFunctionFactory.get(CoreDataConstants.NULL_STRING);
    public static final RegionFunction<Integer> NULL_INTEGER = RegionFunctionFactory.get(CoreDataConstants.NULL_INTEGER);
    public static final RegionFunction<StringSet> NULL_STRINGSET = RegionFunctionFactory.get(CoreDataConstants.NULL_STRING_SET);
    public static final RegionFunction<Boolean> LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL = RegionFunctionFactory.get(SikuliDataConstants.LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL);
    public static final RegionFunction<Boolean> LAZY_ELEMENT_WAS_NULL = RegionFunctionFactory.get(SikuliDataConstants.LAZY_ELEMENT_WAS_NULL);
    public static final RegionFunction<Match> NULL_WEBELEMENT = RegionFunctionFactory.get(SikuliDataConstants.NULL_MATCH);
    public static final RegionFunction<MatchList> NULL_MATCH_LIST = RegionFunctionFactory.get(SikuliDataConstants.NULL_MATCH_LIST);

    public static final Map<SikuliTypeKey, RegionFunction<?>> FUNCTION_MAP = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(SikuliTypeKey.BOOLEAN, NULL_BOOLEAN),
                entry(SikuliTypeKey.INTEGER, NULL_INTEGER),
                entry(SikuliTypeKey.VOID, NULL_VOID),
                entry(SikuliTypeKey.STRING, NULL_STRING),
                entry(SikuliTypeKey.STRING_SET, NULL_STRINGSET),
                entry(SikuliTypeKey.OBJECT, NULL_OBJECT)
            )
        )
    );

    public static final SikuliTypedEnumKeyData<Boolean> BOOLEAN_FUNCTION_KEY = new SikuliTypedEnumKeyData<>(SikuliTypeKey.BOOLEAN, Boolean.class);
    public static final SikuliTypedEnumKeyData<Integer> INTEGER_FUNCTION_KEY = new SikuliTypedEnumKeyData<>(SikuliTypeKey.INTEGER, Integer.class);
    public static final SikuliTypedEnumKeyData<String> STRING_FUNCTION_KEY = new SikuliTypedEnumKeyData<>(SikuliTypeKey.STRING, String.class);
    public static final SikuliTypedEnumKeyData<StringSet> STRING_SET_FUNCTION_KEY = new SikuliTypedEnumKeyData<>(SikuliTypeKey.STRING_SET, StringSet.class);
    public static final SikuliTypedEnumKeyData<Object> OBJECT_FUNCTION_KEY = new SikuliTypedEnumKeyData<>(SikuliTypeKey.OBJECT, Object.class);
    public static final SikuliTypedEnumKeyData<Void> VOID_FUNCTION_KEY = new SikuliTypedEnumKeyData<>(SikuliTypeKey.VOID, Void.class);
    public static final SikuliTypedEnumKeyData<Match> WEB_ELEMENT_FUNCTION_KEY = new SikuliTypedEnumKeyData<>(SikuliTypeKey.MATCH, Match.class);
    public static final SikuliTypedEnumKeyData<MatchList> WEB_ELEMENT_LIST_FUNCTION_KEY = new SikuliTypedEnumKeyData<>(SikuliTypeKey.MATCH_LIST, MatchList.class);

    public static final Map<Class<?>, SikuliTypeKey> TYPE_MAP = Collections.unmodifiableMap(
        new LinkedHashMap<>(
            Map.ofEntries(
                entry(String.class, SikuliTypeKey.STRING),
                entry(Boolean.class, SikuliTypeKey.BOOLEAN)
            )
        )
    );

    private static Match getByIndex(Data<MatchList> data, int index) {
        return data.object.get(index);
    }

    private static Match getByContainedText(Data<MatchList> data, String text) {
        final var list = data.object;
        final var size = list.size();
        var object = SikuliCoreConstants.STOCK_MATCH;
        var index = 0;
        for (; (index < size); ++index) {
            object = list.get(index);
            if (StringUtilities.contains(object.getText(), text)) {
                break;
            }
        }

        return object;
    }

    public static final GetElementByData<String, Match, MatchList> BY_CONTAINED_TEXT_CONSTANTS = new GetElementByData<>(
            "getElementByContainedText",
            GetterValidators::isInvalidElementByTextParameters,
            RegionFunctionConstants::getByContainedText,
            FrameworkCoreFormatter::getByFilterMessage,
            SikuliDataConstants.NULL_MATCH,
            "Text"
    );

    public static final GetElementByData<Integer, Match, MatchList> BY_INDEX_CONSTANTS = new GetElementByData<>(
            "getElementByIndex",
            GetterValidators::isInvalidElementByIndexParameters,
            RegionFunctionConstants::getByIndex,
            FrameworkCoreFormatter::getByFilterMessage,
            SikuliDataConstants.NULL_MATCH,
            "Index"
    );
}
