package by.vsu.components;

import by.vsu.abstractClasses.AbstractUniDirectionalList;
import by.vsu.entity.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NodeUniDirectionalList<T> extends AbstractUniDirectionalList
{
    private Node<T> head = null;
    private Node<T> tail = null;
    private Node<T> pointer = null;


    @Override
    public T nextValue()
    {
        if (!isEmpty())
        {
            T res = pointer.getValue();

            if (pointer.getNext() == null)
            {
                pointer = head;
            }
            else
            {
                pointer = pointer.getNext();
            }

            return res;
        }

        return null;
    }

    @Nullable
    private Node<T> next()
    {
        if (!isEmpty())
        {
            if (pointer.getNext() == null)
            {
                return head;
            }
            else
            {
                return pointer.getNext();
            }
        }

        return null;
    }

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
                pointer = null;

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

            pointer = tail;

            return value;
        }

        return (T)popBack(node.getNext());
    }

    @Override
    public T popFront()
    {
        if (!isEmpty())
        {
            T res = head.getValue();

            head = head.getNext();
            pointer = head;

            return res;
        }

        return null;
    }

    @Override
    public boolean pushBack(Object value)
    {
        if (isEmpty())
        {
            head = new Node<>((T)value);
            tail = head;
            pointer = head;
        }
        else
        {
            tail.setNext(new Node<>((T)value));
            tail = tail.getNext();
            pointer = tail;
        }

        return true;
    }

    @Override
    public boolean pushFront(Object value)
    {
        if (isEmpty())
        {
            head = new Node<>((T)value);
            tail = head;
            pointer = head;
        }
        else
        {
            Node<T> node = head;
            head = new Node<>((T)value);
            head.setNext(node);

            pointer = head;
        }

        return true;
    }

    @Override
    public T pop()
    {
        if (!isEmpty())
        {
            T res = pointer.getValue();

            pointer.setValue(next().getValue());
            pointer.setNext(next().getNext());

            return res;
        }

        return null;
    }

    @Override
    public T popAfter()
    {
        pointer = next();

        return pop();
    }

    @Override
    public boolean pushBefore(Object value)
    {
        pointer = next();

        return pushAfter(value);
    }

    @Override
    public boolean pushAfter(Object value)
    {
        if (isEmpty())
        {
            head = new Node<>((T)value);
            tail = head;
            pointer = head;
        }
        else
        {
            Node<T> node = next();

            pointer.setNext(new Node<>((T)value));
            pointer.getNext().setNext(node);
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

        if (node.getNext() != null && node.getNext() != head)
        {
            stringBuilder.append(toString(node.getNext()));
        }

        return stringBuilder.toString();
    }
}
