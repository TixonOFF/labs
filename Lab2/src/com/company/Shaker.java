package com.company;

public class Shaker
{
    private static long swapCount = 0;
    private static long compareCount = 0;

    public static long getSwapCount()
    {
        return swapCount;
    }

    public static long getCompareCount()
    {
        return compareCount;
    }

    public static void setSwapCount(long swapCount)
    {
        Shaker.swapCount = swapCount;
    }

    public static void setCompareCount(long compareCount)
    {
        Shaker.compareCount = compareCount;
    }

    public static void sort(Integer[] array)//n^2
    {
        int left = 0;
        int right = array.length - 1;

        while (left < right)
        {
            for (int i = left; i < right; i++)//максимальный элемент из промежутка [left; right] попадет в позицию right
            {
                if(array[i] > array[i + 1])
                {
                    swap(array, i, i + 1);
                }

                setCompareCount(getCompareCount() + 2);
            }
            right--;

            for (int i = right; i > left ; i--)//минимальный элемент из промежутка [left; right] попадет в позицию left
            {
                if(array[i] < array[i - 1])
                {
                    swap(array, i, i - 1);
                }

                setCompareCount(getCompareCount() + 2);
            }
            left++;

            setCompareCount(getCompareCount() + 1);
        }
    }

    public static void swap(Integer[] a, int i, int j)
    {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;

        setSwapCount(getSwapCount() + 1);
    }
}
