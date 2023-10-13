package org.example;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Класс деревва.
 *
 * @param <T> - любой тип
 */
public class Tree<T> implements Iterable<T> {
    private int amountOfElem;
    private final T value;
    private final Tree<T> parent;
    private final ArrayList<Tree<T>> childs;

    /**
     * констуктор класса.
     *
     * @param value - задаваемое занчение элемента дерева
     */
    public Tree(T value) {
        this.value = value;
        this.parent = null;
        this.childs = new ArrayList<>();
        this.amountOfElem = 1;
    }

    /**
     * перегрузка конструктора для добавления нового элемента с учетом его родителя.
     *
     * @param parent - родитель добавляемого элемента
     * @param value  - значение нового элемента дерева
     */
    private Tree(Tree<T> parent, T value) {
        this.value = value;
        this.parent = parent;
        this.childs = new ArrayList<>();
    }

    /**
     * метод для получения списка поддеревьев.
     *
     * @return - клонированный список поддеревьев
     */
    public ArrayList<Tree<T>> getChilds() {
        return (ArrayList<Tree<T>>) childs.clone();
    }

    /**
     * метод для получения значения текущего элемента дерева, используется в DFS и BFS.
     *
     * @return - значение элемента дерева
     */
    public T getValue() {
        return this.value;
    }

    /**
     * гетер для получения размера дерева.
     *
     * @return - количество элементов
     */
    public int getAmountOfElem() {
        return this.amountOfElem;
    }

    /**
     * метод для добавления нового элемента дерева.
     *
     * @param value - значение нового элемента
     * @return - ссылка на новый элемент
     */
    public Tree<T> addChild(T value) {
        Tree<T> newElem = new Tree<>(this, value);
        this.childs.add(newElem);
        upAmounts(1);
        return newElem;
    }

    /**
     * поднимает количество вершин для каждой из стоящих выше вершин на заданное число.
     *
     * @param amount - доваляемое число вершин
     */
    private void upAmounts(int amount) {
        this.amountOfElem += amount;
        if (this.parent != null) {
            this.parent.upAmounts(amount);
        }
    }

    /**
     * опускает количество вершин для каждой из стоящих выше вершин на заданное число.
     *
     * @param amount - кол-во удаленных вершин
     */
    private void downAmounts(int amount) {
        this.amountOfElem -= amount;
        if (this.parent != null) {
            this.parent.downAmounts(amount);
        }
    }

    /**
     * метод для добавления поддерева.
     *
     * @param subtree - поддрево
     * @return - ссылка на поддерево
     */
    public Tree<T> addChild(Tree<T> subtree) {
        this.childs.add(subtree);
        upAmounts(this.amountOfElem);
        return subtree;
    }

    /**
     * метод для удаления поддерева.
     */
    public void removeSubtree() {
        if (this.parent != null) {
            this.parent.childs.remove(this);
            this.parent.downAmounts(this.amountOfElem);
        }
    }

    /**
     * метод для удаления элемента дерева.
     */
    public void removeElem() {
        if (this.parent != null) {
            int len = this.childs.size();
            for (int i = 0; i < len; i++) {
                this.parent.childs.add(this.childs.get(i));
            }
            this.parent.childs.remove(this);
            this.parent.downAmounts(1);
        }
    }

    /**
     * метод для получения итератора.
     *
     * @return - DFS итератор
     */
    @Override
    public Iterator<T> iterator() {
        return new DFSIterator<>(this);
    }

    /**
     * сравнение двух деревьев.
     *
     * @param tree - дерево
     * @return - true or false
     */
    public boolean equals(Tree<T> tree) {
        boolean result = true;
        var dfs1 = tree.iterator();
        var dfs2 = this.iterator();
        while (dfs1.hasNext() && dfs2.hasNext()) {
            if (dfs1.next() != dfs2.next()) {
                return false;
            }
        }
        if (dfs1.hasNext() != dfs2.hasNext()) result = false;
        return result;
    }
}