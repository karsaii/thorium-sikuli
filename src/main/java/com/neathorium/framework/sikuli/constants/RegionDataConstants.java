package com.neathorium.framework.sikuli.constants;

import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.records.Data;
import org.sikuli.script.Match;

public abstract class RegionDataConstants {
    public static final Data<Match> NULL_REGION_ALL = DataFactoryFunctions.getWith(RegionConstants.NULL_ALL, false, "NULL_REGION_ALL", "The default all region for negative cases");
}
