package com.neathorium.framework.sikuli.namespaces.region;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.namespaces.DataFunctions;
import com.neathorium.core.records.Data;
import org.sikuli.script.Region;

import java.util.function.Function;

public interface RegionFunctionFactory {
    static <T> RegionFunction<T> get(Function<Region, Data<T>> function) {
        return function::apply;
    }

    static <T> RegionFunction<T> get(Data<T> data) {
        return region -> DataFactoryFunctions.getWith(data.object, data.status, DataFunctions.getFormattedMessage(data), data.exception, data.exceptionMessage);
    }
}
