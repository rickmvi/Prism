package com.github.rickmvi.collections.map;

import com.github.rickmvi.console.util.convert.Stringifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.concurrent.CompletableFuture;

public class Mapping {

    /* ========================== Case Handling ========================== */

    public static <T> void on(T value, @NotNull Map<T, Runnable> cases, Runnable defaultAction) {
        cases.getOrDefault(value, defaultAction).run();
    }

    public static <T, R> R returning(T value, @NotNull Map<T, Supplier<R>> cases, Supplier<R> defaultAction) {
        return cases.getOrDefault(value, defaultAction).get();
    }

    @Contract("_, _, _ -> new")
    public static <T> @NotNull CompletableFuture<T> returnAsync(
            T value,
            Map<T, Supplier<T>> cases,
            Supplier<T> defaultCase
    ) {
        return CompletableFuture.supplyAsync(() -> returning(value, cases, defaultCase));
    }

    /* ========================== Iteration ========================== */

    public static <K, V> void forEach(@NotNull Map<K, V> map, BiConsumer<K, V> action) {
        map.forEach(action);
    }

    public static <K, V> void forEachEntry(@NotNull Map<K, V> map, Consumer<Map.Entry<K, V>> action) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.accept(entry);
        }
    }

    /* ========================== Retrieval ========================== */

    public static <K, V> V getOrDefault(@NotNull Map<K, V> map, K key, V defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    public static <K, V> @NotNull Optional<V> getFirstMatch(
            @NotNull Map<K, V> map,
            Predicate<Map.Entry<K, V>> predicate
    ) {
        return map.entrySet().stream()
                .filter(predicate)
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public static <K, V> boolean containsKeyIgnoreCase(@NotNull Map<K, V> map, String key) {
        return map.keySet().stream().anyMatch(k -> k.toString().equalsIgnoreCase(key));
    }

    /* ========================== Transformations ========================== */

    public static <K, V> Map<K, V> filterMap(@NotNull Map<K, V> map, BiPredicate<K, V> predicate) {
        return map.entrySet().stream()
                .filter(e -> predicate.test(e.getKey(), e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <K, V, R> List<R> mapValues(@NotNull Map<K, V> map, BiFunction<K, V, R> mapper) {
        return map.entrySet().stream()
                .map(e -> mapper.apply(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    /* ========================== String Replacements ========================== */

    public static @NotNull String replace(
            @NotNull String target,
            @NotNull Map<String, Object> replacements
    ) {
        return getReplacement(target, replacements);
    }

    public static @NotNull String getReplacement(
            @NotNull String target,
            @NotNull Map<String, Object> replacements
    ) {
        for (Map.Entry<String, Object> entry : replacements.entrySet()) {
            target = target.replace(entry.getKey(), Stringifier.valueOf(entry.getValue()));
        }
        return target;
    }

    public static <K, V> @NotNull String replaceAll(
            @NotNull String target,
            @NotNull Map<K, V> replacements
    ) {
        for (Map.Entry<K, V> entry : replacements.entrySet()) {
            String key = Stringifier.valueOf(entry.getKey());
            String value = Stringifier.valueOf(entry.getValue());
            target = target.replace(key, value);
        }
        return target;
    }

    /* ========================== Sorting ========================== */

    public static <K, V> Map<K, V> sortByKey(@NotNull Map<K, V> map, Comparator<K> comparator) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(comparator))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static <K, V> Map<K, V> sortByValue(@NotNull Map<K, V> map, Comparator<V> comparator) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}
