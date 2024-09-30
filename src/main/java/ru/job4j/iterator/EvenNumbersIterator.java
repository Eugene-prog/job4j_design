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
        boolean result = false;
        if (data.length != 0 && cursor < data.length) {
            while (data[cursor] % 2 != 0 && cursor < data.length - 1) {
                cursor++;
            }
            result = cursor < data.length && data[cursor] % 2 == 0;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int evenNumber = data[cursor];
        cursor++;
        return evenNumber;
    }
}