package com.neathorium.framework.sikuli.rework.records;

import org.sikuli.script.Finder;
import org.sikuli.script.Image;
import org.sikuli.script.Match;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class RepeatableFindData<T> {
    public final AtomicBoolean IS_STOPPED;
    public final double FIND_TIMEOUT;
    public final T TARGET;
    public final List<Match> MATCH;
    public final Finder FINDER;
    public final Image IMAGE;

    public RepeatableFindData(AtomicBoolean IS_STOPPED, double FIND_TIMEOUT, T TARGET, List<Match> MATCH, Finder FINDER, Image IMAGE) {
        this.IS_STOPPED = IS_STOPPED;
        this.FIND_TIMEOUT = FIND_TIMEOUT;
        this.TARGET = TARGET;
        this.MATCH = MATCH;
        this.FINDER = FINDER;
        this.IMAGE = IMAGE;
    }
}
