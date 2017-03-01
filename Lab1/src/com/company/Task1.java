package com.company;

public class Task1
{
    public static int factorial(int bottomNumber, int topNumber)//36
    {
        if (topNumber < 0 | bottomNumber < 0 | topNumber < bottomNumber)//5
        {
            return 0;//1
        }

        if (topNumber - bottomNumber == 1)//3
        {
            return topNumber * bottomNumber;//2
        }
        if (topNumber == bottomNumber)//2
        {
            return topNumber;//1
        }

        int middle = (topNumber - bottomNumber) / 2 + bottomNumber;//4

        return factorial(bottomNumber, middle) * factorial(middle + 1, topNumber);//22
    }

    public static long factorial2(int number)//12
    {
        if (number < 0)//2
        {
            return 0;//1
        }

        if (number < 2)//2
        {
            return 1;//1
        }

        return number * factorial2(number - 1);//6
    }

    public static long factorial3(int number)//3n
    {
        int num = 1;//1
        for (int i = 2; i <= number; i++)//2(n - 1) + 1
        {
            num *= i;//1
        }

        return num;//1
    }
}
