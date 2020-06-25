package com.github.karsaii.framework.sikuli.namespaces.extensions.boilers;

import com.github.karsaii.core.records.Data;
import org.sikuli.script.Region;

import java.util.function.Function;

@FunctionalInterface
public interface RegionFunction<T> extends Function<Region, Data<T>> {
    default Function<Region, Data<T>> get() {
        return this;
    }
}
