package org.example;

/**
 * Главный класс.
 */
public class Main {
    /**
     * входная точка.
     *
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        try {
            tree.addChild(subtree);
        } catch (NullSubTreeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        var b = a.addChild("B");
        b.removeSubtree();
        for (var elem : tree) {
            System.out.println(elem);
        }
        System.out.println("------------------");
        tree.treeStream().forEach(System.out::println);
        System.out.println("------------------");
        try {
            for (var elem : tree) {
                tree.addChild("k");
                System.out.println(elem);
            }
        } catch (Exception ConcurrentModificationException) {
            System.out.println("помер :(");
        }
    }
}