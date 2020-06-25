package com.github.karsaii.framework.sikuli.namespaces.factories;

import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.MatchList;
import org.sikuli.script.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface MatchListFactory {
    static MatchList getWith(List<Match> list) {
        return new MatchList(list);
    }

    static MatchList getWithEmptyList() {
        return getWith(new ArrayList<>());
    }

    static MatchList getWithSingleItem(Match element) {
        return new MatchList(Collections.singletonList(element));
    }
}
