package org.example;

import java.util.Iterator;
import java.util.Stack;

/**
 * класс имплементирующий итератор ( с использованием алгоритма DFS ).
 *
 * @param <T> - любой тип
 */
public class DFSIterator<T> implements Iterator<T> {
    private Stack<Tree<T>> stack;

    /**
     * конструктор итератора.
     *
     * @param node - элемент дерева
     */
    public DFSIterator(Tree<T> node) {
        this.stack = new Stack<>();
        this.stack.push(node);
    }

    /**
     * переписанный метод hasNext для проверки на наличие следующиего элемента.
     *
     * @return - true or false
     */
    @Override
    public boolean hasNext() {
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
        return null;
    }
}