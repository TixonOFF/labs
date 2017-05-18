package by.vsu;

public class Main
{
    public static void main(String[] args)
    {
        int size = 11;

        Graph graph = new Graph(size);
        graph.add(0, 1, true);
        graph.add(0, 2, false);
        graph.add(0, 4, true);
        graph.add(0, 6, true);
        graph.add(0, 8, false);
        graph.add(1, 2, false);
        graph.add(1, 3, true);
        graph.add(2, 6, true);
        graph.add(3, 6, false);
        graph.add(3, 7, true);
        graph.add(6, 7, true);
        graph.add(6, 8, false);
        graph.add(7, 8, false);
        graph.add(9, 10, true);

        Handler handler = new Handler(graph);

        System.out.println(handler);

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.println("Из " + i + " в " + j + ":");
                handler.shortestWay(i, j);
                handler.getWay(i, j);
                System.out.println();
            }
        }
    }
}
