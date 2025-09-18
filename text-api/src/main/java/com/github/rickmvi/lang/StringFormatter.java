package com.github.rickmvi.lang;

import com.github.rickmvi.collections.map.Mapping;

import com.github.rickmvi.console.util.convert.Stringifier;
import com.github.rickmvi.util.constants.Constants;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import com.github.rickmvi.control.Repeater;
import com.github.rickmvi.debug.Logger;

import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.rickmvi.util.Primitives.isNegative;
import static com.github.rickmvi.util.Primitives.isGreaterThan;
import static com.github.rickmvi.jtoolbox.collections.array.Array.length;

@lombok.experimental.UtilityClass
public final class StringFormatter {

    private final Pattern GENERIC_PATTERN = Pattern.compile(Constants.GENERIC);

    private static final Map<String, Object> TOKENS = Map.of(
            "%n", System.lineSeparator(),
            "%r", "\r",
            "%t", "\t",
            "%sp", " "
    );

    public static @NotNull String format(@NotNull String templateString, @NotNull Object... args) {
        templateString = Mapping.getReplacement(templateString, TOKENS);
        templateString = replaceIndexTokens(templateString, args);
        for (Object arg : args) {
            templateString = GENERIC_PATTERN
                    .matcher(templateString)
                    .replaceFirst(
                            Matcher.quoteReplacement(Stringifier.valueOf(arg)));
        }

        return templateString;
    }

    private static @NotNull String replaceIndexTokens(@NotNull String template, Object @NotNull ... args) {
        Pattern tokenIndex = Pattern.compile(Constants.PLACEHOLDERS_REGEX);
        Matcher matcher = tokenIndex.matcher(template);
        StringBuffer buffer = new StringBuffer();

        Repeater.whileTrue(matcher::find, () -> {
            String token = matcher.group(1);

            int index = -1;
            index = getIndex(index, matcher);

            if (isNegative(index) || isGreaterThan(index, length(args))) {
                matcher.appendReplacement(buffer, "");
                return;
            }

            Object value = args[index];
            String replacement = returnToken(token, value);
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(replacement));
        });

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    @ApiStatus.Internal
    private static int getIndex(int index, @NotNull Matcher matcher) {
        try {
            index = Integer.parseInt(matcher.group(2));
        } catch (NumberFormatException e) {
            Logger.error("Invalid index format in placeholder '{}'", e, matcher.group(2));
        }
        return index;
    }

    @ApiStatus.Internal
    private static @NotNull String returnToken(String token, Object value) {
        return Mapping.returning(token,
                Map.of(
                        "dc", () -> NumericFormatter.DECIMAL_COMMA.format(value),
                        "dp", () -> NumericFormatter.DECIMAL_POINT.format(value),
                        "in", () -> NumericFormatter.INTEGER.format(value),
                        "p",  () -> NumericFormatter.PERCENT.format(value),
                        "sc", () -> NumericFormatter.SCIENTIFIC.format(value),
                        "S",  () -> String.valueOf(value).toUpperCase(),
                        "lc", () -> String.valueOf(value).toLowerCase()
        ), () -> "");
    }
}
