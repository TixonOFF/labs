package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Barrier <T extends Comparable>
{
    public T[] sort(T[] a)
    {
        ArrayList <T> list = new ArrayList<T>(Arrays.asList(a));
        list.add(0, list.get(0));
        T[] array = list.toArray(a);

        int n = array.length;

        for (int i = 0; i < n - 1; i++)
        {
            array[i + 1] = (T)a[i];
        }//создаём дополнительно пустой первый элемент

        T x;
        int j;

        for (int i = 1; i < n; i++)
        {
            x = array[i];//запоминаем элемент
            array[0] = x;//ставим его в начало
            j = i;

            while (x.compareTo(array[j - 1]) < 0)//пока x < array[j - 1]
            {
                array[j] = array[j - 1];//сдвигаем
                j--;
            }

            array[j] = x;//в итоговую позицию записываем x
        }


        list = new ArrayList<T>(Arrays.asList(array));
        list.remove(0);
        array = list.toArray(a);//удаляем первый элемент

        return array;
    }

    public T[] recursionSort(T[] a)
    {
        ArrayList <T> list = new ArrayList<T>(Arrays.asList(a));
        list.add(0, list.get(0));
        T[] array = list.toArray(a);

        int n = array.length;

        for (int i = 0; i < n - 1; i++)
        {
            array[i + 1] = (T)a[i];
        }//создаём дополнительно пустой первый элемент

        T x;
        int j;

        recSort(array, n - 1);

        list = new ArrayList<T>(Arrays.asList(array));
        list.remove(0);
        array = list.toArray(a);//удаляем первый элемент

        return array;
    }

    public void recSort(T[] array, int index)
    {
        if (index != 1)
        {
            recSort(array, index - 1);
        }


        T x = array[index];//запоминаем элемент
        array[0] = x;//ставим его в начало
        int j = index;

        while (x.compareTo(array[j - 1]) < 0)//пока x < array[j - 1]
        {
            array[j] = array[j - 1];//сдвигаем
            j--;
        }

        array[j] = x;//в итоговую позицию записываем x
    }
}
