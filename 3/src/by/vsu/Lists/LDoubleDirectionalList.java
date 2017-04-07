package by.vsu.Lists;

import by.vsu.Abstract.DoubleDirectionalList;
import by.vsu.Abstract.SingleDirectionalList;

import java.util.ArrayList;

public class LDoubleDirectionalList<T extends Comparable> extends DoubleDirectionalList
{
    protected ArrayList<T> array;
    protected int pointer;


    public LDoubleDirectionalList(ArrayList<T> array)
    {
        this.array = array;
        pointer = 0;
    }

    @Override
    public T previous()
    {
        if (--pointer == -1)
        {
            pointer = size() - 1;
        }

        return (T)get();
    }

    private void setPointer(int pointer)
    {
        this.pointer = pointer;
    }

    @Override
    public int getPointer()
    {
        return pointer;
    }

    @Override
    public int size()
    {
        return array.size();
    }

    @Override
    public T get()
    {
        return array.get(getPointer());
    }

    @Override
    public T next()
    {
        if (++pointer == size())
        {
            setPointer(0);
        }

        return get();
    }

    @Override
    public void begin()
    {
        setPointer(0);
    }

    @Override
    public void end()
    {
        setPointer(size() - 1);
    }

    @Override
    public void pushFront(Comparable value)
    {
        array.set(0, (T)value);
        begin();
    }

    @Override
    public void pushBack(Comparable value)
    {
        array.add((T)value);
        end();
    }

    @Override
    public void pushAfter(Comparable value)
    {
        int pos = getPointer();

        array.set(pos + 1, (T)value);

        setPointer(pos + 1);
    }

    @Override
    public void pushBefore(Comparable value)
    {
        int pos = getPointer();

        array.set(pos, (T)value);

        setPointer(pos);
    }

    @Override
    public void remove(int index)
    {
        if (size() > 0 && index >= 0 && index < size())
        {
            array.remove(index);
            begin();
        }
    }

    public void addList(LDoubleDirectionalList list)
    {
        array.addAll(list.array);
        begin();
    }

    public LDoubleDirectionalList detachList(int pos)
    {
        if (pos > 0 && pos < size())
        {
            ArrayList<T> list1 = new ArrayList<>();

            for (int i = 0; i < pos; i++)
            {
                list1.add(array.get(i));
            }

            ArrayList<T> list2 = new ArrayList<>();

            for (int i = pos; i < size(); i++)
            {
                list2.add(array.get(i));
            }

            array = list1;
            begin();

            return new LDoubleDirectionalList(list2);
        }

        return null;
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
