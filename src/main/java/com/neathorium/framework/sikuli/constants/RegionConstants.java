package com.neathorium.framework.sikuli.constants;

import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.support.IScreen;

public abstract class RegionConstants {
    public static final Region ALL = Screen.all();
    public static final Match NULL_ALL = new Match(ALL, (IScreen)ALL);
}
