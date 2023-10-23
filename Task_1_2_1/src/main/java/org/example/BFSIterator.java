package org.example;


import java.util.*;

/**
 * класс имплементирующий итератор ( с ипользованием алгоритма BFS ).
 *
 * @param <T> - любой тип
 */
public final class BFSIterator<T> implements Iterator<T> {
    private final Tree<T> startedTree;
    private final int amountOfElem;
    private final Queue<Tree<T>> quene;

    /**
     * конструктор итератора.
     *
     * @param node - элемент дерева
     */
    public BFSIterator(Tree<T> node) {
        this.quene = new LinkedList<>();
        this.quene.add(node);
        this.amountOfElem = node.getAmountOfElem();
        this.startedTree = node;
    }

    /**
     * переписанный метод hasNext для проверки на наличие следующего элемента.
     *
     * @return - true or false
     */
    @Override
    public boolean hasNext() {
        if (this.amountOfElem != this.startedTree.getAmountOfElem()) {
            throw new ConcurrentModificationException();
        }
        return !this.quene.isEmpty();
    }

    /**
     * переписанный метод next для поиска следующего элемента.
     *
     * @return - возвращает следующий элемент или бросает исключение
     */
    @Override
    public T next() {
        if (hasNext()) {
            Tree<T> next = this.quene.remove();
            this.quene.addAll(next.getChilds());
            return next.getValue();
        }
        throw new NoSuchElementException();
    }
}