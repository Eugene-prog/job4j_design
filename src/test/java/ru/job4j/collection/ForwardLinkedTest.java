package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class ForwardLinkedTest {

    private ForwardLinked<Integer> list;

    @BeforeEach
    public void initData() {
        list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
    }

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void checkAdd() {
        assertThat(list).containsExactly(1, 2);
        list.add(3);
        assertThat(list).containsExactly(1, 2, 3);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenGetNegateIndexThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAndDeleteFirstThenOk() {
        assertThat(list).containsExactly(1, 2);
        list.add(3);
        assertThat(list).containsExactly(1, 2, 3);
        assertThat(list.deleteFirst()).isEqualTo(1);
        assertThat(list).containsExactly(2, 3);
        assertThat(list.deleteFirst()).isEqualTo(2);
        assertThat(list).containsExactly(3);
    }

    @Test
    void whenDeleteFirstFromEmptyListThenException() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        assertThat(list).isEmpty();
        assertThatThrownBy(list::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenAddIterHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenHasIteratorAndAddThenHasNextExceptionThrown() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenHasIteratorAndAddThenNextExceptionThrown() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenAddIterNextOne() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHashNextFalse() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenAddIterMultiHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOneNextTwo() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
    }
}