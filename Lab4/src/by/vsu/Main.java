package by.vsu;

public class Main
{
    public static void main(String[] args)
    {
        BSTree<Integer> bsTree = new BSTree<>();
        //BSTree<Integer> bsTree = new AVLTree<>();

        bsTree.add(4);
        bsTree.add(5);
        bsTree.add(6);

        new Panel(bsTree);
    }
}
