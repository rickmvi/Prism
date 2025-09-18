package com.github.rickmvi.control;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class Condition {

    /* ========================== Executors ========================== */

    public static void ifTrue(boolean condition, Runnable action) {
        if (condition) action.run();
    }

    public static void ifFalse(boolean condition, Runnable action) {
        if (!condition) action.run();
    }

    /* ========================== Return Values ========================== */

    @Contract("false, _, _ -> param3")
    public static <T> T ifTrueReturn(boolean condition, Supplier<T> supplier, T defaultValue) {
        return condition ? supplier.get() : defaultValue;
    }

    @Contract("true, _, _ -> param3")
    public static <T> T ifFalseReturn(boolean condition, Supplier<T> supplier, T defaultValue) {
        return !condition ? supplier.get() : defaultValue;
    }

    /* ========================== Supplier-based ========================== */

    public static <R> R supplyIfTrueElse(
            boolean condition,
            @NotNull Supplier<R> whenTrue,
            @NotNull Supplier<R> orElse
    ) {
        return condition ? whenTrue.get() : orElse.get();
    }

    public static <R> R supplyIfFalseElse(
            boolean condition,
            @NotNull Supplier<R> whenFalse,
            @NotNull Supplier<R> orElse
    ) {
        return !condition ? whenFalse.get() : orElse.get();
    }

    public static <T> T supplyByCondition(
            boolean condition,
            Supplier<T> trueSupplier,
            Supplier<T> falseSupplier
    ) {
        return condition ? trueSupplier.get() : falseSupplier.get();
    }

    /* ========================== Optional-based ========================== */

    @Contract("_, _ -> !null")
    public static <R> Optional<R> optionalIfTrue(
            boolean condition,
            @NotNull Supplier<R> whenTrue
    ) {
        return condition ? Optional.ofNullable(whenTrue.get()) : Optional.empty();
    }

    @Contract("_, _ -> !null")
    public static <R> Optional<R> optionalIfFalse(
            boolean condition,
            @NotNull Supplier<R> whenFalse
    ) {
        return !condition ? Optional.ofNullable(whenFalse.get()) : Optional.empty();
    }

    /* ========================== Message Helpers ========================== */

    public static String messageOrDefault(
            Supplier<String> messageSupplier,
            String defaultMessage
    ) {
        return supplyByCondition(
                messageSupplier == null,
                () -> defaultMessage,
                messageSupplier
        );
    }

    /* ========================== Throw Helpers ========================== */

    @Contract("true, _ -> fail")
    public static void ifTrueThrow(
            boolean condition,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        if (condition) throw exceptionSupplier.get();
    }
}
