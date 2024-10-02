package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int cursor;

    public NonNullIterator(Integer[] data) {
        this.data = data;
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        while (cursor < data.length && data[cursor] == null) {
            cursor++;
        }
        return cursor < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[cursor++];
    }

}