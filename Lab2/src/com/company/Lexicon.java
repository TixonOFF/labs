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

            res = sortByPosition(res, position);//сортируем все элементы res по символу с индексом position

            position--;
        }

        String[] resArray = res.toArray(array);

        System.arraycopy(resArray, 0, array, 0, array.length);//копируем resArray в array
    }

    public static ArrayList<String> sortByPosition(ArrayList <String> array, int position)//сортировка array по символу с индексом position
    {
        ArrayList <ArrayList <String>> table = new ArrayList<>();
        for (int i = 0; i < 128; i++)
        {
            table.add(new ArrayList<>());
        }

        for (int i = 0; i < array.size(); i++)
        {
            table.get(Character.toLowerCase(array.get(i).charAt(position))).add(array.get(i));//добавляем слово в колонку с индексом array[i][position]
        }

        ArrayList <String> a = new ArrayList<>();

        for (int i = 0; i < table.size(); i++)
        {
            if (!table.get(i).isEmpty())
            {
                for (int j = table.get(i).size() - 1; j >= 0; j--)
                {
                    a.add(table.get(i).get(j));//последовательно все забираем из таблицы
                }
            }
        }

        return a;
    }
}
