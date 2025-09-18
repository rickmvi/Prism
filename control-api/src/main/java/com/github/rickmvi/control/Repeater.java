package com.github.rickmvi.control;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;
import java.util.function.BooleanSupplier;
import java.util.concurrent.CompletableFuture;

public class Repeater {

    public static void whileTrue(@NotNull BooleanSupplier condition, Runnable action) {
        while (condition.getAsBoolean()) action.run();
    }

    public static void whileTrueCancellable(
            @NotNull BooleanSupplier condition,
            Runnable action,
            @NotNull BooleanSupplier cancel
    ) {
        while (condition.getAsBoolean() && !cancel.getAsBoolean()) action.run();
    }

    public static void whileTrueCancellable(
            @NotNull BooleanSupplier condition,
            Runnable action,
            Supplier<Boolean> cancel
    ) {
        while (condition.getAsBoolean() && !cancel.get()) action.run();
    }

    @Contract("_, _ -> new")
    public static @NotNull CompletableFuture<Void> whileTrueAsync(BooleanSupplier condition, Runnable action) {
        return CompletableFuture.runAsync(() -> whileTrue(condition, action));
    }
}
