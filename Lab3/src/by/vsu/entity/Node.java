package by.vsu.entity;

public class Node<T>
{
    private Node nextNode = null;
    private T value;


    public Node(T value)
    {
        this.value = value;
    }

    public Node getNextNode()
    {
        return nextNode;
    }

    public T getValue()
    {
        return value;
    }

    public void setNextNode(Node nextNode)
    {
        this.nextNode = nextNode;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public Node next()
    {
        return nextNode;
    }

    public void pushBack(T value)
    {
        if (nextNode == null)
        {
            nextNode = new Node(value);
        }
        else
        {
            nextNode.pushBack(value);
        }
    }

    public T popBack()
    {
        if (nextNode.getNextNode() == null)
        {
            T value = (T)nextNode.getValue();
            nextNode = null;

            return value;
        }

        return (T)nextNode.popBack();
    }

    public int getSize()
    {
        if (nextNode == null)
        {
            return 1;
        }

        return 1 + nextNode.getSize();
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (value != null)
        {
            stringBuilder.append(value);
            stringBuilder.append(" ");
        }
        if (nextNode != null)
        {
            stringBuilder.append(nextNode);
        }

        return stringBuilder.toString();
    }
}
