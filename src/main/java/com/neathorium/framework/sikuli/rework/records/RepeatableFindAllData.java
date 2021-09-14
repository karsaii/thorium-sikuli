package com.neathorium.framework.sikuli.rework.records;

import org.sikuli.script.Finder;
import org.sikuli.script.Image;
import org.sikuli.script.Match;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RepeatableFindAllData<T> extends RepeatableFindData<T> {

    public RepeatableFindAllData(AtomicBoolean IS_STOPPED, double FIND_TIMEOUT, T TARGET, List<Match> MATCH, Finder FINDER, Image IMAGE) {
        super(IS_STOPPED, FIND_TIMEOUT, TARGET, MATCH, FINDER, IMAGE);
    }
}
