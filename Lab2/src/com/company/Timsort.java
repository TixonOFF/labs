package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Timsort
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
        Timsort.swapCount = swapCount;
    }

    public static void setCompareCount(long compareCount)
    {
        Timsort.compareCount = compareCount;
    }

    public static void sort(Integer[] array)
    {//сложность n^2 * log(n)
        //По специальному алгоритму входной массив разделяется на подмассивы.
        //Каждый подмассив сортируется сортировкой вставками.
        //Отсортированные подмассивы собираются в единый массив с помощью сортировки слиянием.

        int N = array.length;
        int minrun = getMinrun(N);//длина каждого подмассива

        ArrayList <Range> minrunStack = new ArrayList<>();//список границ подмассивов

        for (int i = 0; i * minrun < N; i++)
        {
            int currentRunFirstIndex = i * minrun;//индекс начала подмассива

            int currentRunLastIndex = currentRunFirstIndex + minrun - 1;//индекс конца подмассива
            if (currentRunLastIndex + minrun > N - 1)
            {
                currentRunLastIndex = N - 1;//последний подмассив дополним до конца исходного массива

                setCompareCount(getCompareCount() + 1);
            }

            sortPart(array, currentRunFirstIndex, currentRunLastIndex);//сортируем подмассив методом вставки

            minrunStack.add(new Range(currentRunFirstIndex, currentRunLastIndex));//добавляем в список границ подмассивов

            setCompareCount(getCompareCount() + 1);
        }

        for (int i = 1; i < minrunStack.size(); i++)
        {
            merge(array, minrunStack.get(i).start, minrunStack.get(i).end);

            setCompareCount(getCompareCount() + 1);
        }
    }

    public static void sortPart(Integer[] array, int start, int end)//сортировка вставками
    {//n = start - end, сложность - n^2
        for(int i = start + 1; i <= end; i++)
        {
            for (int j = i; j > 0 && array[j - 1] > array[j]; j--) // пока j>0 и элемент j-1 > j
            {
                swap(array, j - 1, j);
            }
        }
    }

    private static int getMinrun(int n)//длина подмассивов
    {//сложность - log(n)
        //берутся старшие 6 бит из N и добавляется единица, если в оставшихся младших битах есть хотя бы один ненулевой
        int r = 0;

        while (n >= 64)
        {
            r |= n & 1;
            n >>= 1;

            setCompareCount(getCompareCount() + 1);
        }

        return n + r;
    }

    public static void merge(Integer[] array, int start, int end)//слияние подмассивов
    {//n = end + 1, сложность - n * log(n)
        Integer[] left = Arrays.copyOfRange(array, 0, start);
        Integer[] right = Arrays.copyOfRange(array, start, end + 1);

        int leftIndex = 0;
        int rightIndex = 0;//актуальные индексы для массивов

        for (int i = 0; i <= end; i++)//пробегаем по всем индексам результата
        {
            if (left[leftIndex] > right[rightIndex])//находим минимальный актуальный элемент и добавляем его в результат
            {
                array[i] = right[rightIndex];
                rightIndex++;
            }
            else
            {
                array[i] = left[leftIndex];
                leftIndex++;
            }

            if (leftIndex == left.length)//если закончился первый массив
            {
                while (rightIndex != right.length)//добавляем все оставшиеся элементы второго в результат
                {
                    array[i + 1] = right[rightIndex];
                    rightIndex++;
                    i++;

                    setCompareCount(getCompareCount() + 1);
                }

                return;
            }

            if (rightIndex == right.length)//если закончился второй массив
            {
                while (leftIndex != left.length)//добавляем все оставшиеся элементы первого в результат
                {
                    array[i + 1] = left[leftIndex];
                    leftIndex++;
                    i++;

                    setCompareCount(getCompareCount() + 1);
                }

                return;
            }

            setCompareCount(getCompareCount() + 4);
        }
    }

    public static void swap(Integer[] a, int i, int j)//3
    {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;

        setSwapCount(getSwapCount() + 1);
    }

    static class Range
    {
        int start;
        int end;

        public Range(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
    }
}
