package by.vsu;

public class Main
{
    public static void main(String[] args)
    {
        BSTree<Integer> bsTree = new BSTree<>();
        //BSTree<Integer> bsTree = new AVLTree<>();

        bsTree.add(60);
        bsTree.add(50);
        bsTree.add(40);
        bsTree.add(80);
        bsTree.add(70);
        bsTree.add(69);
        bsTree.add(30);
        bsTree.add(20);
        bsTree.add(10);
        bsTree.add(45);
        bsTree.add(46);
        bsTree.add(44);
        bsTree.add(71);
        bsTree.add(95);
        bsTree.add(91);
        bsTree.add(94);
        bsTree.add(96);

        new Panel(bsTree);

        //bsTree.symmetricBypass2();

        Handler.task22(bsTree);

        System.out.println(bsTree);
    }
}
