package com.company;

public class Insertion2
{
    public static void sort(Integer[] array)
    {
        int n = array.length;
        int L, R, x, m;

        for (int i = 1; i < n; i++)
        {
            x = array[i];
            L = 1;
            R = i;

            while (L < R)
            {
                m = (L + R) / 2;

                if (array[m] < x)
                {
                    L = m + 1;
                }
                else
                {
                    R = m;
                }
            }

            for (int j = i; j >= R + 1; j--)
            {
                array[j] = array[j - 1];
            }

            array[R] = x;
        }
    }
}
