package by.vsu.Arrays;

import by.vsu.Abstract.DoubleDirectionalList;

import java.util.Arrays;

public class ADoubleDirectionalList<T extends Comparable> extends DoubleDirectionalList
{
    protected T[] array;
    protected int pointer;


    public ADoubleDirectionalList(T[] array)
    {
        this.array = array;
        pointer = 0;
    }

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
        return array.length;
    }

    @Override
    public T get()
    {
        return array[getPointer()];
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
        Comparable[] res = new Comparable[size() + 1];

        res[0] = value;

        end();
        for (int i = 1; i < size() + 1; i++)
        {
            res[i] = next();
        }

        array = (T[])res;
        begin();
    }

    @Override
    public void pushBack(Comparable value)
    {
        Comparable[] res = new Comparable[size() + 1];

        end();
        for (int i = 0; i < size(); i++)
        {
            res[i] = next();
        }
        res[res.length - 1] = value;

        array = (T[])res;
        end();
    }

    @Override
    public void pushAfter(Comparable value)
    {
        int pos = getPointer();

        Comparable[] res = new Comparable[size() + 1];

        end();
        for (int i = 0; i <= pos; i++)
        {
            res[i] = next();
        }
        res[pos + 1] = value;
        for (int i = pos + 2; i < res.length; i++)
        {
            res[i] = next();
        }

        array = (T[])res;
        setPointer(pos + 1);
    }

    @Override
    public void pushBefore(Comparable value)
    {
        int pos = getPointer();

        Comparable[] res = new Comparable[size() + 1];

        end();
        for (int i = 0; i < pos; i++)
        {
            res[i] = next();
        }
        res[pos] = value;
        for (int i = pos + 1; i < res.length; i++)
        {
            res[i] = next();
        }

        array = (T[])res;
        setPointer(pos);
    }

    @Override
    public void remove(int index)
    {
        if (size() > 0 && index >= 0 && index < size())
        {
            Comparable[] res = new Comparable[size() - 1];

            end();
            for (int i = 0; i < index; i++)
            {
                res[i] = next();
            }

            next();

            for (int i = index; i < size() - 1; i++)
            {
                res[i] = next();
            }

            array = (T[])res;
            begin();
        }
    }

    public void addList(ASingleDirectionalList list)
    {
        Comparable[] res = new Comparable[size() + list.size()];

        end();
        for (int i = 0; i < size(); i++)
        {
            res[i] = next();
        }

        list.end();
        for (int i = size(); i < res.length; i++)
        {
            res[i] = list.next();
        }

        array = (T[])res;
        begin();
    }

    public ASingleDirectionalList detachList(int pos)
    {
        if (pos > 0 && pos < size())
        {
            T[] list = array.clone();

            array = Arrays.copyOfRange(array, 0, pos);
            begin();

            return new ASingleDirectionalList(Arrays.copyOfRange(list, pos, list.length).clone());
        }

        return null;
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
            T max = array[0];

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
