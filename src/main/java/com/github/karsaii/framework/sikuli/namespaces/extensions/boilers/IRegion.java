package com.github.karsaii.framework.sikuli.namespaces.extensions.boilers;

import com.github.karsaii.core.extensions.interfaces.functional.boilers.IContainedData;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

@FunctionalInterface
public interface IRegion extends IContainedData<Region, Match> {
    RegionFunction<Match> get();
}
