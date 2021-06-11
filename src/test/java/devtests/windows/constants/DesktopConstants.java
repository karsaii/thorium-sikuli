package devtests.windows.constants;

import com.github.karsaii.core.constants.project.ProjectPathConstants;
import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorFactory;
import com.github.karsaii.framework.sikuli.namespaces.factories.LazyMatchFactory;
import com.github.karsaii.framework.sikuli.records.lazy.LazyMatch;

public abstract class DesktopConstants {
    public static final String NAME = "Desktop - ";
    public static final String COMPONENT_PATH = ProjectPathConstants.PROJECT_ROOT + "/src/main/resources/Windows10/";

    public static final String START_BUTTON_NAME = NAME + "Start Button";
    public static final String START_BUTTON_PATH = COMPONENT_PATH + "start-menu-button-cropped-no-values.png";

    public static final LazyMatch START_BUTTON = LazyMatchFactory.getWithFilterParameters(START_BUTTON_NAME, LazyLocatorFactory.get(START_BUTTON_PATH, "image"));
    public static final LazyMatch INTELLIJ_TEXT = LazyMatchFactory.getWithFilterParameters("IntelliJ text", LazyLocatorFactory.get("IntelliJ", "text"));

}
