package com.github.karsaii.framework.sikuli.constants;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.namespaces.factories.DecoratedListFactory;

import java.util.Arrays;

public class SikuliGetOrderConstants {
    public static final DecoratedList<String> REGULAR_ORDER = DecoratedListFactory.getWith(Arrays.asList("image", "text"), String.class);
    public static final DecoratedList<String> REVERSE_ORDER = DecoratedListFactory.getWith(Arrays.asList("text", "image"), String.class);

    public static final DecoratedList<String> DEFAULT = REGULAR_ORDER;
}
