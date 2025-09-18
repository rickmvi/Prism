package com.github.rickmvi.collections.queue;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Queuing {

    /**
     * Filters the given queue using a predicate and returns a new queue with the matched elements.
     *
     * @param queue     the source queue to filter
     * @param predicate the condition to apply to each element
     * @param <T>       the type of elements in the queue
     * @return a new queue containing only the elements that match the predicate
     */
    public static <T> Queue<T> filterQueue(@NotNull Queue<T> queue, Predicate<T> predicate) {
        return queue.stream().filter(predicate).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Transforms the elements of a queue into another type using a mapping function.
     *
     * @param queue  the source queue
     * @param mapper the function to apply to each element
     * @param <T>    the type of input elements
     * @param <R>    the type of output elements
     * @return a new queue with the mapped elements
     */
    public static <T, R> Queue<R> mapQueue(@NotNull Queue<T> queue, Function<T, R> mapper) {
        return queue.stream().map(mapper).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Safely retrieves the head of the queue without removing it, or null if the queue is empty.
     *
     * @param queue the queue to peek
     * @param <T>   the type of elements
     * @return the head element or null if the queue is empty
     */
    @Contract(pure = true)
    public static <T> T peekOrNull(@NotNull Queue<T> queue) {
        return queue.peek();
    }

    /**
     * Retrieves and removes the head of the queue, or returns the fallback if empty.
     *
     * @param queue    the queue to poll
     * @param fallback the value to return if the queue is empty
     * @param <T>      the type of elements
     * @return the head of the queue or fallback
     */
    public static <T> T pollOrDefault(@NotNull Queue<T> queue, T fallback) {
        return queue.isEmpty() ? fallback : queue.poll();
    }

    /**
     * Checks whether the queue contains at least one element that matches the given predicate.
     *
     * @param queue     the queue to check
     * @param predicate the condition to apply
     * @param <T>       the type of elements
     * @return true if any element matches the predicate, false otherwise
     */
    public static <T> boolean containsInQueue(@NotNull Queue<T> queue, Predicate<T> predicate) {
        return queue.stream().anyMatch(predicate);
    }

    /**
     * Applies an action to each element in the queue.
     *
     * @param queue  the queue to iterate
     * @param action the action to perform on each element
     * @param <T>    the type of elements
     */
    public static <T> void forEachQueue(@NotNull Queue<T> queue, Consumer<T> action) {
        queue.forEach(action);
    }
}
