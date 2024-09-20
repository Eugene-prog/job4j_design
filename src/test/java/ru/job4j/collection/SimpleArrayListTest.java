package ru.job4j.collection;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

class SimpleArrayListTest {

    private SimpleList<Integer> list;

    @BeforeEach
    void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void checkIterator() {
        assertThat(list.size()).isEqualTo(3);
        assertThat(list).hasSize(3);
    }

    @Test
    void whenAddThenSizeIncrease() {
        list.add(4);
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void whenRemoveThenGetValueAndSizeDecrease() {
        assertThat(list.remove(1)).isEqualTo(2);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(3);
    }

    @Test
    void whenAddAndGetByCorrectIndex() {
        list.add(4);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAndAndGetByIncorrectIndexThenGetException() {
        SimpleList<Integer> list = new SimpleArrayList<>(10);
        list.add(5);
        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.remove(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenGetByNegateIndexThenGetException() {
        assertThatThrownBy(() -> list.get(-2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenSetByNegateIndexThenGetException() {
        assertThatThrownBy(() -> list.set(-3, 22))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveByNegateIndexThenGetException() {
        assertThatThrownBy(() -> list.remove(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveByInvalidIndexThenGetException() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        assertThatThrownBy(() -> list.remove(1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isNull();
        assertThat(list.get(1)).isNull();
    }

    @Test
    void whenSetThenGetOldValueAndSizeNotChanged() {
        assertThat(list.set(1, 22)).isEqualTo(2);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void whenSetByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.set(5, 22))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenSetByInvalidIndexThenGetException() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        assertThatThrownBy(() -> list.set(2, 22))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        assertThat(list.iterator().hasNext()).isFalse();
    }

    @Test
    void whenGetIteratorFromEmptyListThenNextThrowException() {
        list = new SimpleArrayList<>(5);
        assertThatThrownBy(list.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        assertThat(list.iterator().next()).isEqualTo(1);
        assertThat(list.iterator().next()).isEqualTo(1);
    }

    @Test
    void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenNoPlaceThenMustIncreaseCapacity() {
        assertThat(list.size()).isEqualTo(3);
        IntStream.range(3, 10).forEach(v -> list.add(v));
        assertThat(list.size()).isEqualTo(10);
    }

    @Test
    void whenIncreaseEmptyContainer() {
        list = new SimpleArrayList<>(0);
        assertThat(list.size()).isZero();
        list.add(1);
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo(1);
    }

    @Test
    void whenAddAfterGetIteratorHasNextThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list.add(4);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenRemoveAfterGetIteratorHasNextThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list.remove(0);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenSetAfterGetIteratorHasNextThenMustBeOk() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list.set(0, 22);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.remove(0);
        assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenSetAfterGetIteratorThenMustBeOk() {
        Iterator<Integer> iterator = list.iterator();
        list.set(0, 22);
        assertThat(iterator.next()).isEqualTo(22);
    }
}
