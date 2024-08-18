package ru.job4j.assertj;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleCollection<T> implements Iterable<T> {
    private final T[] container = (T[]) new Object[5];

    public SimpleCollection(T v0, T v1, T v2, T v3, T v4) {
        this.container[0] = v0;
        this.container[1] = v1;
        this.container[2] = v2;
        this.container[3] = v3;
        this.container[4] = v4;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < container.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}