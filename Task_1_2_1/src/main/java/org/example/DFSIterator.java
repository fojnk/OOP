package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * класс имплементирующий итератор ( с использованием алгоритма DFS ).
 *
 * @param <T> - любой тип
 */
public final class DFSIterator<T> implements Iterator<T> {
    private final Tree<T> startedTree;
    private final Stack<Tree<T>> stack;
    private final int amountOfElem;

    /**
     * конструктор итератора.
     *
     * @param node - элемент дерева
     */
    public DFSIterator(Tree<T> node) {
        this.stack = new Stack<>();
        this.stack.push(node);
        this.amountOfElem = node.getAmountOfElem();
        this.startedTree = node;
    }

    /**
     * переписанный метод hasNext для проверки на наличие следующиего элемента.
     *
     * @return - true or false
     */
    @Override
    public boolean hasNext() {
        if (this.amountOfElem != this.startedTree.getAmountOfElem()) {
            throw new ConcurrentModificationException();
        }
        return !this.stack.empty();
    }

    /**
     * переписанный метод next для поиска следующего элемента.
     *
     * @return - следующий элемент или null
     */
    @Override
    public T next() {
        if (hasNext()) {
            Tree<T> next = this.stack.pop();
            for (Tree<T> node : next.getChilds()) {
                this.stack.push(node);
            }
            return next.getValue();
        }
        throw new NoSuchElementException();
    }
}