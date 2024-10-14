package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int cursor;

    public CyclicIterator(List<T> data) {
        this.data = data;
        this.cursor = -1;
    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (cursor == data.size() - 1) {
            cursor = 0;
        } else {
            cursor++;
        }
        return data.get(cursor);
    }
}
