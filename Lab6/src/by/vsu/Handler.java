package by.vsu;

import heap.Heap;

import java.util.*;

public class Handler
{
    private Graph graph;
    private Integer wayWeight = Integer.MAX_VALUE;
    private Stack<Integer> way;


    public Handler(Graph graph)
    {
        this.graph = graph;
        way = new Stack<>();
    }

    public Graph getGraph()
    {
        return graph;
    }

    public Stack<Integer> getWay()
    {
        return way;
    }

    public Integer getWayWeight()
    {
        return wayWeight;
    }

    public void setGraph(Graph graph)
    {
        this.graph = graph;
    }

    public void setWayWeight(Integer wayWeight)
    {
        this.wayWeight = wayWeight;
    }

    public void setWay(Stack<Integer> way)
    {
        this.way = way;
    }

    public void shortestWay(int start, int end)//сложность (n - 1)!
    {
        if (start == end)
        {
            System.out.println("Вершины совпадают");
            return;
        }

        Node[] nodeArray = graph.getNodeArray();
        int n = nodeArray.length;

        if (start >= 0 && start < n && end >= 0 && end < n)
        {
            Stack<Node> stack = new Stack<>();

            getWay(nodeArray[start], stack, end, 0);

            if (!getWay().isEmpty())
            {
                getWay().add(0, start);

                System.out.println(getWay());
                System.out.println(getWayWeight());
            }
            else
            {
                System.out.println("Пути нет");
            }
        }

        setWayWeight(Integer.MAX_VALUE);
        setWay(new Stack<>());
    }

    private void getWay(Node node, Stack<Node> stack, int end, int wayWeight)
    {
        Node[] nodeArray = graph.getNodeArray();

        while (node != null && !stack.contains(node))
        {
            stack.push(node);

            int weight;
            if (node.getRoadType())//добавляем вес дороги
            {
                weight = Node.FARE_ON_THE_RAIL;
            }
            else
            {
                weight = Node.FARE_ON_THE_ROAD;
            }

            if (stack.size() == 1 || stack.size() > 1 && stack.get(stack.size() - 2).getRoadType() != node.getRoadType())//если предыдущая дорога другого типа
            {
                weight *= 1.1d;
            }

            wayWeight += weight;

            if (node.getApex() == end)//если дошли до конца
            {
                if (wayWeight < getWayWeight())
                {
                    setWayWeight(wayWeight);

                    Stack<Integer> way = new Stack<>();
                    for (int i = 0; i < stack.size(); i++)
                    {
                        way.push(stack.get(i).getApex());
                    }

                    setWay(way);
                }
            }
            else
            {
                getWay(nodeArray[node.getApex()], stack, end, wayWeight);
            }

            wayWeight -= weight;//отнимаем вес дороги

            node = node.getNext();

            stack.pop();
        }
    }

    public void getWay(int start, int end)
    {
        int size = graph.getNodeArray().length;

        if (start == end)
        {
            System.out.println("Вершины совпадают");
            return;
        }

        if (start >= 0 && start < size && end >= 0 && end < size)
        {
            Stack<Integer> way = new Stack<>();
            boolean[] used = new boolean[size];
            int[] distance = new int[size];
            int[] previous = new int[size];
            Heap heap = new Heap();//куча с рассотяниями до всех вершин

            Arrays.fill(previous, -1);
            Arrays.fill(distance, Integer.MAX_VALUE / 2);
            distance[start] = 0;

            for (int i = 0; i < size; i++)
            {
                heap.insert(new heap.Node(distance[i], i));
            }

            heap.Node min = heap.getMin();
            int value, weight, current;
            boolean factor = true;//множитель 1.1

            while (min != null && min.getValue() != end)
            {
                value = min.getValue();//текущая вершина
                used[value] = true;

                Node apex = graph.getNodeArray()[value];//первая смежная с ней

                while (apex != null)
                {
                    if (apex.getRoadType())//добавляем вес дороги
                    {
                        weight = Node.FARE_ON_THE_RAIL;
                    }
                    else
                    {
                        weight = Node.FARE_ON_THE_ROAD;
                    }

                    current = apex.getApex();//индекс вершины

                    if (factor || previous[value] != -1 && getRoadType(previous[value], value) != apex.getRoadType())
                    {
                        weight *= 1.1d;
                    }

                    if (!used[current] && distance[current] > distance[value] + weight)
                    {
                        distance[current] = distance[value] + weight;
                        previous[current] = value;

                        heap.Node trash = heap.getMin();//пересоздаем кучу
                        while (trash != null)
                        {
                            trash = heap.getMin();
                        }
                        for (int i = 0; i < size; i++)
                        {
                            if (!used[i])
                            {
                                heap.insert(new heap.Node(distance[i], i));
                            }
                        }
                    }

                    apex = apex.getNext();//следующая смежная вершина
                }

                factor = false;
                min = heap.getMin();
            }

            way.add(end);//формирование пути
            value = end;
            while (previous[value] != -1)
            {
                way.add(0, previous[value]);
                value = previous[value];
            }

            if (distance[end] < Integer.MAX_VALUE / 2)
            {
                System.out.println(way);
                System.out.println(distance[end]);
            }
            else
            {
                System.out.println("Пути нет");
            }
        }
    }

    private boolean getRoadType(int source, int destination)
    {
        Node root = graph.getNodeArray()[source];

        while (root != null)
        {
            if (root.getApex() == destination)
            {
                return root.getRoadType();
            }

            root = root.getNext();
        }

        return false;
    }

    @Override
    public String toString()
    {
        return graph.toString();
    }
}

