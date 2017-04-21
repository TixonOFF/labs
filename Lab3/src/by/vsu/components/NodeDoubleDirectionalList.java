package by.vsu.components;

import by.vsu.abstractClasses.AbstractDoubleDirectionalList;
import by.vsu.abstractClasses.AbstractUniDirectionalList;
import by.vsu.entity.DoubleNode;
import by.vsu.entity.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NodeDoubleDirectionalList<T> extends AbstractDoubleDirectionalList
{
    private DoubleNode<T> head = null;
    private DoubleNode<T> tail = null;
    private DoubleNode<T> pointer = null;


    @Override
    public T nextValue()
    {
        if (!isEmpty())
        {
            T res = (T)pointer.getValue();

            if (pointer.getNext() == null)
            {
                pointer = head;
            }
            else
            {
                pointer = (DoubleNode<T>)pointer.getNext();
            }

            return res;
        }

        return null;
    }

    @Nullable
    private DoubleNode<T> next()
    {
        if (!isEmpty())
        {
            if (pointer.getNext() == null)
            {
                return head;
            }
            else
            {
                return (DoubleNode<T>)pointer.getNext();
            }
        }

        return null;
    }

    @Override
    public T previousValue()
    {
        if (!isEmpty())
        {
            T res = (T)pointer.getValue();

            if (pointer.getPrevious() == null)
            {
                pointer = tail;
            }
            else
            {
                pointer = (DoubleNode<T>)pointer.getPrevious();
            }

            return res;
        }

        return null;
    }

    @Nullable
    private DoubleNode<T> previous()
    {
        if (!isEmpty())
        {
            if (pointer.getPrevious() == null)
            {
                pointer = tail;
            }
            else
            {
                pointer = (DoubleNode<T>)pointer.getPrevious();
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
                T value = (T)head.getValue();
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

    private T popBack(DoubleNode<T> node)
    {
        if (node.getNext() == tail)
        {
            T value = (T)tail.getValue();

            tail = node;
            node.setNext(null);

            pointer = tail;

            return value;
        }

        return popBack((DoubleNode<T>)node.getNext());
    }

    @Override
    public T popFront()
    {
        if (!isEmpty())
        {
            T res = (T)head.getValue();

            head = (DoubleNode<T>)head.getNext();
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
            head = new DoubleNode<>((T)value);
            tail = head;
            pointer = head;
        }
        else
        {
            tail.setNext(new DoubleNode<>((T)value));
            tail = (DoubleNode<T>)tail.getNext();
            pointer = tail;
        }

        return true;
    }

    @Override
    public boolean pushFront(Object value)
    {
        if (isEmpty())
        {
            head = new DoubleNode<>((T)value);
            tail = head;
            pointer = head;
        }
        else
        {
            Node<T> node = head;
            head = new DoubleNode<>((T)value);
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
            T res = (T)pointer.getValue();

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
            head = new DoubleNode<>((T)value);
            tail = head;
            pointer = head;
        }
        else
        {
            Node<T> node = next();

            pointer.setNext(new DoubleNode<>((T)value));
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
