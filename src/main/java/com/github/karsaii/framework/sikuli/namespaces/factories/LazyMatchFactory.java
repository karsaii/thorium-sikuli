package com.github.karsaii.framework.sikuli.namespaces.factories;

import com.github.karsaii.framework.core.namespaces.validators.Invalids;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatch;
import com.github.karsaii.framework.sikuli.records.lazy.filtered.LazyFilteredMatchParameters;

import java.util.HashMap;
import java.util.Map;

public interface LazyMatchFactory {
    static <T> LazyMatch getWithDefaultValidator(String name, Map<String, LazyFilteredMatchParameters> parameters) {
        return new LazyMatch(name, parameters, Invalids::defaultFalseValidator);
    }
    
    static <T> LazyMatch getWithDefaultLocatorsAndValidator(String name) {
        return new LazyMatch(name, new HashMap<>(), Invalids::defaultFalseValidator);
    }

    //TODO: Rest of the functions!
}
