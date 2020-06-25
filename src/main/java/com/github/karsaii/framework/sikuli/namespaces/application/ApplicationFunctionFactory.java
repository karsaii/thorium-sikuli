package com.github.karsaii.framework.sikuli.namespaces.application;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.ApplicationFunction;
import org.sikuli.script.App;

import java.util.function.Function;

public interface ApplicationFunctionFactory {
    static <T> ApplicationFunction<T> get(Function<App, Data<T>> function) {
        return function::apply;
    }
}
