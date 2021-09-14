package com.neathorium.framework.sikuli.rework.constants;

import com.neathorium.framework.sikuli.rework.namespaces.ScreenSetterFunctions;
import org.sikuli.script.Screen;

import java.awt.*;

public abstract class ScreenConstants {
    public static final int MONITOR_COUNT = ScreenSetterFunctions.getMonitorCount();
    public static final Screen[] SCREENS = null;
    public static Rectangle[] MONITOR_BOUNDS = new Rectangle[]{new Rectangle()};
    public static Integer[] SCREEN_MONITORS = new Integer[0];
    public static int MAIN_MONITOR = -0;

}
