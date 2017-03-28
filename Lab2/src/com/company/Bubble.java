package com.company;

public class Bubble
{
    public static void sort(Integer[] array)
    {
        int n = array.length;

        for (int i = 1; i < n - 1; i++)
        {
            for (int j = n - 1; j > i; j--)
            {
                if (array[j - 1] > array[j])
                {
                    swap(array, j, j - 1);
                }
            }
        }
    }

    public static void swap(Integer[] a, int i, int j)
    {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
