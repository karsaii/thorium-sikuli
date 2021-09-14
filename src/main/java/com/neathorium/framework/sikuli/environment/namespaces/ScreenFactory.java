package com.neathorium.framework.sikuli.environment.namespaces;

import com.neathorium.framework.sikuli.environment.constants.ScreenFactoryConstants;
import com.neathorium.framework.sikuli.environment.constants.ScreenKeyConstants;
import com.neathorium.framework.sikuli.environment.enums.ScreenKey;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public interface ScreenFactory {
    private static Region setScanrateToMaximum(Region region) {
        region.setWaitScanRate(ScreenFactoryConstants.CONTINUOUS_SCANNING_VALUE);
        return region;
    }

    static Region getAllScreens() {
        return setScanrateToMaximum(Screen.all());
    }

    static Region getScreen(int index) {
        return setScanrateToMaximum(new Screen(index));
    }

    static Region getFirstScreen() {
        return setScanrateToMaximum(getScreen(ScreenKeyConstants.FIRST_INDEX));
    }

    static Region get(ScreenKey key) {
        return ScreenFactoryConstants.SCREEN_BUILD_MAP.getOrDefault(key, ScreenFactory::getAllScreens).get();
    }
}
