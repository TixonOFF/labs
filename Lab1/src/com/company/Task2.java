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

    public static Task2 sum(Task2 number1, Task2 number2)//6n^2 + 41n + 35
    {
        if (number1.getK() == number2.getK())//2
        {
            if (number1.getSign() & !number2.getSign())//3
            {
                return Task2.diff(number1, new Task2(true, number2.getK(), number2.getNumbers()));
            }
            if (!number1.getSign() & number2.getSign())//3
            {
                return Task2.diff(number2, new Task2(true, number1.getK(), number1.getNumbers()));
            }

            int k = number1.getK();
            int size;
            Integer[] num1List;
            Integer[] num2List;//4

            if (number1.getSize() >= number2.getSize())//2
            {
                size = number1.getSize();
                num1List = number1.getNumbers();
                num2List = copy(number2.getNumbers(), number1.getSize());//9n + 5
            }
            else
            {
                size = number2.getSize();
                num1List = copy(number1.getNumbers(), number2.getSize());
                num2List = number2.getNumbers();
            }

            Integer[] numbers = new Integer[0];
            int overflow = 0;//2


            for (int i = size - 1; i >= 0; i--)//2n + 2
            {
                numbers = add(numbers, (num1List[i] + num2List[i] + overflow) % k);//3n + 4 + 3

                overflow = (num1List[i] + num2List[i] + overflow) / k;//3
            }
            numbers = add(numbers, overflow);//3n + 4


            Collections.reverse(Arrays.asList(numbers));//1
            numbers = deleteNulls(numbers);//9n + 6
            return new Task2(number1.getSign(), k, numbers);//1
        }

        System.out.println("Разные основания");
        return null;
    }

    public static Task2 diff(Task2 number1, Task2 number2)//12n^2 + 56n + 44
    {
        if (number1.getK() == number2.getK())//2
        {
            if (number1.getSign() & !number2.getSign())//3
            {
                return Task2.sum(number1, new Task2(true, number2.getK(), number2.getNumbers()));
            }
            if (!number1.getSign() & !number2.getSign())//4
            {
                return Task2.diff(new Task2(true, number1.getK(), number1.getNumbers()), new Task2(true, number1.getK(), number1.getNumbers()));
            }
            if (!number1.getSign() & number2.getSign())//3
            {
                return Task2.sum(number1, new Task2(false, number2.getK(), number2.getNumbers()));
            }

            int k = number1.getK();
            int size;
            Integer[] num2List;//3

            if (number1.getSize() >= number2.getSize())//2
            {
                size = number1.getSize();//1
                num2List = copy(number2.getNumbers(), number1.getSize());//9n + 5
            }
            else
            {
                size = number2.getSize();
                num2List = copy(number1.getNumbers(), number2.getSize());
            }

            Integer[] numbers = new Integer[0];
            int overflow = 0;//2

            if (!number2.isBigger(number1))//7n + 4 + 1
            {
                for (int i = size - 1; i >= 0; i--)//2n + 2
                {
                    if (number1.getNumbers()[i] - num2List[i] - overflow < 0)//4
                    {
                        numbers = add(numbers, (number1.getNumbers()[i] - num2List[i] - overflow + k) % k);//3n + 4
                        overflow = 1;//1
                        continue;
                    }
                    numbers = add(numbers, (number1.getNumbers()[i] - num2List[i] - overflow) % k);//3n + 4
                    overflow = 0;//1
                }
                numbers = add(numbers, overflow);//3n + 4


                Collections.reverse(Arrays.asList(numbers));//1
                numbers = deleteNulls(numbers);//9n + 6
                return new Task2(number1.getSign(), k, numbers);//1
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

    public boolean isBigger(Task2 number)//7n + 4
    {
        if (sign == number.getSign())//2
        {
            for (int i = 0; i < getSize(); i++)//2n + 1
            {
                if (!numbers[i].equals(number.getNumbers()[i]))//3
                {
                    return numbers[i] > number.getNumbers()[i];//2
                }
            }
        }

        return sign;//1
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

    public static Integer[] add(Integer[] array, int number)//3n + 4
    {
        Integer[] array1 = new Integer[array.length + 1];//1

        for (int i = 0; i < array.length; i++)//2n + 1
        {
            array1[i] = array[i];//1
        }

        array1[array1.length - 1] = number;//1

        return array1;//1
    }

    public static Integer[] deleteNulls(Integer[] array)//9n + 6
    {
        boolean check = true;
        int size = 0;//2

        for (int i = 0; i < array.length; i++)//2n + 1
        {
            if (array[i].equals(0))//1
            {
                if (check)//1
                {
                    size++;//1
                }
            }
            else
            {
                check = false;
            }
        }

        Integer[] arr = new Integer[array.length - size];//1

        for (int i = size; i < array.length; i++)//2n + 1
        {
            arr[i - size] = array[i];//2
        }

        return arr;//1
    }

    public static Integer[] copy(Integer[] array, int size)//9n + 5
    {
        Integer[] arr = new Integer[size];//1

        for (int i = 0; i < size - array.length; i++)//3n + 1
        {
            arr[i] = 0;//1
        }

        for (int i = size - array.length; i < size; i++)//2n + 2
        {
            arr[i] = array[i - size + array.length];//3
        }

        return arr;//1
    }
}
