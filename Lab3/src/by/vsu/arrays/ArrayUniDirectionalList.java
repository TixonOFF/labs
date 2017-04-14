package by.vsu.arrays;

import by.vsu.abstractClasses.AbstractUniDirectionalList;

public class ArrayUniDirectionalList<T> extends AbstractUniDirectionalList
{
    private T[] array;
    private Integer[] pointers;
    private int pointer;
    private int head;


    public ArrayUniDirectionalList(int size)
    {
        if (size > 0)
        {
            array = (T[]) new Object[size];
            pointers = new Integer[size];
        }
        else
        {
            array = (T[]) new Object[0];
            pointers = new Integer[0];
        }

        pointer = 0;
        head = 0;
    }

    @Override
    public boolean isEmpty()
    {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public T pop()
    {
        decrementPointer();

        return popAfter();
    }

    @Override
    public T popAfter()
    {
        T res = null;

        if (!isEmpty())
        {
            incrementPointer();
            int pos = pointer;
            decrementPointer();

            if (pos == head)
            {
                head = pointers[head];
            }

            pointers[pointer] = pointers[pos];
            pointers[pos] = null;

            res = array[pos];
            array[pos] = null;
        }

        return res;
    }

    @Override
    public boolean pushBefore(Object value)
    {
        boolean res;

        if (pointer == head)
        {
            decrementPointer();
            res = pushAfter(value);

            head = pointer;
        }
        else
        {
            decrementPointer();
            res = pushAfter(value);
        }

        return res;
    }

    @Override
    public boolean pushAfter(Object value)
    {
        if (isEmpty())
        {
            if (array.length > 0)
            {
                array[0] = (T) value;
                pointers[0] = 0;

                head = 0;
                pointer = 0;

                return true;
            }

            return false;
        }

        boolean isFree = false;
        int pos = 0;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == null)
            {
                isFree = true;
                pos = i;
                array[i] = (T)value;
                break;
            }
        }

        if (isFree)
        {
            int next = pointers[pointer];
            pointers[pointer] = pos;
            pointers[pos] = next;

            pointer = pos;//

            return true;
        }

        return false;
    }

    private void decrementPointer()
    {
        if (!isEmpty())
        {
            for (int i = 0; i < array.length; i++)
            {
                if (pointers[i] != null)
                {
                    if (pointers[i] == pointer)
                    {
                        pointer = i;
                        return;
                    }
                }
            }
        }
    }

    private void incrementPointer()
    {
        if (!isEmpty())
        {
            pointer = pointers[pointer];
        }
    }

    @Override
    public T popBack()
    {
        pointer = head;
        decrementPointer();

        T res = pop();

        decrementPointer();

        return res;
    }

    @Override
    public T popFront()
    {
        pointer = head;
        decrementPointer();
        head = pointer;

        T res = popAfter();

        incrementPointer();
        head = pointer;

        return res;
    }

    @Override
    public boolean pushBack(Object value)
    {
        pointer = head;
        decrementPointer();

        boolean res = pushAfter(value);

        pointer = head;
        decrementPointer();

        return res;
    }

    @Override
    public boolean pushFront(Object value)
    {
        pointer = head;
        boolean res = pushBefore(value);
        head = pointers[pointer];
        pointer = head;

        return res;
    }

    @Override
    public T next()
    {
        T res = array[pointer];
        incrementPointer();

        return res;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < array.length; i++)
        {
            stringBuilder.append(array[i]);
            stringBuilder.append(" ");
        }

        stringBuilder.append("\n");

        for (int i = 0; i < array.length; i++)
        {
            stringBuilder.append(pointers[i]);
            stringBuilder.append(" ");
        }

        stringBuilder.append("\n");
        stringBuilder.append(pointer);
        stringBuilder.append("\n");
        stringBuilder.append(head);

        return stringBuilder.toString();
    }
}
