package com.company;

import java.io.*;
import java.util.Scanner;

public class Merger
{
    public static void sort(String fileName)
    {
        Scanner scanner;

        try
        {
            scanner = new Scanner(new FileReader(fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Файл не найден");
            return;
        }

        Integer[] array = new Integer[2];
        Integer index = 0;
        File file;
        BufferedWriter bufferedWriter;

        while (scanner.hasNextInt())//пока есть еще int
        {
            array[0] = scanner.nextInt();//считываем
            file = new File(index.toString() + ".txt");

            if (scanner.hasNextInt())//если есть еще
            {
                array[1] = scanner.nextInt();//считываем

                try
                {
                    sortArray(array);//сортируем массив с двумя элементами

                    bufferedWriter = new BufferedWriter(new FileWriter(file.getName()));
                    bufferedWriter.write(array[0].toString() + " " + array[1].toString());//записали массив в новый файл
                    bufferedWriter.close();
                }
                catch (IOException e)
                {
                    System.out.println("Не удалось создать файл");
                    return;
                }
            }
            else
            {
                try
                {
                    bufferedWriter = new BufferedWriter(new FileWriter(file.getName()));
                    bufferedWriter.write(array[0].toString());//записали 1 int в файл (последний файл)
                    bufferedWriter.close();
                }
                catch (IOException e)
                {
                    System.out.println("Не удалось создать файл");
                    return;
                }
            }

            index++;
        }

        try
        {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));

            Integer min;

            while (true)
            {
                min = getMinValue(index - 1);//нашли минимальный элемент из начала всех файлов

                if (min != null)
                {
                    bufferedWriter.write(min.toString() + " ");//записали его
                }
                else
                {
                    bufferedWriter.close();
                    break;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Не удалось записать файл");
            return;
        }
    }

    public static void sortArray(Integer[] array)//сортировка массива с двумя элементами
    {
        if (array[0] > array[1])
        {
            int value = array[0];
            array[0] = array[1];
            array[1] = value;
        }
    }

    public static Integer getMinValue(int maxFileIndex)//получаем минимальный элемент из начала всех файлов
    {
        Integer index = null;
        Integer value = null;
        Scanner scanner;
        int next;

        for (Integer i = 0; i <= maxFileIndex; i++)//пробегаем по всем файлам
        {
            try
            {
                scanner = new Scanner(new FileReader(i.toString() + ".txt"));
            }
            catch (FileNotFoundException e)
            {
                continue;
            }


            if (scanner.hasNextInt())//если в файле есть int
            {
                next = scanner.nextInt();//запоминаем его

                if (value == null)//если первый раз считали значение
                {
                    value = next;
                    index = i;
                }
                else
                {
                    if (value > next)//сравниваем со считанным ранее из другого файла
                    {
                        value = next;
                        index = i;
                    }
                }
            }
        }

        try
        {
            scanner = new Scanner(new FileReader(index.toString() + ".txt"));//для файла с минимальным значением

            scanner.nextInt();//пропускаем его
            Integer num;

            if (scanner.hasNextInt())//если есть еще
            {
                num = scanner.nextInt();//считываем второй int

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(index.toString() + ".txt"));
                bufferedWriter.write(num.toString());//перезаписываем второй int в начало
                bufferedWriter.close();
            }
            else
            {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(index.toString() + ".txt"));
                bufferedWriter.write("");//если больше нет int, то оставляем файл пустым
                bufferedWriter.close();
            }

            scanner.close();
        }
        catch (Exception e)
        {
            return null;
        }

        return value;
    }
}
