package com.github.karsaii.framework.sikuli.rework.namespaces;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.framework.sikuli.rework.records.RepeatableFindData;
import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Finder;
import org.sikuli.script.Image;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.ScreenImage;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public interface RegionFunctions {
    static boolean isOnOtherScreen(Region region) {
        return region.isOtherScreen();
    }

    static <T> Iterator<Match> doFindAll(T target, RepeatableFindData<T> data) {
        return null;
    }


    static <T> Match doFind(Region region, T target, Image img, RepeatableFindData<T> data) {
        /*Finder finder = null;
        Match match = null;

        var findingText = false;
        ScreenImage simg;
        final var isNotNullData = NullableFunctions.isNotNull(data);
        var findTimeout = isNotNullData ? data.FIND_TIMEOUT : region.getAutoWaitTimeout();
        String someText = "";
        if (isNotNullData && NullableFunctions.isNotNull(data.FINDER)) {
            finder = data.FINDER;
            simg = region.getScreen().capture(this);
            finder._findInput.setSource(Finder.Finder2.makeMat(simg.getImage()));
            finder.setRepeating();
            if (Settings.FindProfiling) {
                Debug.logp("[FindProfiling] Region.doFind repeat: %d msec", new Date().getTime() - region.lastSearchTimeRepeat);
            }
            lastSearchTime = (new Date()).getTime();
            finder.findRepeat();
        } else {
            //screen = getScreen();
            lastFindTime = (new Date()).getTime();
            if (ptn instanceof String) {
                if (((String) ptn).startsWith("\t") && ((String) ptn).endsWith("\t")) {
                    findingText = true;
                    someText = ((String) ptn).replaceAll("\\t", "");
                } else {
                    if (img.isValid()) {
                        lastSearchTime = (new Date()).getTime();
                        finder = checkLastSeenAndCreateFinder(img, findTimeout, null);
                        if (!finder.hasNext()) {
                            runFinder(finder, img);
                        }
                    } else if (img.isText()) {
                        findingText = true;
                        someText = img.getNameGiven();
                    }
                }
                if (findingText) {
                    log(logLevel, "doFind: Switching to TextSearch");
                    finder = new Finder(this);
                    lastSearchTime = (new Date()).getTime();
                    finder.findText(someText);
                }
            } else if (ptn instanceof Pattern) {
                if (img.isValid()) {
                    lastSearchTime = (new Date()).getTime();
                    finder = checkLastSeenAndCreateFinder(img, findTimeout, (Pattern) ptn);
                    if (!finder.hasNext()) {
                        runFinder(finder, ptn);
                    }
                }
            } else if (ptn instanceof Image || ptn instanceof ScreenImage) {
                if (img.isValid()) {
                    lastSearchTime = (new Date()).getTime();
                    finder = checkLastSeenAndCreateFinder(img, findTimeout, null);
                    if (!finder.hasNext()) {
                        runFinder(finder, img);
                    }
                }
            } else {
                throw new RuntimeException(String.format("SikuliX: find, wait, exists: invalid parameter: %s", ptn));
            }
            if (repeating != null) {
                repeating._finder = finder;
                repeating._image = img;
            }
        }
        if (finder != null) {
            lastSearchTimeRepeat = lastSearchTime;
            lastSearchTime = (new Date()).getTime() - lastSearchTime;
            if (finder.hasNext()) {
                lastFindTime = (new Date()).getTime() - lastFindTime;
                match = finder.next();
                match.setTimes(lastFindTime, lastSearchTime);
                if (Settings.Highlight) {
                    match.highlight(Settings.DefaultHighlightTime);
                }
                if (Settings.FindProfiling) {
                    Debug.logp("[FindProfiling] Region.doFind final: %d msec", lastSearchTime);
                }
            }
        }
        return match;*/
        return null;
    }

}
