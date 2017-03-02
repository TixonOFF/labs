package com.company;

public class Task1
{
    public static long factorial(long bottomNumber, long topNumber)//n / 2 * 12 + n / 2 * 19 = 31n/2
    {
        if (topNumber < 0 | bottomNumber < 0 | topNumber < bottomNumber)//6
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

        long middle = (topNumber - bottomNumber) / 2 + bottomNumber;//4

        return factorial(bottomNumber, middle) * factorial(middle + 1, topNumber);//3
    }

    public static long factorial2(int number)//(n - 1)*7 + 5 = 7n - 2
    {
        if (number < 0)//2
        {
            return 0;//1
        }

        if (number < 2)//2
        {
            return 1;//1
        }

        return number * factorial2(number - 1);//3
    }

    public static long factorial3(int number)//3n
    {
        int num = 1;//1
        for (int i = 2; i <= number; i++)//1
        {//2(n - 1)
            num *= i;//1
        }

        return num;//1
    }
}
