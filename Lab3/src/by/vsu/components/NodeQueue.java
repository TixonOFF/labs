package by.vsu.components;

import by.vsu.abstractClasses.AbstractQueue;
import by.vsu.entity.Node;

public class NodeQueue<T> extends AbstractQueue
{
    private Node<T> node = null;


    @Override
    public boolean isEmpty()
    {
        return node == null;
    }

    @Override
    public T popFront()
    {
        if (!isEmpty())
        {
            Node<T> node = this.node;
            this.node = this.node.getNextNode();

            return node.getValue();
        }

        return null;
    }

    @Override
    public boolean pushBack(Object value)
    {
        if (isEmpty())
        {
            node = new Node(value);
        }
        else
        {
            node.pushBack((T)value);
        }

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (!isEmpty())
        {
            stringBuilder.append(node);
        }

        return stringBuilder.toString();
    }
}
