package com.company;

public class Printer <T>
{
    public void printArray(T[] array)
    {
        for (T obj : array)
        {
            System.out.print(obj + " ");
        }

        System.out.println();
    }
}
