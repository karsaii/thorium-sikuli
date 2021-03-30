package com.github.karsaii.framework.sikuli.namespaces.region;

import com.github.karsaii.core.constants.CoreConstants;
import com.github.karsaii.core.constants.ExecutorConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.interfaces.functional.QuadFunction;
import com.github.karsaii.core.extensions.interfaces.functional.TriPredicate;
import com.github.karsaii.core.extensions.interfaces.functional.boilers.IGetMessage;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.executor.ExecutionParametersDataFactory;
import com.github.karsaii.core.namespaces.executor.ExecutionResultDataFactory;
import com.github.karsaii.core.namespaces.executor.ExecutionStateDataFactory;
import com.github.karsaii.core.namespaces.executor.ExecutionStepsDataFactory;
import com.github.karsaii.core.namespaces.executor.Executor;
import com.github.karsaii.core.namespaces.executor.ExecutorFunctionDataFactory;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.SimpleMessageData;
import com.github.karsaii.core.records.executor.ExecutionParametersData;
import com.github.karsaii.core.records.executor.ExecutionResultData;
import com.github.karsaii.core.records.executor.ExecutionStateData;
import com.github.karsaii.core.records.executor.ExecutionStepsData;
import com.github.karsaii.framework.sikuli.namespaces.extensions.boilers.RegionFunction;
import org.sikuli.script.Region;

import java.util.Arrays;
import java.util.function.Function;

import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.core.namespaces.BaseExecutionFunctions.ifDependency;

public interface RegionExecutor {
    private static <ReturnType, ParameterReturnType> RegionFunction<ReturnType> executeGuardCore(
        ExecutionParametersData<Function<Region, Data<?>>, RegionFunction<ParameterReturnType>> execution,
        RegionFunction<ReturnType> executionChain,
        Data<ReturnType> negative,
        int stepLength
    ) {
        return RegionFunctionFactory.get(ifDependency("executeGuardCore", CoreFormatter.getCommandAmountRangeErrorMessage(stepLength, execution.range), executionChain, negative));
    }

    @SafeVarargs
    static <ReturnType> RegionFunction<ExecutionResultData<ReturnType>> execute(
        ExecutionParametersData<Function<Region, Data<?>>, RegionFunction<ExecutionResultData<ReturnType>>> execution,
        ExecutionStateData stateData,
        Function<Region, Data<?>>... steps
    ) {
        final var negative = DataFactoryFunctions.getWith(ExecutionResultDataFactory.getWithDefaultState((ReturnType) CoreConstants.STOCK_OBJECT), false, CoreFormatterConstants.EMPTY);
        return executeGuardCore(execution, RegionFunctionFactory.get(execution.executor.apply(execution.functionData, stateData, steps)), negative, steps.length);
    }

    private static <ReturnType> Data<ReturnType> executeData(
        ExecutionStepsData<Region> stepsData,
        ExecutionParametersData<Function<Region, Data<?>>, RegionFunction<ExecutionResultData<ReturnType>>> execution
    ) {
        final var result = execute(execution, ExecutionStateDataFactory.getWithDefaults(), stepsData.steps).apply(stepsData.dependency);
        return DataFactoryFunctions.replaceObject(result, result.object.result);
    }

    private static <ReturnType> RegionFunction<ReturnType> executeData(
        ExecutionParametersData<Function<Region, Data<?>>, RegionFunction<ExecutionResultData<ReturnType>>> execution,
        RegionFunction<?>... steps
    ) {
        return RegionFunctionFactory.get(dependency -> executeData(ExecutionStepsDataFactory.getWithStepsAndDependency(steps, dependency), execution));
    }

    static <ReturnType> RegionFunction<ReturnType> execute(ExecutionParametersData<Function<Region, Data<?>>, RegionFunction<ExecutionResultData<ReturnType>>> execution, RegionFunction<?>... steps) {
        final var negative = DataFactoryFunctions.getWith((ReturnType) CoreConstants.STOCK_OBJECT, false, CoreFormatterConstants.EMPTY);
        return executeGuardCore(execution, executeData(execution, steps), negative, steps.length);
    }

    static <ReturnType> RegionFunction<ReturnType> execute(IGetMessage stepMessage, RegionFunction<?>... steps) {
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithExecuteParametersDataAndDefaultExitCondition(stepMessage, ExecutorConstants.DEFAULT_EXECUTION_DATA),
                Executor::execute
            ),
            steps
        ));
    }

    static <ReturnType> RegionFunction<ReturnType> execute(String message, RegionFunction<?>... steps) {
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithSpecificMessage(message),
                Executor::execute
            ),
            steps
        ));
    }

    static <ReturnType> RegionFunction<ReturnType> execute(QuadFunction<ExecutionStateData, String, Integer, Integer, String> messageHandler, RegionFunction<?>... steps) {
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithDefaultExitConditionAndMessageData(messageHandler),
                Executor::execute
            ),
            steps
        ));
    }

    static <ReturnType> RegionFunction<ReturnType> execute(RegionFunction<?>... steps) {
        return RegionFunctionFactory.get(Executor.execute(ExecutionParametersDataFactory.getWithDefaultFunctionDataAndDefaultRange(Executor::execute), steps));
    }

    static <ReturnType> RegionFunction<ReturnType> conditionalSequence(TriPredicate<Data<?>, Integer, Integer> guard, RegionFunction<?> before, RegionFunction<?> after) {
        final RegionFunction<?>[] steps = Arrays.asList(before, after).toArray(new RegionFunction<?>[0]);
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithSpecificMessageDataAndBreakCondition(new SimpleMessageData(CoreFormatterConstants.EXECUTION_ENDED), guard),
                Executor::execute
            ),
            steps
        ));
    }

    static <T, U, ReturnType> RegionFunction<ExecutionResultData<ReturnType>> conditionalSequence(RegionFunction<T> before, RegionFunction<U> after, Class<ReturnType> clazz) {
        final RegionFunction<?>[] steps = Arrays.asList(before, after).toArray(new RegionFunction<?>[0]);
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultFunctionDataAndTwoCommandRange(Executor::execute),
            steps
        ));
    }

    static <ReturnType> RegionFunction<ExecutionResultData<ReturnType>> execute(IGetMessage stepMessage, ExecutionStateData stateData, RegionFunction<?>... steps) {
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithExecuteParametersDataAndDefaultExitCondition(stepMessage, ExecutorConstants.DEFAULT_EXECUTION_DATA),
                Executor::execute
            ),
            localStateData,
            steps
        ));
    }

    static <ReturnType> RegionFunction<ExecutionResultData<ReturnType>> execute(String message, ExecutionStateData stateData, RegionFunction<?>... steps) {
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithSpecificMessage(message),
                Executor::execute
            ),
            localStateData,
            steps
        ));
    }

    static <ReturnType> RegionFunction<ExecutionResultData<ReturnType>> execute(QuadFunction<ExecutionStateData, String, Integer, Integer, String> messageHandler, ExecutionStateData stateData, RegionFunction<?>... steps) {
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithDefaultExitConditionAndMessageData(messageHandler),
                Executor::execute
            ),
            localStateData,
            steps
        ));
    }

    static <ReturnType> RegionFunction<ExecutionResultData<ReturnType>> execute(ExecutionStateData stateData, RegionFunction<?>... steps) {
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return RegionFunctionFactory.get(Executor.execute(ExecutionParametersDataFactory.getWithDefaultFunctionDataAndDefaultRange(Executor::execute), localStateData, steps));
    }


    static <ReturnType> RegionFunction<ExecutionResultData<ReturnType>> conditionalSequence(TriPredicate<Data<?>, Integer, Integer> guard, ExecutionStateData stateData, RegionFunction<?> before, RegionFunction<?> after) {
        final RegionFunction<?>[] steps = Arrays.asList(before, after).toArray(new RegionFunction<?>[0]);
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithSpecificMessageDataAndBreakCondition(new SimpleMessageData(CoreFormatterConstants.EXECUTION_ENDED), guard),
                Executor::execute
            ),
            localStateData,
            steps
        ));
    }

    static <T, U, ReturnType> RegionFunction<ExecutionResultData<ReturnType>> conditionalSequence(ExecutionStateData stateData, RegionFunction<T> before, RegionFunction<U> after, Class<ReturnType> clazz) {
        final RegionFunction<?>[] steps = Arrays.asList(before, after).toArray(new RegionFunction<?>[0]);
        final var localStateData = (isNotNull(stateData.indices) && !stateData.indices.isEmpty()) ? stateData : ExecutionStateDataFactory.getWith(stateData.executionMap, steps.length);
        return RegionFunctionFactory.get(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultFunctionDataAndTwoCommandRange(Executor::execute),
            localStateData,
            steps
        ));
    }

    static <ReturnType> Function<ExecutionStateData, RegionFunction<ExecutionResultData<ReturnType>>> executeState(IGetMessage stepMessage, RegionFunction<?>... steps) {
        return stateData -> execute(stepMessage, stateData, steps);
    }

    static <ReturnType> Function<ExecutionStateData, RegionFunction<ExecutionResultData<ReturnType>>> executeState(String message, RegionFunction<?>... steps) {
        return stateData -> execute(message, stateData, steps);
    }

    static <ReturnType> Function<ExecutionStateData, RegionFunction<ExecutionResultData<ReturnType>>> executeState(QuadFunction<ExecutionStateData, String, Integer, Integer, String> messageHandler, RegionFunction<?>... steps) {
        return stateData -> execute(messageHandler, stateData, steps);
    }

    static <ReturnType> Function<ExecutionStateData, RegionFunction<ExecutionResultData<ReturnType>>> executeState(RegionFunction<?>... steps) {
        return stateData -> execute(stateData, steps);
    }

    static <ReturnType> Function<ExecutionStateData, RegionFunction<ExecutionResultData<ReturnType>>> conditionalSequenceState(TriPredicate<Data<?>, Integer, Integer> guard, RegionFunction<?> before, RegionFunction<?> after) {
        return stateData -> conditionalSequence(guard, stateData, before, after);
    }

    static <T, U, ReturnType> Function<ExecutionStateData, RegionFunction<ExecutionResultData<ReturnType>>> conditionalSequenceState(RegionFunction<T> before, RegionFunction<U> after, Class<ReturnType> clazz) {
        return stateData -> conditionalSequence(stateData, before, after, clazz);
    }
}
