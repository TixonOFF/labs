package com.company;

public class Task1
{
    public static int factorial(int bottomNumber, int topNumber)
    {
        if (topNumber < 0 | bottomNumber < 0 | topNumber < bottomNumber)
        {
            return 0;
        }

        if (topNumber - bottomNumber == 1)
        {
            return topNumber * bottomNumber;
        }
        if (topNumber == bottomNumber)
        {
            return topNumber;
        }

        int middle = (topNumber - bottomNumber) / 2 + bottomNumber;

        return factorial(bottomNumber, middle) * factorial(middle + 1, topNumber);
    }

    public static long factorial2(int number)
    {
        if (number < 0)
        {
            return 0;
        }

        if (number < 2)
        {
            return 1;
        }

        return number * factorial2(number - 1);
    }

    public static long factorial3(int number)
    {
        int num = 1;
        for (int i = 2; i <= number; i++)
        {
            num *= i;
        }

        return num;
    }
}
