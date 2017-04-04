package com.company;

public class Bubble <T extends Comparable>
{
    public void sort(T[] array)
    {
        int n = array.length;

        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - 1 - i; j++)
            {
                if (array[j].compareTo(array[j + 1]) > 0)
                {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public void swap(T[] a, int i, int j)
    {
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
