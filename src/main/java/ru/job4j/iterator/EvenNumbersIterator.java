package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        this.index = -1;
    }

    private int searchIndex(int[] tempData, int tempIndex) {
        int startIndex = tempIndex;
        if (tempIndex < tempData.length - 1) {
            tempIndex++;
        }
        while (tempData[tempIndex] % 2 != 0) {
            if (tempIndex < tempData.length - 1) {
                tempIndex++;
            } else {
                break;
            }
        }
        return (tempData[tempIndex] % 2 == 0) ? tempIndex : startIndex;
    }

    @Override
    public boolean hasNext() {
        if (data.length == 0) {
            return false;
        }
        return index < searchIndex(data, index);
    }

    @Override
    public Integer next() {
        if (!hasNext() || data.length == 0) {
            throw new NoSuchElementException();
        }
        this.index = searchIndex(data, index);
        return data[index];
    }

}