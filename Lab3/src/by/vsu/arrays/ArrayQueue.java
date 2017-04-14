package by.vsu.arrays;

import by.vsu.abstractClasses.AbstractQueue;

public class ArrayQueue<T> extends AbstractQueue
{
    private T[] array;
    private int top;
    private int bottom;


    public ArrayQueue(int size)
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
