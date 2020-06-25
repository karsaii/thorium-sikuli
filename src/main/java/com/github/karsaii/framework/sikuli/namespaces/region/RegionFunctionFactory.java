package com.github.karsaii.framework.sikuli.namespaces.region;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import org.sikuli.script.Region;

import java.util.function.Function;

public interface RegionFunctionFactory {
    static <T> RegionFunction<T> get(Function<Region, Data<T>> function) {
        return function::apply;
    }
}
