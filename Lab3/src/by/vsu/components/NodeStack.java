package by.vsu.components;

import by.vsu.abstractClasses.AbstractStack;
import by.vsu.entity.Node;
import org.jetbrains.annotations.NotNull;

public class NodeStack<T> extends AbstractStack
{
    private Node<T> head = null;
    private Node<T> tail = null;


    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    @Override
    public T popBack()
    {
        if (!isEmpty())
        {
            if (head.getNext() == null)
            {
                T value = head.getValue();
                head = null;
                tail = null;

                return value;
            }
            else
            {
                return popBack(head);
            }
        }

        return null;
    }

    private T popBack(Node<T> node)
    {
        if (node.getNext() == tail)
        {
            T value = tail.getValue();

            tail = node;
            node.setNext(null);

            return value;
        }

        return (T)popBack(node.getNext());
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
