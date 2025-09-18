package com.github.rickmvi.control;

import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

@lombok.experimental.UtilityClass
public class Looper {

    public static void doWhile(@NotNull BooleanSupplier condition, @NotNull Runnable action) {
        do action.run(); while (condition.getAsBoolean());
    }

    public static <T> T doWhile(@NotNull Supplier<T> action, @NotNull Predicate<T> continueCondition) {
        T result;
        do {
            result = action.get();
        } while (continueCondition.test(result));
        return result;
    }
}
