package com.github.rickmvi.console;

import com.github.rickmvi.console.util.convert.Stringifier;
import com.github.rickmvi.control.Condition;
import com.github.rickmvi.lang.StringFormatter;
import com.github.rickmvi.debug.Logger;

import java.util.Objects;
import java.io.PrintStream;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.github.rickmvi.control.Condition.ifTrue;

public class Out {

    public static void print(@Nullable Object o) {
        ifTrue(o != null, () -> System.out.print(Stringifier.toString(o)));
    }

    public static void write(@Nullable Object o) {
        ifTrue(o != null, () -> System.out.println(Stringifier.toString(o)));
    }

    public static void formatted(@Nullable Object format, @Nullable Object... args) {
        ifTrue(format != null && Objects.nonNull(args),
                () -> print(StringFormatter.format(Stringifier.toString(format), args)));
    }

    public static void newline() {
        System.out.println();
    }

    public static void to(@NotNull PrintStream stream, @Nullable Object text) {
        ifTrue(text != null,
                () -> stream.print(Stringifier.toString(text)));
    }

    public static void debug(@Nullable Object o) {
        ifTrue(o != null, () -> Logger.debug(Stringifier.toString(o)));
    }

    public static void outputStackTrace(@Nullable Throwable t) {
        Condition.ifFalse(t == null, () -> t.printStackTrace(System.out));
    }

    public static void withOut(@NotNull Consumer<PrintStream> action) {
        action.accept(System.out);
    }

    public static void withErr(@NotNull Consumer<PrintStream> action) {
        action.accept(System.err);
    }
}
