package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));

    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfterTwoEqualOneTwoThree() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfNumberEqualThree() {
        ListUtils.removeIf(input, x -> x == 3);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenReplaceIfNumberEqualThreeThenFour() {
        ListUtils.replaceIf(input, x -> x == 3, 4);
        assertThat(input).hasSize(2).containsSequence(1, 4);
    }

    @Test
    void whenAddTwoFourThenRemoveAllEqualThreeFour() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.removeAll(input, List.of(1, 2));
        assertThat(input).hasSize(2).containsSequence(3, 4);
    }

}