package com.neathorium.framework.sikuli.namespaces.extensions.boilers;

import com.neathorium.core.extensions.DecoratedList;
import org.sikuli.script.Match;

import java.util.List;

public class MatchList extends DecoratedList<Match> {
    public MatchList(List<Match> list) {
        super(list, Match.class.getTypeName());
    }

    public MatchList subList(int fromIndex, int toIndex) {
        return subList(MatchList.class, fromIndex, toIndex);
    }

    public MatchList tail() {
        return tail(MatchList.class);
    }

    public MatchList initials() {
        return initials(MatchList.class);
    }
}