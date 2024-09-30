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
        boolean result = false;
        if (data.length != 0 && cursor < data.length) {
            while (data[cursor] == null && cursor < data.length - 1) {
                cursor++;
            }
            result = data[cursor] != null;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int nonNullNumber = data[cursor];
        cursor++;
        return nonNullNumber;
    }

}