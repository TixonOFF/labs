package by.vsu;

public class Graph
{
    private Node[] nodeArray;


    public Graph(int size)
    {
        nodeArray = new Node[Math.abs(size)];
    }

    public Node[] getNodeArray()
    {
        return nodeArray;
    }

    public void setNodeArray(Node[] nodeArray)
    {
        this.nodeArray = nodeArray;
    }

    public void add(int source, int destination, boolean roadType)
    {
        if (destination < nodeArray.length && source < nodeArray.length && destination >= 0 && source >= 0 && source != destination)
        {
            if (nodeArray[source] == null)
            {
                nodeArray[source] = new Node(destination, roadType);
            }
            else
            {
                Node node = nodeArray[source];
                nodeArray[source] = new Node(destination, roadType);
                nodeArray[source].setNext(node);
            }

            if (nodeArray[destination] == null)//для симметрии
            {
                nodeArray[destination] = new Node(source, roadType);
            }
            else
            {
                Node node = nodeArray[destination];
                nodeArray[destination] = new Node(source, roadType);
                nodeArray[destination].setNext(node);
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < nodeArray.length; i++)
        {
            stringBuilder.append(toString(nodeArray[i]));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public String toString(Node node)
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (node != null && node.getNext() != null)
        {
            stringBuilder.append(" ");
            stringBuilder.append(toString(node.getNext()));
        }

        return node + stringBuilder.toString();
    }
}
