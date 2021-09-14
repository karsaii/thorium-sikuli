package com.neathorium.framework.sikuli.namespaces.extensions.boilers;

import com.neathorium.framework.sikuli.records.lazy.LazyMatchLocator;
import com.neathorium.core.extensions.DecoratedList;

import java.util.List;

public class LazyMatchLocatorList extends DecoratedList<LazyMatchLocator> {
    public LazyMatchLocatorList(List<LazyMatchLocator> list) {
        super(list, LazyMatchLocator.class.getTypeName());
    }

    public LazyMatchLocatorList subList(int fromIndex, int toIndex) {
        return new LazyMatchLocatorList(super.subList(fromIndex, toIndex));
    }

    public LazyMatchLocatorList tail() {
        return new LazyMatchLocatorList(super.tail());
    }

    public LazyMatchLocatorList initials() {
        return new LazyMatchLocatorList(super.initials());
    }
}
