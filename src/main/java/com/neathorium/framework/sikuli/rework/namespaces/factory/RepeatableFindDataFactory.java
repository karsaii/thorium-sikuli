package com.neathorium.framework.sikuli.rework.namespaces.factory;

import com.neathorium.framework.sikuli.rework.records.RepeatableFindData;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import org.sikuli.script.Element;
import org.sikuli.script.Finder;
import org.sikuli.script.Image;
import org.sikuli.script.Match;
import org.sikuli.script.ScreenImage;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public interface RepeatableFindDataFactory {
    static <T> RepeatableFindData<T> getWith(T target, List<Match> match, Finder finder, Image image) {
        var localImage = image;
        if (NullableFunctions.isNull(image)) {
            localImage = Element.getImageFromTarget(target);
        } else {
            if (target instanceof ScreenImage) {
                localImage = new Image(((ScreenImage) target).getImage());
            } else {
                localImage = image;
            }
        }
        return new RepeatableFindData<T>(new AtomicBoolean(), 0.0, target, match, finder, localImage);
    }

    static <PSI> RepeatableFindData<PSI> getWith(PSI target, Image image) {
        return getWith(target, null, null, image);
    }
}
