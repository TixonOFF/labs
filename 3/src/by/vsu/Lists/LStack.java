package by.vsu.Lists;

import by.vsu.Abstract.Stack;

import java.util.ArrayList;

public class LStack<T extends Comparable> extends Stack
{
    protected ArrayList<T> array;


    public LStack(ArrayList<T> array)
    {
        this.array = array;
    }

    @Override
    public Comparable get()
    {
        return (Comparable) array.get(size() - 1);
    }

    @Override
    public int size()
    {
        return array.size();
    }

    @Override
    public void push(Comparable value)
    {
        array.add((T)value);
    }

    @Override
    public Comparable pop()
    {
        Comparable element = array.get(size() - 1);

        array.remove(size() - 1);

        return element;
    }

    @Override
    public int indexOf(Comparable key)
    {
        return array.indexOf(key);
    }

    @Override
    public T max()
    {
        if (size() > 0)
        {
            T max = array.get(0);

            for (int i = 1; i < size(); i++)
            {
                if (max.compareTo(array.get(i)) < 0)
                {
                    max = array.get(i);
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
            T min = array.get(0);

            for (int i = 1; i < size(); i++)
            {
                if (min.compareTo(array.get(i)) > 0)
                {
                    min = array.get(i);
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
            stringBuilder.append(array.get(i));
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}
