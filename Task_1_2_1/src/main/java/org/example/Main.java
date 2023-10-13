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
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.removeSubtree();
        BFSIterator<String> bfs = new BFSIterator<String>(tree);
        while (bfs.hasNext()) {
            System.out.println(bfs.next());
        }
        System.out.println("------------------");
        DFSIterator<String> dfs = new DFSIterator<String>(tree);
        while (dfs.hasNext()) {
            System.out.println(dfs.next());
        }
    }
}