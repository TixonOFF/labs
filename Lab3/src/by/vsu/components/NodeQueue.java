package by.vsu.components;

import by.vsu.abstractClasses.AbstractQueue;
import by.vsu.entity.Node;
import org.jetbrains.annotations.NotNull;

public class NodeQueue<T> extends AbstractQueue
{
    private Node<T> head = null;
    private Node<T> tail = null;


    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    @Override
    public T popFront()
    {
        if (!isEmpty())
        {
            T res = head.getValue();

            head = head.getNext();

            return res;
        }

        return null;
    }

    @Override
    public boolean pushBack(Object value)
    {
        if (isEmpty())
        {
            head = new Node(value);
            tail = head;
        }
        else
        {
            tail.setNext(new Node<>((T)value));
            tail = tail.getNext();
        }

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (!isEmpty())
        {
            stringBuilder.append(toString(head));
        }

        return stringBuilder.toString();
    }

    @NotNull
    private String toString(Node<T> node)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(node);
        stringBuilder.append(" ");

        if (node.getNext() != null)
        {
            stringBuilder.append(toString(node.getNext()));
        }

        return stringBuilder.toString();
    }
}
