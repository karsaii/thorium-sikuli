package com.github.karsaii.framework.sikuli.rework.namespaces;

import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.support.IScreen;

import java.awt.*;

public interface ScreenFunctions {
    static IScreen getScreen(Region region) {
        return (Screen)region;
    }

    static boolean isHeadlessOrNoMonitors(Screen screen) {
        return GraphicsEnvironment.isHeadless() || Screen.isHeadless();
    }
}
