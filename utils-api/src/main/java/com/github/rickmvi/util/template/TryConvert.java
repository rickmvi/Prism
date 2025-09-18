package com.github.rickmvi.util.template;

import org.jetbrains.annotations.Contract;
import com.github.rickmvi.debug.Logger;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class TryConvert {

    @Contract(value = " -> fail", pure = true)
    private TryConvert() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Converts a value of type {@code T} to an {@code Optional} of type {@code R} using the specified converter function.
     * If the input value is {@code null}, an empty {@code Optional} is returned.
     * If an exception occurs during conversion, an empty {@code Optional} is returned after logging a warning message.
     *
     * @param <T> the type of the input value to be converted
     * @param <R> the type of the resulting value after conversion
     * @param value the input value to be converted; may be {@code null}
     * @param converter the function used to convert the input value from type {@code T} to type {@code R}
     * @return an {@code Optional} containing the converted value, or an empty {@code Optional} if the input value is
     *         {@code null}, the conversion result is {@code null}, or an exception occurs during the conversion
     */
    @Contract("null, _ -> !null")
    public static <T, R> Optional<R> convert(T value, Function<T, R> converter) {
        if (Objects.isNull(value)) return Optional.empty();
        try {
            return Optional.ofNullable(converter.apply(value));
        } catch (Exception e) {
            Logger.error(
                    "Failed to convert value: {} to type: {} ",
                    e,
                    value,
                    converter.getClass().getSimpleName()
            );
            return Optional.empty();
        }
    }
}
