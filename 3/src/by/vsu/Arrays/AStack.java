package by.vsu.Arrays;

import by.vsu.Abstract.Stack;

public class AStack<T extends Comparable> extends Stack
{
    protected T[] array;


    public AStack(T[] array)
    {
        this.array = array;
    }

    @Override
    public Comparable get()
    {
        return array[size() - 1];
    }

    @Override
    public int size()
    {
        return array.length;
    }

    @Override
    public void push(Comparable value)
    {
        Comparable[] res = new Comparable[size() + 1];

        for (int i = 0; i < size(); i++)
        {
            res[i] = array[i];
        }
        res[res.length - 1] = value;

        array = (T[])res;
    }

    @Override
    public Comparable pop()
    {
        Comparable element = array[array.length - 1];

        Comparable[] res = new Comparable[size() - 1];

        for (int i = 0; i < size() - 1; i++)
        {
            res[i] = array[i];
        }

        array = (T[])res;

        return element;
    }

    @Override
    public int indexOf(Comparable key)
    {
        for (int i = 0; i < size(); i++)
        {
            if (array[i].equals(key))
            {
                return i;
            }
        }

        return -1;
    }

    @Override
    public T max()
    {
        if (size() > 0)
        {
            T max = (T)array[0];

            for (int i = 1; i < size(); i++)
            {
                if (max.compareTo(array[i]) < 0)
                {
                    max = (T)array[i];
                }
            }

            return max;
        }

        return null;
    }

    @Override
    public T min()
    {
        if (size() > 0)
        {
            T min = array[0];

            for (int i = 1; i < size(); i++)
            {
                if (min.compareTo(array[i]) > 0)
                {
                    min = array[i];
                }
            }

            return min;
        }

        return null;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size(); i++)
        {
            stringBuilder.append(array[i]);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}
