package devtests.windows;

import devtests.windows.constants.DesktopConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sikuli.script.Screen;

public class DesktopTests {
    @DisplayName("Start button exists")
    @Test
    public void startButtonExists() {
        final var result = DesktopConstants.START_BUTTON.get().get().apply(new Screen());
        Assertions.assertTrue(result.status, result.message.toString());
    }

    @DisplayName("IntelliJ text exists")
    @Test
    public void intellijTextExists() throws InterruptedException {
        final var screen = new Screen();
        //screen.wait(30f);
        final var result = DesktopConstants.INTELLIJ_TEXT.get().get().apply(screen);
        Assertions.assertTrue(result.status, result.message.toString());
    }
}
