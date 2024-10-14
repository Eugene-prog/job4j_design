package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class CyclicIteratorTest {

    @Test
    void whenEmptyThenHasNextIsFalse() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of());
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenEmptyAndNextThenThrow() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of());
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenNotEmptyThenHasNextIsTrue() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1));
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenNotEmptyThenSomeHasNextIsTrue() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1));
        iterator.hasNext();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenOneElementThenNext() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1));
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenNotEmptyThenNext() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1, 2, 3));
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
    }
}