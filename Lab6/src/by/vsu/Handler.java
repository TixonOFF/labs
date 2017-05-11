package by.vsu;

import java.util.Stack;

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

    public void shortestWay(int firstApex, int lastApex)//сложность (n - 1)!
    {
        if (firstApex == lastApex)
        {
            System.out.println("Вершины совпадают");
            return;
        }

        Node[] nodeArray = graph.getNodeArray();
        int n = nodeArray.length;

        if (firstApex >= 0 && firstApex < n && lastApex >= 0 && lastApex < n)
        {
            Stack<Node> stack = new Stack<>();

            getWay(nodeArray[firstApex], stack, lastApex, 0);

            if (!getWay().isEmpty())
            {
                getWay().add(0, firstApex);

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

    private void getWay(Node node, Stack<Node> stack, int lastApex, int wayWeight)
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

            if (stack.size() > 1 && stack.get(stack.size() - 2).getRoadType() != node.getRoadType())//если предыдущая дорога другого типа
            {
                weight *= 1.1d;
            }

            wayWeight += weight;

            if (node.getApex() == lastApex)//если дошли до конца
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
                getWay(nodeArray[node.getApex()], stack, lastApex, wayWeight);
            }

            wayWeight -= weight;//отнимаем вес дороги

            node = node.getNext();

            stack.pop();
        }
    }

    @Override
    public String toString()
    {
        return graph.toString();
    }
}
