package com.neathorium.framework.sikuli.namespaces.region;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.neathorium.core.namespaces.DataExecutionFunctions;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.records.Data;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface RegionExecutionFunctions {
    static <T> RegionFunction<T> ifRegion(String nameof, String errorMessage, RegionFunction<T> positive, Data<T> negative) {
        return RegionFunctionFactory.get(DataExecutionFunctions.ifDependency(nameof, isBlank(errorMessage), positive.get(), DataFactoryFunctions.replaceMessage(negative, nameof, errorMessage)));
    }
}
