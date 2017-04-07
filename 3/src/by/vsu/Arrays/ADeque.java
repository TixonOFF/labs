package by.vsu.Arrays;

import by.vsu.Abstract.Deque;

public class ADeque<T extends Comparable> extends Deque
{
    protected T[] array;


    public ADeque(T[] array)
    {
        this.array = array;
    }

    @Override
    public void pushFront(Comparable value)
    {
        Comparable[] res = new Comparable[size() + 1];

        res[0] = value;

        for (int i = 1; i <= size(); i++)
        {
            res[i] = array[i - 1];
        }

        array = (T[])res;
    }

    @Override
    public Comparable popBack()
    {
        Comparable element = array[size() - 1];

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
    public Comparable max()
    {
        if (size() > 0)
        {
            Comparable max = array[0];

            for (int i = 1; i < size(); i++)
            {
                if (max.compareTo(array[i]) < 0)
                {
                    max = array[i];
                }
            }

            return max;
        }

        return null;
    }

    @Override
    public Comparable min()
    {
        if (size() > 0)
        {
            Comparable min = array[0];

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
        Comparable element = array[0];

        Comparable[] res = new Comparable[size() - 1];

        for (int i = 1; i < size(); i++)
        {
            res[i - 1] = array[i];
        }

        array = (T[])res;

        return element;
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
