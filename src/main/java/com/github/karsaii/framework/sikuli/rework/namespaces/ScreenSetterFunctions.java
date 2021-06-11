package com.github.karsaii.framework.sikuli.rework.namespaces;

import org.sikuli.script.SikuliXception;

import java.awt.*;

public interface ScreenSetterFunctions {
    static GraphicsDevice[] getDevices() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    }

    static int getMonitorCount() {
        if (GraphicsEnvironment.isHeadless()) {
            throw new SikuliXception(String.format("SikuliX: Init: running in headless environment"));
        }

        final var monitorCount = getDevices().length;
        if (monitorCount == 0) {
            throw new RuntimeException("StartUp: GraphicsEnvironment has no ScreenDevices");
        }

        return monitorCount;
    }
}
