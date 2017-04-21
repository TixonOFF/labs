package by.vsu.arrays;

import by.vsu.abstractClasses.AbstractDoubleDirectionalList;

public class ArrayDoubleDirectionalList<T> extends AbstractDoubleDirectionalList
{
    private T[] array;
    private Integer[] nextPointers;
    private Integer[] previousPointers;
    private int pointer;
    private int head;
    private int tail;

    public ArrayDoubleDirectionalList(int size)
    {
        if (size > 0)
        {
            array = (T[]) new Object[size];
            nextPointers = new Integer[size];
            previousPointers = new Integer[size];
        }
        else
        {
            array = (T[]) new Object[-size];
            nextPointers = new Integer[-size];
            previousPointers = new Integer[-size];
        }

        pointer = 0;
        head = 0;
        tail = 0;
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

            if (pos == head)//если нужно достать элемент из начала списка
            {
                head = nextPointers[head];//сдвигаем начало вперёд
            }

            if (pos == tail)
            {
                tail = pointer;
            }

            nextPointers[pointer] = nextPointers[pos];//устанавливаем сслыки на следующий и предыдущий элементы
            previousPointers[nextPointers[pos]] = pointer;

            nextPointers[pos] = null;//удаляем следующий за текущим
            previousPointers[pos] = null;

            res = array[pos];//запоминаем значение которое удаляем
            array[pos] = null;//удаляем следующий за текущим
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
                nextPointers[0] = 0;

                head = 0;
                pointer = 0;

                return true;
            }

            return false;
        }

        boolean isFree = false;
        int pos = 0;

        for (int i = 0; i < array.length; i++)//есть ли свободное место
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
            int next = nextPointers[pointer];//запомнили где следующий элемент

            nextPointers[pointer] = pos;//связали первый с вставляемым
            nextPointers[pos] = next;//связали вставляемый со следующим

            previousPointers[pos] = pointer;
            previousPointers[nextPointers[pos]] = pos;

            if (pointer == tail)
            {
                tail = pos;
            }

            pointer = pos;//переместили указатель

            return true;
        }

        return false;
    }

    private void decrementPointer()
    {
        if (!isEmpty())
        {
            pointer = previousPointers[pointer];
        }
    }

    private void incrementPointer()
    {
        if (!isEmpty())
        {
            pointer = nextPointers[pointer];
        }
    }

    @Override
    public T popBack()
    {
        pointer = tail;

        T res = pop();

        return res;
    }

    @Override
    public T popFront()
    {
        pointer = head;
        decrementPointer();
        head = pointer;

        T res = popAfter();

        incrementPointer();//возвращаем указатель в начало
        head = pointer;

        return res;
    }

    @Override
    public boolean pushBack(Object value)
    {
        pointer = tail;

        boolean res = pushAfter(value);

        pointer = tail;//перемещаем указатель в конец

        return res;
    }

    @Override
    public boolean pushFront(Object value)
    {
        pointer = head;
        return pushBefore(value);
    }

    @Override
    public T nextValue()
    {
        T res = array[pointer];
        incrementPointer();

        return res;
    }

    @Override
    public Object previousValue()
    {
        T res = array[pointer];
        decrementPointer();

        return res;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        int pos = pointer;

        pointer = head;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                stringBuilder.append(nextValue());
                stringBuilder.append(" ");
            }
        }

        pointer = pos;

        return stringBuilder.toString();
    }
}
