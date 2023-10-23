package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Класс деревва.
 *
 * @param <T> - любой тип
 */
public class Tree<T> implements Iterable<T> {
    private int amountOfElem;
    private final T value;
    private Tree<T> parent;
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
     * конструктор для глубокого клонирования.
     *
     * @param tree   - элемент, с которого начинаем клонирование
     * @param parent - родитель элемента, для установления связей в новом дереве
     */
    private Tree(Tree<T> tree, Tree<T> parent) {
        this.parent = parent;
        this.amountOfElem = tree.amountOfElem;
        this.value = tree.value;
        this.childs = new ArrayList<>();
        for (var elem : tree.getChilds()) {
            this.childs.add(new Tree<>(elem, this));
        }
    }

    /**
     * метод для глубокого клонирования дерева.
     *
     * @return - новое дерево.
     */
    public Tree<T> make_clone() {
        return (new Tree<>(this, this.parent));
    }

    /**
     * метод для поверхностного клонирования, оставил для тестов.
     *
     * @return - клонированный список
     */
    public ArrayList<Tree<T>> getChilds() {
        return (ArrayList<Tree<T>>) this.childs.clone();
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
     * метод для получения потока.
     *
     * @return - поток
     */
    public Stream<T> treeStream() {
        var mySpliterator = Spliterators.spliterator(this.iterator(), this.amountOfElem,
                Spliterator.IMMUTABLE | Spliterator.SIZED | Spliterator.DISTINCT
        );
        return StreamSupport.stream(mySpliterator, false);
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
     * метод для добавления поддерева.
     *
     * @param subtree - поддрево
     * @return - ссылка на поддерево
     */
    public Tree<T> addChild(Tree<T> subtree) throws NullSubTreeException {
        if (subtree == null) {
            throw new NullSubTreeException("null subtree");
        }
        subtree.parent = this;
        this.childs.add(subtree);
        upAmounts(this.amountOfElem);
        return subtree;
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
     * Переписанный метод equals.
     *
     * @param o - объект
     * @return - true or false
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Tree<?> tree)) {
            return false;
        }

        if (this.amountOfElem != tree.amountOfElem) {
            return false;
        }

        boolean result = true;
        var dfs1 = tree.iterator();
        var dfs2 = this.iterator();
        while (dfs1.hasNext() && dfs2.hasNext()) {
            if (dfs1.next() != dfs2.next()) {
                return false;
            }
        }
        if (dfs1.hasNext() != dfs2.hasNext()) {
            result = false;
        }
        return result;
    }
}