package com.wangyousong.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toConcurrentMap;

public class Finder<KEY, T> {
    private final Map<KEY, T> keyMap;

    private Finder(Map<KEY, T> keyMap) {
        this.keyMap = keyMap;
    }

    public static <KEY, T> Finder<KEY, Collection<T>> group(Collection<T> values, Function<T, KEY> keyMapper) {
        return new Finder<>(values.stream().collect(Collectors.groupingBy(keyMapper, Collectors.toCollection(ArrayList::new))));
    }

    public Finder(Collection<T> items, Function<T, KEY> keyGetter) {
        keyMap = items.parallelStream()
                .collect(toConcurrentMap(keyGetter, it -> it));
    }

    public <V> Finder(Collection<V> items, Function<V, KEY> keyMapper, Function<V, T> valueMapper) {
        keyMap = items.parallelStream()
                .collect(toConcurrentMap(keyMapper, valueMapper));
    }

    public Optional<T> get(KEY key) {
        return Optional.ofNullable(keyMap.get(key));
    }

    public T getOrDefault(KEY key, T defaultValue) {
        return get(key).orElse(defaultValue);
    }

    public Collection<KEY> keys() {
        return keyMap.keySet();
    }

    public Collection<T> values() {
        return keyMap.values();
    }

    public <R> Set<R> getValueField(Function<T, R> valueMapper) {
        return values().stream()
                .map(valueMapper)
                .collect(Collectors.toSet());
    }

    public <R> Set<R> getValuesField(Function<T, Stream<R>> valueMapper) {
        return values().stream()
                .flatMap(valueMapper)
                .collect(Collectors.toSet());
    }
}
