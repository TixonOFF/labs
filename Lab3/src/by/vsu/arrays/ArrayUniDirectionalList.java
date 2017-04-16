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

            if (pos == head)//если нужно достать элемент из начала списка
            {
                head = pointers[head];//сдвигаем начало вперёд
            }

            pointers[pointer] = pointers[pos];//устанавливаем сслыку на следующий элемент
            pointers[pos] = null;//удаляем следующий за текущим

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
                pointers[0] = 0;

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
            int next = pointers[pointer];//запомнили где следующий элемент
            pointers[pointer] = pos;//связали первый с вставляемым
            pointers[pos] = next;//связали вставляемый со следующим

            pointer = pos;//переместили указатель

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
                    if (pointers[i] == pointer)//если указывает на текущий элемент
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
        pointer = head;
        decrementPointer();

        boolean res = pushAfter(value);

        pointer = head;
        decrementPointer();//перемещаем указатель в конец

        return res;
    }

    @Override
    public boolean pushFront(Object value)
    {
        pointer = head;
        return pushBefore(value);
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

        int pos = pointer;

        pointer = head;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                stringBuilder.append(next());
                stringBuilder.append(" ");
            }
        }

        pointer = pos;

        return stringBuilder.toString();
    }
}
