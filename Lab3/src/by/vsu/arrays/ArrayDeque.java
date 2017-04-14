package by.vsu.arrays;

import by.vsu.abstractClasses.AbstractDeque;

public class ArrayDeque<T> extends AbstractDeque
{
    private T[] array;
    private int top;
    private int bottom;


    public ArrayDeque(int size)
    {
        if (size > 0)
        {
            array = (T[]) new Object[size];
        }
        else
        {
            array = (T[]) new Object[0];
        }

        top = 0;
        bottom = -1;
    }

    @Override
    public boolean isEmpty()
    {
        return top == bottom + 1;
    }

    @Override
    public T popBack()
    {
        if (!isEmpty())
        {
            return array[--top];
        }

        return null;
    }

    @Override
    public boolean pushBack(Object value)
    {
        if (top < array.length)
        {
            array[top++] = (T)value;

            return true;
        }

        return false;
    }

    @Override
    public T popFront()
    {
        if (!isEmpty())
        {
            return array[++bottom];
        }

        return null;
    }

    @Override
    public boolean pushFront(Object value)
    {
        if (bottom >= 0)
        {
            array[bottom--] = (T)value;

            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = bottom + 1; i < top; i++)
        {
            stringBuilder.append(array[i]);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}
