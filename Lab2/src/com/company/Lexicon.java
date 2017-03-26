package com.company;

import java.util.ArrayList;

public class Lexicon
{
    public static void sort(String[] array)
    {
        int maxLength = 0;

        for (String anArray : array)//находим длину наибольшего слова
        {
            if (anArray.length() > maxLength)
            {
                maxLength = anArray.length();
            }
        }

        ArrayList <ArrayList <String>> table = new ArrayList<>();//в каждой строке таблицы находятся слова одной длины(в первой строке самые длинные слова)
        ArrayList <String> row;

        for (int i = maxLength; i > 0; i--)
        {
            row = new ArrayList<>();

            for (String anArray : array)
            {
                if (anArray.length() == i)
                {
                    row.add(anArray);
                }
            }

            if (!row.isEmpty())
            {
                table.add(row);
            }
        }

        ArrayList <String> res = new ArrayList<>();

        int position = maxLength - 1;

        for (int i = 0; i < table.size();)
        {
            if (table.get(i).get(0).length() == position + 1)// если длина слов строки = position + 1
            {
                for (int j = 0; j < table.get(i).size(); j++)// добавляем их в начало res
                {
                    res.add(0, table.get(i).get(j));
                }

                i++;
            }

            sortByPosition(res, position);//сортируем все элементы res по символу с индексом position

            position--;
        }

        String[] resArray = res.toArray(array);

        System.arraycopy(resArray, 0, array, 0, array.length);//копируем resArray в array
    }

    public static void sortByPosition(ArrayList <String> array, int position)//сортировка array по символу с индексом position(метод пузырька)
    {
        for (int i = 0; i < array.size() - 1; i++)
        {
            for (int j = 0; j < array.size() - 1 - i; j++)
            {
                Character c1 = array.get(j).charAt(position);
                Character c2 = array.get(j + 1).charAt(position);

                if (c1.toString().compareToIgnoreCase(c2.toString()) > 0)
                {
                    String str = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, str);
                }
            }
        }
    }
}
