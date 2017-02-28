package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader
{
    private Scanner scanner;
    private Boolean sign = true;

    public Loader(String path)
    {
        try
        {
            scanner = new Scanner(new File(path));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Файл не найден");
        }
    }

    public Integer loadInt()
    {
        if (scanner.hasNext())
        {
            return (scanner.nextInt());
        }
        return null;
    }

    public Integer[] loadVector()
    {
        Integer[] vector = new Integer[0];

        while (scanner.hasNextLine())
        {
            String str = scanner.next();

            if (str.charAt(0) == '-')
            {
                sign = false;

                for (int i = 1; i < str.length(); i++)
                {
                    vector = Task2.add(vector, Character.getNumericValue(str.charAt(i)));
                }
                return vector;
            }

            for (int i = 0; i < str.length(); i++)
            {
                vector = Task2.add(vector, Character.getNumericValue(str.charAt(i)));
            }
        }
        return vector;
    }

    public Boolean getSign()
    {
        return sign;
    }
}
