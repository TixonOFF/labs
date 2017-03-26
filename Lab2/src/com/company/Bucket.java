package com.company;

import java.util.ArrayList;

public class Bucket
{
    public static Double[] sort(Double[] array, int parts)
    {
        ArrayList <Double> res;

        if (parts >= 1 & parts <= array.length)//валидность
        {
            ArrayList <ArrayList <Double>> table = new ArrayList<>();//таблица со строками - черпаками
            ArrayList <Double> row;

            for (double i = 0; i < parts; i++)
            {
                row = new ArrayList<>();

                for (int j = 0; j < array.length; j++)
                {
                    if (array[j] >= (i / parts) & array[j] < ((i + 1) / parts))//если array[j] принадлежит отрезку
                    {
                        row.add(array[j]);
                    }
                }

                if (!row.isEmpty())
                {
                    table.add(row);
                }
            }

            for (int i = 0; i < table.size(); i++)
            {
                insertSort(table.get(i));//каждый черпак сортируем методом вставки
            }

            res = new ArrayList<>();

            for (int i = 0; i < table.size(); i++)
            {
                for (int j = 0; j < table.get(i).size(); j++)
                {
                    res.add(table.get(i).get(j));// добавляем последовательно все элементы в res
                }
            }
        }
        else
        {
            System.out.println("Некорректный ввод");
            return null;
        }

        Double [] a = new Double[0];
        return res.toArray(a);
    }

    public static void insertSort(ArrayList <Double> array)//сортировка вставками
    {
        int n = array.size();

        for(int i = 1; i < n; i++)
        {
            for (int j = i; j > 0 && array.get(j - 1) > array.get(j); j--) // пока j>0 и элемент j-1 > j
            {
                swap(array, j - 1, j);
            }
        }
    }

    public static void swap(ArrayList <Double> a, int i, int j)
    {
        double swap = a.get(i);
        a.set(i, a.get(j));
        a.set(j, swap);
    }
}
