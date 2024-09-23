package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class SimpleStackTest {
    private SimpleStack<Integer> stack;

    @BeforeEach
    void init() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
    }

    @Test
    void whenPushThenPoll() {
        stack.push(3);
        stack.push(4);
        assertThat(stack.pop()).isEqualTo(4);
    }

    @Test
    void whenPushPollThenPushPoll() {
        stack.pop();
        stack.push(3);
        assertThat(stack.pop()).isEqualTo(3);
    }

    @Test
    void whenPushPushThenPollPoll() {
        stack.pop();
        assertThat(stack.pop()).isEqualTo(1);
        assertThatThrownBy(stack::pop)
                .isInstanceOf(NoSuchElementException.class);
    }
}