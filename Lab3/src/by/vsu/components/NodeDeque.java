package by.vsu.components;

import by.vsu.abstractClasses.AbstractDeque;
import by.vsu.entity.Node;

public class NodeDeque<T> extends AbstractDeque
{
    private Node<T> node = null;


    @Override
    public boolean isEmpty()
    {
        return node == null;
    }

    @Override
    public T popBack()
    {
        if (!isEmpty())
        {
            if (node.getNextNode() == null)
            {
                T value = node.getValue();
                node = null;

                return value;
            }

            return node.popBack();
        }

        return null;
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
    public boolean pushFront(Object value)
    {
        if (isEmpty())
        {
            node = new Node(value);
        }
        else
        {
            Node<T> node = this.node;
            this.node = new Node<>((T)value);
            this.node.setNextNode(node);
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
