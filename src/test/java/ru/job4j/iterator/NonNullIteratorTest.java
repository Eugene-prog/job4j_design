package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class NonNullIteratorTest {
    private Iterator<Integer> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new NonNullIterator(new Integer[]{null, null, 2, null, null, null, -4, null, 6, null});
    }

    @Test
    void shouldReturnNotNullElementsSequentially() {
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(-4);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(6);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(-4);
        assertThat(iterator.next()).isEqualTo(6);
    }

    @Test
    void shouldReturnFalseIfNoAnyNotNullElements() {
        iterator = new NonNullIterator(new Integer[]{null});
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void shouldReturnFalseIfNoAnyElements() {
        iterator = new NonNullIterator(new Integer[]{});
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void allNumbersAreNotNull() {
        iterator = new NonNullIterator(new Integer[]{2, 4, 6, 8});
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(6);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(8);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void allNumbersAreNull() {
        iterator = new NonNullIterator(new Integer[]{null, null, null, null});
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.hasNext()).isFalse();
    }
}