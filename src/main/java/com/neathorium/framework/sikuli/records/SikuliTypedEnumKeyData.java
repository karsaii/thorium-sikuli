package com.neathorium.framework.sikuli.records;

import com.neathorium.framework.sikuli.enums.SikuliTypeKey;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;

import java.util.Objects;

public class SikuliTypedEnumKeyData<T> {
    public final SikuliTypeKey KEY;
    public final Class<T> CLAZZ;

    public SikuliTypedEnumKeyData(SikuliTypeKey key, Class<T> clazz) {
        this.KEY = key;
        this.CLAZZ = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (SikuliTypedEnumKeyData<?>) o;
        return (
            CoreUtilities.isEqual(KEY, that.KEY) &&
            CoreUtilities.isEqual(CLAZZ, that.CLAZZ)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(KEY, CLAZZ);
    }
}
