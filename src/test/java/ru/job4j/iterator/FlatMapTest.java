package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

class FlatMapTest {
    @Test
    void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flatMap = new FlatMap<>(data);
        assertThat(flatMap.next()).isEqualTo(1);
        assertThat(flatMap.next()).isEqualTo(2);
        assertThat(flatMap.next()).isEqualTo(3);
    }

    @Test
    void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flatMap = new FlatMap<>(data);
        assertThat(flatMap.next()).isEqualTo(1);
        assertThat(flatMap.next()).isEqualTo(2);
        assertThat(flatMap.next()).isEqualTo(3);
    }

    @Test
    void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flatMap = new FlatMap<>(data);
        assertThat(flatMap.hasNext()).isTrue();
        assertThat(flatMap.hasNext()).isTrue();
    }

    @Test
    void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flatMap = new FlatMap<>(data);
        assertThat(flatMap.next()).isEqualTo(1);
        assertThat(flatMap.hasNext()).isFalse();
    }

    @Test
    void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(
                Collections.emptyIterator()
        ).iterator();
        FlatMap<Object> flatMap = new FlatMap<>(data);
        assertThatThrownBy(flatMap::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenEmptyInteger() {
        Iterator<Integer> empty = Collections.emptyIterator();
        Iterator<Iterator<Integer>> data = List.of(
                empty
        ).iterator();
        FlatMap<Integer> flatMap = new FlatMap<>(data);
        assertThatThrownBy(flatMap::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenSeveralEmptyAndNotEmpty() {
        Iterator<Integer> empty = Collections.emptyIterator();
        Iterator<Iterator<Integer>> data = List.of(
                empty,
                empty,
                empty,
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flatMap = new FlatMap<>(data);
        assertThat(flatMap.hasNext()).isTrue();
        assertThat(flatMap.next()).isEqualTo(1);
    }

    @Test
    void whenSeveralEmptyThenReturnFalse() {
        Iterator<Integer> empty = Collections.emptyIterator();
        Iterator<Iterator<Integer>> data = List.of(
                empty,
                empty,
                empty
        ).iterator();
        FlatMap<Integer> flatMap = new FlatMap<>(data);
        assertThat(flatMap.hasNext()).isFalse();
    }
}