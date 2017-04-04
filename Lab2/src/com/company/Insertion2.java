package com.company;

public class Insertion2 <T extends Comparable>
{
    public void sort(T[] array)
    {
        int n = array.length;
        int L, R, m;
        T x;

        for (int i = 1; i < n; i++)
        {
            x = array[i];//запомнили текущий элемент
            L = 0;
            R = i;//установили границы

            while (L < R)//сужаем границы
            {
                m = (L + R) / 2;//середина

                if (array[m].compareTo(x) < 0)
                {
                    L = m + 1;
                }
                else
                {
                    R = m;
                }
            }

            for (int j = i; j >= R + 1; j--)//сдвигаем элемент
            {
                array[j] = array[j - 1];
            }

            array[R] = x;
        }
    }
}
