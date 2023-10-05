package org.example;

import java.util.ArrayList;

public class Tree<T> {
    private T value;
    private Tree<T> parent;
    private ArrayList<Tree<T>> childs;

    public Tree(T value) {
        this.value = value;
        this.parent = null;
        this.childs = new ArrayList<>();
    }

    public Tree(Tree<T> parent, T value) {
        this.value = value;
        this.parent = parent;
        this.childs = new ArrayList<>();
    }

    public Tree<T> addChild(T value) {
        Tree<T> newElem = new Tree<T>(this, value);
        this.childs.add(newElem);
        return newElem;
    }

    public Tree<T> addChild(Tree<T> subtree) {
        this.childs.add(subtree);
        return subtree;
    }

    public void remove() {
        if (this.parent != null) {
            this.parent.childs.remove(this);
        }
    }

}