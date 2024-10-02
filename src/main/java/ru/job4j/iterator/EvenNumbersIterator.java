package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int cursor;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        while (cursor < data.length && data[cursor] % 2 != 0) {
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