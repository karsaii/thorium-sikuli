package com.github.karsaii.framework.sikuli.records;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.command.CommandRangeData;
import com.github.karsaii.framework.core.records.lazy.ExternalSelectorData;
import org.sikuli.script.Region;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExternalSikuliSelectorData extends ExternalSelectorData<Region> {
    public ExternalSikuliSelectorData(BiFunction<String, List<String>, Function<Region, Data<String>>> getSelector, String preferredProperties, String selectorType, CommandRangeData range, int limit, Data<String> defaultSelector) {
        super(getSelector, preferredProperties, selectorType, range, limit, defaultSelector);
    }
}
