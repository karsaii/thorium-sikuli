package com.github.karsaii.framework.sikuli.records;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.records.lazy.GetWithBaseData;
import org.sikuli.script.Region;

import java.util.function.Function;

public class GetWithSikuliData<T, U, V, W> extends GetWithBaseData<Region, T, U, V, W> {
    public GetWithSikuliData(T locators, Function<T, U> locatorGetter, Function<V, Function<Region, Data<W>>> getter, Data<W> guardData) {
        super(locators, locatorGetter, getter, guardData);
    }
}
