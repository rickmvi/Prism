package com.github.rickmvi.console.util.convert;

import com.github.rickmvi.util.template.TryConvert;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class NumberParser {

    public static int toInt(@Nullable String value) {
        return toInt(value, 0);
    }

    public static int toInt(@Nullable String value, int fallback) {
        return TryConvert.convert(value, TypeAdapter::toInt).orElse(fallback);
    }

    public static int toInt(@Nullable String value, @NotNull Supplier<Integer> fallback) {
        return TryConvert.convert(value, TypeAdapter::toInt).orElseGet(fallback);
    }

    @Contract("null -> !null")
    public static Optional<Integer> toIntOptional(@Nullable String value) {
        return TryConvert.convert(value, TypeAdapter::toInt);
    }

    public static long toLong(@Nullable String value) {
        return toLong(value, 0L);
    }

    public static long toLong(@Nullable String value, long fallback) {
        return TryConvert.convert(value, TypeAdapter::toLong).orElse(fallback);
    }

    public static long toLong(@Nullable String value, @NotNull Supplier<Long> fallback) {
        return TryConvert.convert(value, TypeAdapter::toLong).orElseGet(fallback);
    }

    @Contract("null -> !null")
    public static Optional<Long> toLongOptional(@Nullable String value) {
        return TryConvert.convert(value, TypeAdapter::toLong);
    }

    public static double toDouble(@Nullable String value) {
        return toDouble(value, 0.0d);
    }

    public static double toDouble(@Nullable String value, double fallback) {
        return TryConvert.convert(value, TypeAdapter::toDouble).orElse(fallback);
    }

    public static double toDouble(@Nullable String value, @NotNull Supplier<Double> fallback) {
        return TryConvert.convert(value, TypeAdapter::toDouble).orElseGet(fallback);
    }

    @Contract("null -> !null")
    public static Optional<Double> toDoubleOptional(@Nullable String value) {
        return TryConvert.convert(value, TypeAdapter::toDouble);
    }

    public static float toFloat(@Nullable String value) {
        return toFloat(value, 0.0f);
    }

    public static float toFloat(@Nullable String value, float fallback) {
        return TryConvert.convert(value, TypeAdapter::toFloat).orElse(fallback);
    }

    public static float toFloat(@Nullable String value, @NotNull Supplier<Float> fallback) {
        return TryConvert.convert(value, TypeAdapter::toFloat).orElseGet(fallback);
    }

    @Contract("null -> !null")
    public static Optional<Float> toFloatOptional(@Nullable String value) {
        return TryConvert.convert(value, TypeAdapter::toFloat);
    }
}
