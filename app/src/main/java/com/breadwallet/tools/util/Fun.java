package com.breadwallet.tools.util;

import java.util.ArrayList;
import java.util.List;

/*
 * Basic substitutes of Java 8's streams.
 */
public final class Fun {

    public static <TIn, TOut> List<TOut> map(TIn[] collection, Map<TIn, TOut> func) {
        List<TOut> result = new ArrayList<>();
        for (TIn element : collection) result.add(func.map(element));
        return result;
    }

    public static <TIn, TOut> List<TOut> map(Iterable<TIn> collection, Map<TIn, TOut> func) {
        List<TOut> result = new ArrayList<>();
        for (TIn element : collection) result.add(func.map(element));
        return result;
    }

    public static <T> List<T> filter(T[] collection, Filter<T> func) {
        List<T> result = new ArrayList<>();
        for (T element : collection) if (func.predicate(element)) result.add(element);
        return result;
    }

    public static <T> List<T> filter(Iterable<T> collection, Filter<T> func) {
        List<T> result = new ArrayList<>();
        for (T element : collection) if (func.predicate(element)) result.add(element);
        return result;
    }

    public interface Map<TIn, TOut> {
        TOut map(TIn value);
    }

    public interface Filter<T> {
        boolean predicate(T value);
    }
}
