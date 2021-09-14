package com.neathorium.framework.sikuli.namespaces.application;

import com.neathorium.framework.sikuli.namespaces.extensions.boilers.ApplicationFunction;
import com.neathorium.core.constants.CoreConstants;
import com.neathorium.core.constants.ExecutorConstants;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.interfaces.functional.QuadFunction;
import com.neathorium.core.extensions.interfaces.functional.TriPredicate;
import com.neathorium.core.extensions.interfaces.functional.boilers.IGetMessage;
import com.neathorium.core.namespaces.BaseExecutionFunctions;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.namespaces.executor.ExecutionParametersDataFactory;
import com.neathorium.core.namespaces.executor.ExecutionResultDataFactory;
import com.neathorium.core.namespaces.executor.ExecutionStateDataFactory;
import com.neathorium.core.namespaces.executor.ExecutionStepsDataFactory;
import com.neathorium.core.namespaces.executor.Executor;
import com.neathorium.core.namespaces.executor.ExecutorFunctionDataFactory;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;
import com.neathorium.core.records.SimpleMessageData;
import com.neathorium.core.records.executor.ExecutionParametersData;
import com.neathorium.core.records.executor.ExecutionResultData;
import com.neathorium.core.records.executor.ExecutionStateData;
import com.neathorium.core.records.executor.ExecutionStepsData;
import org.sikuli.script.App;

import java.util.Arrays;
import java.util.function.Function;

import static com.neathorium.core.extensions.namespaces.NullableFunctions.isNotNull;

public interface ApplicationExecutor {
    private static <ReturnType, ParameterReturnType> ApplicationFunction<ReturnType> executeGuardCore(
        ExecutionParametersData<Function<App, Data<?>>, ApplicationFunction<ParameterReturnType>> execution,
        ApplicationFunction<ReturnType> executionChain,
        Data<ReturnType> negative,
        int stepLength
    ) {
        return ApplicationFunctionFactory.get(BaseExecutionFunctions.ifDependency("executeGuardCore", CoreFormatter.getCommandAmountRangeErrorMessage(stepLength, execution.range), executionChain, negative));
    }

    @SafeVarargs
    static <ReturnType> ApplicationFunction<ExecutionResultData<ReturnType>> execute(
        ExecutionParametersData<Function<App, Data<?>>, ApplicationFunction<ExecutionResultData<ReturnType>>> execution,
        ExecutionStateData stateData,
        Function<App, Data<?>>... steps
    ) {
        final var negative = DataFactoryFunctions.getWith(ExecutionResultDataFactory.getWithDefaultState((ReturnType) CoreConstants.STOCK_OBJECT), false, CoreFormatterConstants.EMPTY);
        return executeGuardCore(execution, ApplicationFunctionFactory.get(execution.executor.apply(execution.functionData, stateData, steps)), negative, steps.length);
    }

    private static <ReturnType> Data<ReturnType> executeData(
        ExecutionStepsData<App> stepsData,
        ExecutionParametersData<Function<App, Data<?>>, ApplicationFunction<ExecutionResultData<ReturnType>>> execution
    ) {
        final var result = execute(execution, ExecutionStateDataFactory.getWithDefaults(), stepsData.steps).apply(stepsData.dependency);
        return DataFactoryFunctions.replaceObject(result, result.object.result);
    }

    private static <ReturnType> ApplicationFunction<ReturnType> executeData(
            ExecutionParametersData<Function<App, Data<?>>, ApplicationFunction<ExecutionResultData<ReturnType>>> execution,
            ApplicationFunction<?>... steps
    ) {
        return ApplicationFunctionFactory.get(dependency -> executeData(ExecutionStepsDataFactory.getWithStepsAndDependency(steps, dependency), execution));
    }

    static <ReturnType> ApplicationFunction<ReturnType> execute(ExecutionParametersData<Function<App, Data<?>>, ApplicationFunction<ExecutionResultData<ReturnType>>> execution, ApplicationFunction<?>... steps) {
        final var negative = DataFactoryFunctions.getWith((ReturnType) CoreConstants.STOCK_OBJECT, false, CoreFormatterConstants.EMPTY);
        return executeGuardCore(execution, executeData(execution, steps), negative, steps.length);
    }

    static <ReturnType> ApplicationFunction<ReturnType> execute(IGetMessage stepMessage, ApplicationFunction<?>... steps) {
        return ApplicationFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithExecuteParametersDataAndDefaultExitCondition(stepMessage, ExecutorConstants.DEFAULT_EXECUTION_DATA),
                Executor::execute
            ),
            steps
        ));
    }

    static <ReturnType> ApplicationFunction<ReturnType> execute(String message, ApplicationFunction<?>... steps) {
        return ApplicationFunctionFactory.get(Executor.execute(
                ExecutionParametersDataFactory.getWithDefaultRange(
                        ExecutorFunctionDataFactory.getWithSpecificMessage(message),
                        Executor::execute
                ),
                steps
        ));
    }

    static <ReturnType> ApplicationFunction<ReturnType> execute(QuadFunction<ExecutionStateData, String, Integer, Integer, String> messageHandler, ApplicationFunction<?>... steps) {
        return ApplicationFunctionFactory.get(Executor.execute(
                ExecutionParametersDataFactory.getWithTwoCommandsRange(
                        ExecutorFunctionDataFactory.getWithDefaultExitConditionAndMessageData(messageHandler),
                        Executor::execute
                ),
                steps
        ));
    }

    static <ReturnType> ApplicationFunction<ReturnType> execute(ApplicationFunction<?>... steps) {
        return ApplicationFunctionFactory.get(Executor.execute(ExecutionParametersDataFactory.getWithDefaultFunctionDataAndDefaultRange(Executor::execute), steps));
    }

    static <ReturnType> ApplicationFunction<ReturnType> conditionalSequence(TriPredicate<Data<?>, Integer, Integer> guard, ApplicationFunction<?> before, ApplicationFunction<?> after) {
        final ApplicationFunction<?>[] steps = Arrays.asList(before, after).toArray(new ApplicationFunction<?>[0]);
        return ApplicationFunctionFactory.get(Executor.execute(
                ExecutionParametersDataFactory.getWithTwoCommandsRange(
                        ExecutorFunctionDataFactory.getWithSpecificMessageDataAndBreakCondition(new SimpleMessageData(CoreFormatterConstants.EXECUTION_ENDED), guard),
                        Executor::execute
                ),
                steps
        ));
    }

    static <T, U, ReturnType> ApplicationFunction<ExecutionResultData<ReturnType>> conditionalSequence(ApplicationFunction<T> before, ApplicationFunction<U> after, Class<ReturnType> clazz) {
        final ApplicationFunction<?>[] steps = Arrays.asList(before, after).toArray(new ApplicationFunction<?>[0]);
        return ApplicationFunctionFactory.get(Executor.execute(
                ExecutionParametersDataFactory.getWithDefaultFunctionDataAndTwoCommandRange(Executor::execute),
                steps
        ));
    }

    static <ReturnType> ApplicationFunction<ExecutionResultData<ReturnType>> execute(IGetMessage stepMessage, ExecutionStateData stateData, ApplicationFunction<?>... steps) {
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return ApplicationFunctionFactory.get(Executor.execute(
                ExecutionParametersDataFactory.getWithDefaultRange(
                        ExecutorFunctionDataFactory.getWithExecuteParametersDataAndDefaultExitCondition(stepMessage, ExecutorConstants.DEFAULT_EXECUTION_DATA),
                        Executor::execute
                ),
                localStateData,
                steps
        ));
    }

    static <ReturnType> ApplicationFunction<ExecutionResultData<ReturnType>> execute(String message, ExecutionStateData stateData, ApplicationFunction<?>... steps) {
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return ApplicationFunctionFactory.get(Executor.execute(
                ExecutionParametersDataFactory.getWithDefaultRange(
                        ExecutorFunctionDataFactory.getWithSpecificMessage(message),
                        Executor::execute
                ),
                localStateData,
                steps
        ));
    }

    static <ReturnType> ApplicationFunction<ExecutionResultData<ReturnType>> execute(QuadFunction<ExecutionStateData, String, Integer, Integer, String> messageHandler, ExecutionStateData stateData, ApplicationFunction<?>... steps) {
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return ApplicationFunctionFactory.get(Executor.execute(
                ExecutionParametersDataFactory.getWithTwoCommandsRange(
                        ExecutorFunctionDataFactory.getWithDefaultExitConditionAndMessageData(messageHandler),
                        Executor::execute
                ),
                localStateData,
                steps
        ));
    }

    static <ReturnType> ApplicationFunction<ExecutionResultData<ReturnType>> execute(ExecutionStateData stateData, ApplicationFunction<?>... steps) {
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return ApplicationFunctionFactory.get(Executor.execute(ExecutionParametersDataFactory.getWithDefaultFunctionDataAndDefaultRange(Executor::execute), localStateData, steps));
    }


    static <ReturnType> ApplicationFunction<ExecutionResultData<ReturnType>> conditionalSequence(TriPredicate<Data<?>, Integer, Integer> guard, ExecutionStateData stateData, ApplicationFunction<?> before, ApplicationFunction<?> after) {
        final ApplicationFunction<?>[] steps = Arrays.asList(before, after).toArray(new ApplicationFunction<?>[0]);
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return ApplicationFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithSpecificMessageDataAndBreakCondition(new SimpleMessageData(CoreFormatterConstants.EXECUTION_ENDED), guard),
                Executor::execute
            ),
            localStateData,
            steps
        ));
    }

    static <T, U, ReturnType> ApplicationFunction<ExecutionResultData<ReturnType>> conditionalSequence(ExecutionStateData stateData, ApplicationFunction<T> before, ApplicationFunction<U> after, Class<ReturnType> clazz) {
        final ApplicationFunction<?>[] steps = Arrays.asList(before, after).toArray(new ApplicationFunction<?>[0]);
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return ApplicationFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultFunctionDataAndTwoCommandRange(Executor::execute),
            localStateData,
            steps
        ));
    }

    static <ReturnType> Function<ExecutionStateData, ApplicationFunction<ExecutionResultData<ReturnType>>> executeState(IGetMessage stepMessage, ApplicationFunction<?>... steps) {
        return stateData -> execute(stepMessage, stateData, steps);
    }

    static <ReturnType> Function<ExecutionStateData, ApplicationFunction<ExecutionResultData<ReturnType>>> executeState(String message, ApplicationFunction<?>... steps) {
        return stateData -> execute(message, stateData, steps);
    }

    static <ReturnType> Function<ExecutionStateData, ApplicationFunction<ExecutionResultData<ReturnType>>> executeState(QuadFunction<ExecutionStateData, String, Integer, Integer, String> messageHandler, ApplicationFunction<?>... steps) {
        return stateData -> execute(messageHandler, stateData, steps);
    }

    static <ReturnType> Function<ExecutionStateData, ApplicationFunction<ExecutionResultData<ReturnType>>> executeState(ApplicationFunction<?>... steps) {
        return stateData -> execute(stateData, steps);
    }

    static <ReturnType> Function<ExecutionStateData, ApplicationFunction<ExecutionResultData<ReturnType>>> conditionalSequenceState(TriPredicate<Data<?>, Integer, Integer> guard, ApplicationFunction<?> before, ApplicationFunction<?> after) {
        return stateData -> conditionalSequence(guard, stateData, before, after);
    }

    static <T, U, ReturnType> Function<ExecutionStateData, ApplicationFunction<ExecutionResultData<ReturnType>>> conditionalSequenceState(ApplicationFunction<T> before, ApplicationFunction<U> after, Class<ReturnType> clazz) {
        return stateData -> conditionalSequence(stateData, before, after, clazz);
    }
}
