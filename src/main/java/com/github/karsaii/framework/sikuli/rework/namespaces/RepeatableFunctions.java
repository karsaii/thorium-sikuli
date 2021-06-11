package com.github.karsaii.framework.sikuli.rework.namespaces;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.framework.sikuli.rework.records.RepeatableFindAllData;
import com.github.karsaii.framework.sikuli.rework.records.RepeatableFindData;
import org.sikuli.script.Region;

import java.util.Date;
import java.util.function.Predicate;

public interface RepeatableFunctions {
    static <T> void runRepeatableFindAll(RepeatableFindData<T> data) {
        return;
    }

    static <T> boolean isSuccessful(RepeatableFindData<T> data) {
        return false;
    }

    static <T> boolean repeat(Region region, Predicate<RepeatableFindData<T>> isSuccessfulCondition, RepeatableFindData<T> data, double timeout) {
        /*findTimeout = timeout;
        int MaxTimePerScan = (int) (1000.0 / region.getWaitScanRate());
        int timeoutMilli = (int) (timeout * 1000);
        long begin_t = (new Date()).getTime();
        do {
            if (CoreUtilities.isTrue(data.IS_STOPPED.get())) {
                break;
            }

            long before_find = (new Date()).getTime();
            run();
            if (isSuccessfulCondition.test(data)) {
                return true;
            } else if (timeoutMilli < MaxTimePerScan) {
                return false;
            }
            long after_find = (new Date()).getTime();
            if (after_find - before_find < MaxTimePerScan) {
                try {
                    Thread.sleep(MaxTimePerScan - (after_find - before_find));
                } catch (InterruptedException e) {
                    return false;
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    return false;
                }
            }
        } while (begin_t + timeout * 1000 > (new Date()).getTime());
        return false;
    }*/
        return false;
    }


}
