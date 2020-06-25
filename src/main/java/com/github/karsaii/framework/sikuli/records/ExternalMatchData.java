package com.github.karsaii.framework.sikuli.records;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.AbstractExternalElementData;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;
import org.sikuli.script.Match;

import java.util.Map;

public class ExternalMatchData extends AbstractExternalElementData<Match> {
    public ExternalMatchData(Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, Data<Match> found) {
        super(typeKeys, found);
    }
}
