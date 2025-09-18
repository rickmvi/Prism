package com.github.rickmvi.debug;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.github.rickmvi.control.Condition;
import com.github.rickmvi.util.constants.Constants;
import com.github.rickmvi.debug.log.LogLevel;

@lombok.RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public enum AnsiColor {
    BLACK   ( Constants.BLACK   ),
    BLUE    ( Constants.BLUE    ),
    BOLD    ( Constants.BOLD    ),
    CYAN    ( Constants.CYAN    ),
    GREEN   ( Constants.GREEN   ),
    MAGENTA ( Constants.MAGENTA ),
    RED     ( Constants.RED     ),
    RESET   ( Constants.RESET   ),
    WHITE   ( Constants.WHITE   ),
    YELLOW  ( Constants.YELLOW  );

    @lombok.Getter(value = lombok.AccessLevel.PUBLIC)
    private final String ansiCode;
    private static final AnsiColor[] VALUES = values();

    /**
     * Retrieves an {@code AnsiColor} by its ordinal index.
     *
     * @param ordinal the ordinal index of the enum constant
     * @return the {@code AnsiColor} at the specified index
     * @throws IndexOutOfBoundsException if the ordinal is invalid
     */
    @Contract(pure = true)
    public static @NotNull AnsiColor valueOf(int ordinal) {
        Condition.ifTrueThrow(
                ordinal < 0 || ordinal >= VALUES.length,
                () -> new IndexOutOfBoundsException("Invalid ordinal: " + ordinal)
        );
        return VALUES[ordinal];
    }

    /**
     * Retrieves the appropriate ANSI color code based on the specified log level.
     *
     * @param level the {@link LogLevel}
     * @return the ANSI escape code associated with the level, or empty string if OFF
     */
    public static @NotNull String getColor(@NotNull LogLevel level) {
        if (level == LogLevel.OFF) return "";
        return switch (level) {
            case TRACE -> MAGENTA.getAnsiCode();
            case DEBUG -> CYAN.getAnsiCode();
            case INFO  -> GREEN.getAnsiCode();
            case WARN  -> YELLOW.getAnsiCode();
            case ERROR -> RED.getAnsiCode();
            case FATAL -> BOLD.getAnsiCode() + RED.getAnsiCode();
            default    -> RESET.getAnsiCode();
        };
    }

    /**
     * Finds the corresponding {@code AnsiColor} enum from a raw ANSI escape code.
     *
     * @param ansiCode the ANSI escape code
     * @return the corresponding {@code AnsiColor}
     * @throws IllegalArgumentException if no matching color is found
     */
    @NotNull
    public static AnsiColor of(@NotNull String ansiCode) {
        for (AnsiColor color : values()) {
            if (color.getAnsiCode().equals(ansiCode)) return color;
        }
        throw new IllegalArgumentException("Invalid ansi code: " + ansiCode);
    }
}
