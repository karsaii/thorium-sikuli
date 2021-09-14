package com.neathorium.framework.sikuli.records;

import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.abstracts.AbstractExternalElementData;
import com.neathorium.framework.core.selector.records.SelectorKeySpecificityData;
import org.sikuli.script.Match;

import java.util.Map;

public class ExternalMatchData extends AbstractExternalElementData<Match> {
    public ExternalMatchData(Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, Data<Match> found) {
        super(typeKeys, found);
    }
}
