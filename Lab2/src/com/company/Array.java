package com.company;

public class Array
{
    public static boolean containsElements(Integer[] array, int x)
    {
        int n = array.length;
        quickSort(array, 0, n - 1);

        for (int i = 0; i < n; i++)
        {
            int key = x - array[i];

            if (array[0] <= key & array[n - 1] >= key & binarySearch(array, key, i, 0, n - 1))
            {
                return true;
            }
        }

        return false;
    }

    public static void quickSort(Integer[] array, int L, int R)
    {
        int x = array[(L + R) / 2];
        int i = L;
        int j = R;

        do
        {
            while (array[i] < x)
            {
                i++;
            }

            while (array[j] > x)
            {
                j--;
            }

            if (i <= j)
            {
                int swap = array[i];
                array[i] = array[j];
                array[j] = swap;

                i++;
                j--;
            }
        }
        while (j >= i);

        if (L < j)
        {
            quickSort(array, L, j);
        }

        if (i < R)
        {
            quickSort(array, i, R);
        }
    }

    public static boolean binarySearch(Integer[] array, int key, int index, int L, int R)
    {
        if (L == R)
        {
            if (L != index)
            {
                return array[L] == key;
            }
            else
            {
                return false;
            }
        }
        else
        {
            int middle = (L + R) / 2;

            return binarySearch(array, key, index, L, middle) | binarySearch(array, key, index, middle + 1, R);
        }
    }
}
