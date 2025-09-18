package com.github.rickmvi.lang;

import com.github.rickmvi.jtoolbox.console.utils.convert.TypeCaster;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@lombok.experimental.UtilityClass
public class NumericFormatter {
    
    /**
     * Creates a {@link NumberFormat} from the specified style.
     *
     * @param style the formatting style
     * @return a functional instance of {@link NumberFormat}
     */
    @Contract(pure = true)
    public static @NotNull NumberFormat of(NumericFormat style) {
        return value -> format(value, style);
    }

    @ApiStatus.Internal
    private static String format(Object value, @NotNull NumericFormat style) {
        return style.format(TypeCaster.toDouble(value));
    }
    
    /** Formatter with comma as a decimal separator */
    public static final NumberFormat DECIMAL_COMMA = of(NumericFormat.DECIMAL_COMMA);

    /** Formatter with dot as a decimal separator */
    public static final NumberFormat DECIMAL_POINT = of(NumericFormat.DECIMAL_POINT);

    /** Formatter for integers with a thousand separator */
    public static final NumberFormat INTEGER       = of(NumericFormat.INTEGER);

    /** Formatter for percentages with two decimal places */
    public static final NumberFormat PERCENT       = of(NumericFormat.PERCENT);

    /** Formatter in scientific notation */
    public static final NumberFormat SCIENTIFIC    = of(NumericFormat.SCIENTIFIC);
}
