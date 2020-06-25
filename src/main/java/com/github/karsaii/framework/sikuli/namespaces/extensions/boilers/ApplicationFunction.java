package com.github.karsaii.framework.sikuli.namespaces.extensions.boilers;

import com.github.karsaii.core.records.Data;
import org.sikuli.script.App;

import java.util.function.Function;

@FunctionalInterface
public interface ApplicationFunction<T> extends Function<App, Data<T>> {
    default Function<App, Data<T>> getIdentity() {
        return this;
    }
}
