package by.vsu;

public class Main
{
    public static void main(String[] args)
    {
        Heap heap = new Heap();
        heap.insert(new Node(3, 1));
        heap.insert(new Node(5, 1));
        heap.insert(new Node(7, 1));
        heap.insert(new Node(4, 1));
        heap.insert(new Node(1, 1));
        heap.insert(new Node(9, 1));
        heap.insert(new Node(0, 1));
        heap.insert(new Node(5, 1));

        new Panel(heap);


        Pattern pattern = new Pattern(4, 8);
        pattern.addFigure(-1, -1, 1, 1, 6);
        pattern.addFigure(1, -2, 2, 2, 8);
        pattern.addFigure(-2, -2, 2, 2, 7);
        pattern.addFigure(-2, -4, 0, 0, 36);
        pattern.addFigure(1, 3, 2, 4, 64);
        pattern.addFigure(0, 0, 1, 1, 36);
        pattern.addFigure(1, 1, 2, 2, 36);
        pattern.addFigure(1, -4, 2, -3, 64);
        pattern.addFigure(-2, 3, -1, 4, 64);
        pattern.addFigure(0, -4, 1, -1, 19);
        pattern.addFigure(-1, 0, 0, 2, 4);

        System.out.println(pattern);

        System.out.println(pattern.getAreas());
    }
}
