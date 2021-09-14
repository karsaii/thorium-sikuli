package com.neathorium.framework.sikuli.namespaces.extensions.boilers;

import com.neathorium.core.records.Data;
import org.sikuli.script.App;

import java.util.function.Function;

@FunctionalInterface
public interface ApplicationFunction<T> extends Function<App, Data<T>> {
    default Function<App, Data<T>> getIdentity() {
        return this;
    }
}
