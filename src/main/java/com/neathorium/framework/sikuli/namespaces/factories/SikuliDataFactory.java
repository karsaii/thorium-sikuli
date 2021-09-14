package com.neathorium.framework.sikuli.namespaces.factories;

import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.records.Data;

import java.util.function.Function;

public interface SikuliDataFactory {
    static <T, V extends DecoratedList<?>> Data<DecoratedList<?>> getUnwrapped(Data<V> data) {
        return DataFactoryFunctions.getWith(data.object.get(), data.status, data.message, data.exception, data.exceptionMessage);
    }

    static <T, V extends DecoratedList<T>> Function<Data<V>, Data<DecoratedList<?>>> getUnwrapped() {
        return data -> DataFactoryFunctions.getWith(data.object.get(), data.status, data.message, data.exception, data.exceptionMessage);
    }
}
