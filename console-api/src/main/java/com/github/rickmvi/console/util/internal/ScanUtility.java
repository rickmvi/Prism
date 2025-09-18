package com.github.rickmvi.console.util.internal;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.github.rickmvi.console.util.convert.NumberParser;
import com.github.rickmvi.control.Condition;
import com.github.rickmvi.debug.Logger;

import java.util.Optional;
import java.util.Scanner;


public class ScanUtility implements InputScan, AutoCloseable {

    @lombok.Getter
    @lombok.Setter(value = lombok.AccessLevel.PRIVATE)
    private Optional<Scanner> scanner = Optional.empty();

    /**
     * Initializes the internal scanner using {@code System.in}.
     * This must be called before any input operation.
     */
    @Override
    public void init() {
        scanner = Optional.of(new Scanner(System.in));
    }

    /**
     * Initializes the internal scanner using a custom {@link Scanner} instance.
     *
     * @param scanner a non-null scanner to be used for input
     */
    @Override
    public void init(@NotNull Scanner scanner) {
        this.scanner = Optional.of(scanner);
    }

    /**
     * Configures the locale of the scanner, affecting number and date parsing behavior.
     *
     * @param location the desired locale setting
     */
    @Override
    public void locale(@NotNull Location location) {
        validate();
        scanner.ifPresent(sc -> {
            switch (location) {
                case US ->      sc.useLocale(java.util.Locale.US);
                case PTBR ->    sc.useLocale(java.util.Locale.of("pt", "BR"));
                case ROOT ->    sc.useLocale(java.util.Locale.ROOT);
                case DEFAULT -> sc.useLocale(java.util.Locale.getDefault());
            }
        });
    }

    /**
     * Checks if another token is available.
     *
     * @return {@code true} if another token is available; {@code false} otherwise
     */
    @Override
    public boolean hasNext() {
        validate();
        return scanner.map(Scanner::hasNext).orElse(false);
    }

    /**
     * Checks if another line of input is available.
     *
     * @return {@code true} if another line is available; {@code false} otherwise
     */
    @Override
    public boolean hasNextLine() {
        validate();
        return scanner.map(Scanner::hasNextLine).orElse(false);
    }

    /**
     * Reads the next token from the input.
     *
     * @return the next input token as a string, or an empty string if scanner is missing
     */
    @Override
    @Contract(pure = true)
    public String next() {
        validate();
        return scanner.map(Scanner::next).orElse("");
    }

    /**
     * Reads the next token that matches a regular expression pattern.
     *
     * @param pattern regex pattern to match
     * @return matched string or empty if the scanner is missing
     */
    @Override
    @Contract(pure = true)
    public String next(@NotNull String pattern) {
        validate();
        return scanner.map(sc -> sc.next(pattern)).orElse("");
    }

    /**
     * Reads the next full line from the input.
     *
     * @return the next line, or an empty string if scanner is missing
     */
    @Override
    @Contract(pure = true)
    public String nextLine() {
        validate();
        return scanner.map(Scanner::nextLine).orElse("");
    }

    /**
     * Reads the next token and parses it into an {@code int}.
     * Falls back to safe parsing via {@link NumberParser#toInt(String)}.
     *
     * @return the parsed integer value, or {@code 0} if invalid
     */
    @Override
    @Contract(pure = true)
    public int nextInt() {
        return NumberParser.toInt(nextSafe());
    }

    /**
     * Reads the next token and parses it into a {@code long}.
     *
     * @return the parsed long value, or {@code 0L} if invalid
     */
    @Override
    @Contract(pure = true)
    public long nextLong() {
        return NumberParser.toLong(nextSafe());
    }

    /**
     * Reads the next token and parses it into a {@code float}.
     *
     * @return the parsed float value, or {@code 0.0f} if invalid
     */
    @Override
    @Contract(pure = true)
    public float nextFloat() {
        return NumberParser.toFloat(nextSafe());
    }

    /**
     * Reads the next token and parses it into a {@code double}.
     *
     * @return the parsed double value, or {@code 0.0} if invalid
     */
    @Override
    @Contract(pure = true)
    public double nextDouble() {
        return NumberParser.toDouble(nextSafe());
    }

    /**
     * Reads the next token and parses it into a {@code boolean}.
     * Accepts "true", "false", "yes", "no", "1", "0", case-insensitively.
     *
     * @return the parsed boolean value
     */
    @Override
    @Contract(pure = true)
    public boolean nextBoolean() {
        return Boolean.parseBoolean(nextSafe());
    }

    /**
     * Reads the next token safely, returning an empty string in case of any exception.
     *
     * @return the next token or {@code ""} if an error occurs
     */
    @Override
    @Contract(pure = true)
    public String nextSafe() {
        try {
            return next();
        } catch (Exception e) {
            Logger.error("nextSafe() failed. Returning empty string. Cause: {}", e, e.getMessage());
            return "";
        }
    }

    /**
     * Closes the scanner and releases underlying resources.
     * Once closed, the scanner should be re-initialized before further use.
     */
    @Override
    public void close() {
        validate();
        scanner.ifPresent(Scanner::close);
    }

    /**
     * Validates that the scanner has been initialized before use.
     *
     * @throws IllegalStateException if the scanner is not present
     */
    private void validate() {
        Condition.ifTrueThrow(scanner.isEmpty(), () ->
                new IllegalStateException("Mistake: Scanner not initialized. Call InputHandler.init() first."));
    }
}
