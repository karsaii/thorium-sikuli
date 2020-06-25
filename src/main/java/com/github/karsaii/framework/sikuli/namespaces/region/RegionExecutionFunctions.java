package com.github.karsaii.framework.sikuli.namespaces.region;

import com.github.karsaii.core.namespaces.DataExecutionFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;

import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface RegionExecutionFunctions {
    static <T> RegionFunction<T> ifRegion(String nameof, String errorMessage, RegionFunction<T> positive, Data<T> negative) {
        return RegionFunctionFactory.get(DataExecutionFunctions.ifDependency(nameof, isBlank(errorMessage), positive.get(), replaceMessage(negative, nameof, errorMessage)));
    }
}
