package com.company;

import java.util.Arrays;
import java.util.Collections;

public class Task2
{
    private boolean sign;
    private int k;//основание
    private Integer[] numbers;

    public Task2()
    {
        sign = true;
        k = 10;

        numbers = new Integer[20000];
        for (int i = 0; i < 20000; i++)
        {
            numbers[i] = k - 1;
        }
    }

    public Task2(boolean sign, int k, Integer[] numbers)
    {
        if (k < 2 | k > 10)
        {
            System.out.println("Некорректный ввод");
            return;
        }

        for (int i = 0; i < numbers.length; i++)
        {
            if (numbers[i] < 0 | numbers[i] >= k)
            {
                System.out.println("Некорректный ввод");
                return;
            }
        }

        if (numbers.length <= 20000)
        {
            this.sign = sign;
            this.k = k;
            this.numbers = deleteNulls(numbers);
            return;
        }

        this.sign = sign;
        this.k = k;
        this.numbers = new Integer[20000];
        for (int i = 0; i < 20000; i++)
        {
            this.numbers[i] = k - 1;
        }
    }

    public Task2(int k, Integer[] numbers)
    {
        if (k < 2 | k > 10)
        {
            System.out.println("Некорректный ввод");
            return;
        }

        for (int i = 0; i < numbers.length; i++)
        {
            if (numbers[i] < 0 | numbers[i] >= k)
            {
                System.out.println("Некорректный ввод");
                return;
            }
        }

        if (numbers.length <= 20000)
        {
            this.k = k;
            this.numbers = deleteNulls(numbers);
            return;
        }

        this.k = k;
        this.numbers = new Integer[20000];
        for (int i = 0; i < 20000; i++)
        {
            this.numbers[i] = k - 1;
        }
    }

    public Integer[] getNumbers()
    {
        return numbers;
    }

    public int getK()
    {
        return k;
    }

    public int getSize()
    {
        return numbers.length;
    }

    public boolean getSign()
    {
        return sign;
    }

    public void setSign(boolean sign)
    {
        this.sign = sign;
    }

    public static Task2 sum(Task2 number1, Task2 number2)
    {
        if (number1.getK() == number2.getK())
        {
            if (number1.getSign() & !number2.getSign())
            {
                return Task2.diff(number1, new Task2(true, number2.getK(), number2.getNumbers()));
            }
            if (!number1.getSign() & number2.getSign())
            {
                return Task2.diff(number2, new Task2(true, number1.getK(), number1.getNumbers()));
            }

            int k = number1.getK();
            int size = 0;
            Integer[] num1List = null;
            Integer[] num2List = null;

            if (number1.getSize() >= number2.getSize())
            {
                size = number1.getSize();
                num1List = number1.getNumbers();
                num2List = copy(number2.getNumbers(), number1.getSize());
            }

            if (number2.getSize() > number1.getSize())
            {
                size = number2.getSize();
                num1List = copy(number1.getNumbers(), number2.getSize());
                num2List = number2.getNumbers();
            }

            Integer[] numbers = new Integer[0];
            int overflow = 0;


            for (int i = size - 1; i >= 0; i--)
            {
                numbers = add(numbers, (num1List[i] + num2List[i] + overflow) % k);

                overflow = (num1List[i] + num2List[i] + overflow) / k;
            }
            numbers = add(numbers, overflow);


            Collections.reverse(Arrays.asList(numbers));
            numbers = deleteNulls(numbers);
            return new Task2(number1.getSign(), k, numbers);
        }

        System.out.println("Разные основания");
        return null;
    }

    public static Task2 diff(Task2 number1, Task2 number2)
    {
        if (number1.getK() == number2.getK())
        {
            if (number1.getSign() & !number2.getSign())
            {
                return Task2.sum(number1, new Task2(true, number2.getK(), number2.getNumbers()));
            }
            if (!number1.getSign() & !number2.getSign())
            {
                return Task2.diff(new Task2(true, number1.getK(), number1.getNumbers()), new Task2(true, number1.getK(), number1.getNumbers()));
            }
            if (!number1.getSign() & number2.getSign())
            {
                return Task2.sum(number1, new Task2(false, number2.getK(), number2.getNumbers()));
            }

            int k = number1.getK();
            int size = 0;
            Integer[] num2List = null;

            if (number1.getSize() >= number2.getSize())
            {
                size = number1.getSize();
                num2List = copy(number2.getNumbers(), number1.getSize());
            }

            if (number2.getSize() > number1.getSize())
            {
                size = number2.getSize();
                num2List = copy(number1.getNumbers(), number2.getSize());
            }

            Integer[] numbers = new Integer[0];
            int overflow = 0;

            if (!number2.isBigger(number1))
            {
                for (int i = size - 1; i >= 0; i--)
                {
                    if (number1.getNumbers()[i] - num2List[i] - overflow < 0)
                    {
                        numbers = add(numbers, (number1.getNumbers()[i] - num2List[i] - overflow + k) % k);
                        overflow = 1;
                        continue;
                    }
                    numbers = add(numbers, (number1.getNumbers()[i] - num2List[i] - overflow) % k);
                    overflow = 0;
                }
                numbers = add(numbers, overflow);


                Collections.reverse(Arrays.asList(numbers));
                numbers = deleteNulls(numbers);
                return new Task2(number1.getSign(), k, numbers);
            }
            else
            {
                for (int i = size - 1; i >= 0; i--)
                {
                    if (number2.getNumbers()[i] - num2List[i] - overflow < 0)
                    {
                        numbers = add(numbers, (number2.getNumbers()[i] - num2List[i] - overflow + k) % k);
                        overflow = 1;
                        continue;
                    }
                    numbers = add(numbers, (number2.getNumbers()[i] - num2List[i] - overflow) % k);
                    overflow = 0;
                }
                numbers = add(numbers, overflow);


                Collections.reverse(Arrays.asList(numbers));
                numbers = deleteNulls(numbers);
                return new Task2(false, k, numbers);
            }
        }

        System.out.println("Разные основания");
        return null;
    }

    public boolean isBigger(Task2 number)
    {
        if (sign == number.getSign())
        {
            for (int i = 0; i < getSize(); i++)
            {
                if (!numbers[i].equals(number.getNumbers()[i]))
                {
                    return numbers[i] > number.getNumbers()[i];
                }
            }
        }

        return sign;
    }

    @Override
    public String toString()
    {
        boolean nullSymbols = getSize() == 0;
        StringBuilder stringBuilder = new StringBuilder();

        if (!sign)
        {
            stringBuilder.append('-');
        }

        for (int i = 0; i < getSize(); i++)
        {
            stringBuilder.append(numbers[i]);
        }

        if (nullSymbols)
        {
            return "0";
        }

        return stringBuilder.toString();
    }

    public static Integer[] add(Integer[] array, int number)
    {
        Integer[] array1 = new Integer[array.length + 1];

        for (int i = 0; i < array.length; i++)
        {
            array1[i] = array[i];
        }

        array1[array1.length - 1] = number;

        return array1;
    }

    public static Integer[] deleteNulls(Integer[] array)
    {
        Integer[] arr = new Integer[0];
        boolean check = false;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != 0 | check)
            {
                check = true;
                arr = add(arr, array[i]);
            }
        }

        return arr;
    }

    public static Integer[] copy(Integer[] array, int size)
    {
        Integer[] arr = new Integer[size];

        for (int i = 0; i < size - array.length; i++)
        {
            arr[i] = 0;
        }

        for (int i = size - array.length; i < size; i++)
        {
            arr[i] = array[i - size + array.length];
        }

        return arr;
    }
}
