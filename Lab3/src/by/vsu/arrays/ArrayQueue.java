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
            array = (T[]) new Object[-size];
        }

        top = -1;
        bottom = -1;
    }

    @Override
    public boolean isEmpty()
    {
        return top == -1;
    }

    @Override
    public boolean pushBack(Object value)
    {
        if (isEmpty())
        {
            if (array.length > 0)
            {
                array[0] = (T)value;
                top = 0;
                bottom = -1;

                return true;
            }
        }

        if (top >= bottom)//если массив без дыры
        {
            if (top + 1 == array.length)
            {
                if (bottom > 0)
                {
                    array[0] = (T)value;
                    top = 0;

                    return true;
                }
            }
            else
            {
                array[++top] = (T)value;

                return true;
            }
        }
        else
        {
            if (top + 1 < bottom)
            {
                array[++top] = (T)value;

                return true;
            }
        }

        return false;
    }

    @Override
    public T popFront()
    {
        if (!isEmpty())
        {
            if (top >= bottom)
            {
                if (bottom == -1)
                {
                    bottom = 1;

                    if (top == 0)
                    {
                        top = -1;
                    }

                    return array[0];
                }
                else
                {
                    if (top == bottom)
                    {
                        int res = top;
                        top = -1;//после удаления будет пусто
                        bottom = -1;

                        return array[res];
                    }

                    return array[bottom++];
                }
            }
            else
            {
                if (bottom + 1 == array.length)
                {
                    bottom = 0;

                    return array[array.length - 1];
                }

                return array[bottom++];
            }
        }

        return null;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (!isEmpty())
        {
            if (top >= bottom)
            {
                for (int i = Math.max(bottom, 0); i <= top; i++)
                {
                    stringBuilder.append(array[i]);
                    stringBuilder.append(" ");
                }
            }
            else
            {
                int i = bottom;

                for (; i < array.length; i++)
                {
                    stringBuilder.append(array[i]);
                    stringBuilder.append(" ");
                }

                for (i = 0; i <= top; i++)
                {
                    stringBuilder.append(array[i]);
                    stringBuilder.append(" ");
                }
            }
        }

        return stringBuilder.toString();
    }
}
