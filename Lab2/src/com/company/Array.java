package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Array
{
    public static boolean containsElements(Integer[] array, int x)
    {
        int n = array.length;
        ArrayList <Integer> list = new ArrayList<>(Arrays.asList(array));

        for (int i = 0; i < n; i++)
        {
            if ((list.indexOf(x - list.get(i)) != -1))//если нашли элемент = x - array[i]
            {
                return true;
            }
        }

        return false;
    }
}
