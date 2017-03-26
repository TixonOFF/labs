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
    {
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
            }

            sortInsertionPart(array, currentRunFirstIndex, currentRunLastIndex);//сортируем подмассив методом вставки

            minrunStack.add(new Range(currentRunFirstIndex, currentRunLastIndex));//добавляем в список границ подмассивов
        }

        for (int i = 1; i < minrunStack.size(); i++)
        {
            merge(array, minrunStack.get(i).start, minrunStack.get(i).end);
        }
    }

    public static void sortInsertionPart(Integer[] array, int start, int end)//сортировка вставками
    {
        for (int i = start + 1; i <= end; i++)
        {
            for (int j = start; j < i; j++)
            {
                if (array[i] < array[j])
                {
                    int value = array[i];

                    for (int k = i; k > j; k--)
                    {
                        array[k] = array[k - 1];
                    }

                    array[j] = value;
                }
            }
        }
    }

    private static int getMinrun(int n)//длина подмассивов
    {
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
    {
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
                    array[i] = right[rightIndex];
                    rightIndex++;
                    i++;
                }

                return;
            }

            if (rightIndex == right.length)//если закончился второй массив
            {
                while (leftIndex != left.length)//добавляем все оставшиеся элементы первого в результат
                {
                    array[i] = left[leftIndex];
                    leftIndex++;
                    i++;
                }

                return;
            }
        }
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
