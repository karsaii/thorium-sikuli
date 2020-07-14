package devtests.windows;

import constants.AssertionConstants;
import devtests.windows.constants.DesktopConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sikuli.script.Screen;

class DesktopTests {
    @DisplayName("Start button exists")
    @Test
    public void startButtonExists() {
        AssertionConstants.assertDataTrue.accept(DesktopConstants.START_BUTTON.get().apply(new Screen()));
    }

    @DisplayName("IntelliJ text exists")
    @Test
    public void intellijTextExists() {
        AssertionConstants.assertDataTrue.accept(DesktopConstants.INTELLIJ_TEXT.get().apply(new Screen()));
    }
}
