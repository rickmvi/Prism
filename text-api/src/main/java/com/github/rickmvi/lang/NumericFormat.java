package com.github.rickmvi.lang;

import org.jetbrains.annotations.Contract;
import com.github.rickmvi.util.constants.Constants;

import java.text.DecimalFormat;

public enum NumericFormat {
    /** Format with comma as decimal separator (e.g., 1.234,56) */
    DECIMAL_COMMA (Constants.DECIMAL_COMMA),

    /** Format with dot as decimal separator (e.g., 1234.56) */
    DECIMAL_POINT (Constants.DECIMAL_POINT),

    /** Integer format with a thousand separator (e.g., 1.234) */
    INTEGER       (Constants.INTEGER),

    /** Percentage format (e.g., 12.50%) */
    PERCENT       (Constants.PERCENT),

    /** Scientific notation format (e.g., 1.23E3) */
    SCIENTIFIC    (Constants.SCIENTIFIC);

    @lombok.Getter(value = lombok.AccessLevel.PUBLIC)
    private final DecimalFormat format;

    /**
     * Constructs a formatting style using the specified pattern.
     *
     * @param pattern the {@link DecimalFormat} pattern
     */
    @Contract(pure = true)
    NumericFormat(String pattern) {
        this.format = new DecimalFormat(pattern);
    }

    /**
     * Formats the given number according to this style.
     *
     * @param number the numeric value to format
     * @return the formatted string
     */
    public String format(double number) {
        return format.format(number);
    }
}
