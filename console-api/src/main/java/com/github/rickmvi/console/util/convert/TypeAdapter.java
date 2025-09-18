package com.github.rickmvi.console.util.convert;

import java.util.Objects;

public class TypeAdapter {

    public static int toInt(Object o) {
        if (Objects.isNull(o)) return 0;
        if (o instanceof Number) return ((Number) o).intValue();
        return Integer.parseInt(Stringifier.valueOf(o));
    }

    public static long toLong(Object o) {
        if (Objects.isNull(o)) return 0L;
        if (o instanceof Number) return ((Number) o).longValue();
        return Long.parseLong(Stringifier.valueOf(o));
    }

    public static float toFloat(Object o) {
        if (Objects.isNull(o)) return 0.0f;
        if (o instanceof Number) return ((Number) o).floatValue();
        return Float.parseFloat(Stringifier.valueOf(o));
    }

    public static double toDouble(Object o) {
        if (Objects.isNull(o)) return 0.0d;
        if (o instanceof Number) return ((Number) o).doubleValue();
        return Double.parseDouble(Stringifier.valueOf(o));
    }

    public static boolean toBoolean(Object o) {
        if (Objects.isNull(o)) return false;
        if (o instanceof Boolean) return (Boolean) o;
        return Boolean.parseBoolean(Stringifier.valueOf(o));
    }
}
