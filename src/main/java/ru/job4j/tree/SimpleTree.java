package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> existingParentNode = findBy(parent);
        if (existingParentNode.isPresent()) {
            Optional<Node<E>> existingChildNode = findBy(child);
            if (existingChildNode.isEmpty()) {
                existingParentNode.get().children.add(new Node<>(child));
                result = true;
            }
        }
        return result;
    }

    public Optional<Node<E>> findByPredicate(Predicate<Node<E>> p) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (p.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> isLeafOrHasTwoChildren = element ->
                !element.children.isEmpty() && element.children.size() != 2;
        return findByPredicate(isLeafOrHasTwoChildren).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> valueEquals = element -> element.value.equals(value);
        return findByPredicate(valueEquals);
    }
}