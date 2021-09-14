package com.neathorium.framework.sikuli.constants.steps;

import com.neathorium.framework.sikuli.environment.enums.ScreenKey;
import com.neathorium.framework.sikuli.environment.namespaces.ScreenFactory;
import com.neathorium.core.extensions.interfaces.functional.boilers.DataSupplier;
import com.neathorium.core.namespaces.executor.step.StepFactory;
import com.neathorium.core.records.Data;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class SikuliTestConstants {
    public static final BiFunction<Function<Region, Data<Boolean>>, ScreenKey, DataSupplier<Boolean>> STEP = StepFactory.step(ScreenFactory::get);
    public static final BiFunction<Function<Region, Data<Match>>, ScreenKey, DataSupplier<Match>> MATCH_STEP = StepFactory.step(ScreenFactory::get);
}
