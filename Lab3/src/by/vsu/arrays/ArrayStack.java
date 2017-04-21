package by.vsu.arrays;

import by.vsu.abstractClasses.AbstractStack;

public class ArrayStack<T> extends AbstractStack
{
    private T[] array;
    private int top;


    public ArrayStack(int size)
    {
        if (size > 0)
        {
            array = (T[]) new Object[size];
        }
        else
        {
            array = (T[]) new Object[-size];
        }

        top = -1;
    }

    @Override
    public boolean isEmpty()
    {
        return top == -1;
    }

    @Override
    public T popBack()
    {
        if (!isEmpty())
        {
            return array[top--];
        }

        return null;
    }

    @Override
    public boolean pushBack(Object value)
    {
        if (top + 1 < array.length)
        {
            array[++top] = (T)value;

            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <= top; i++)
        {
            stringBuilder.append(array[i]);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}
