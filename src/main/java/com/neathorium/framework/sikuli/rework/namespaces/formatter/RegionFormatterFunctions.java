package com.neathorium.framework.sikuli.rework.namespaces.formatter;

import com.neathorium.framework.sikuli.rework.namespaces.RegionFunctions;
import com.neathorium.framework.sikuli.rework.namespaces.ScreenFunctions;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.extensions.namespaces.predicates.BasicPredicates;
import org.sikuli.script.Region;

public interface RegionFormatterFunctions {
    static String getRegionMetaInformationFormatted(Region region) {
        var message = "";
        final var screen = ScreenFunctions.getScreen(region);
        if (NullableFunctions.isNull(screen)) {
            message = "?";
        }
        if (RegionFunctions.isOnOtherScreen(region)) {
            message = screen.getIDString();
        } else {
            final var id = screen.getID();
            message = (BasicPredicates.isNegative(id) ? "Union" : "" + id);
        }

        final var regionName = region.getName();
        final var nameText = CoreUtilities.isFalse(regionName.isEmpty()) ? "#" + regionName + "# " : CoreFormatterConstants.EMPTY;
        final var stringFormat = "%sR[%d,%d %dx%d]@S(%s)";
        return String.format(stringFormat, nameText, region.x, region.y, region.w, region.h, message);
    }
}
