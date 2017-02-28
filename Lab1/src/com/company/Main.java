package com.company;

public class Main
{
    public static void main(String[] args)
    {
        int number = 6;

        if (number >= 0)
        {
            System.out.println(Task1.factorial(1, number) + "\n");
        }

        Loader loader1 = new Loader("in.txt");
        Loader loader2 = new Loader("in2.txt");

        try
        {
            Task2 number1 = new Task2(loader1.loadInt(), loader1.loadVector());
            number1.setSign(loader1.getSign());

            Task2 number2 = new Task2(loader2.loadInt(), loader2.loadVector());
            number2.setSign(loader2.getSign());

            System.out.println(number1 + " + " + number1 + " = " + Task2.sum(number1, number1));
            System.out.println(number1 + " + " + number2 + " = " + Task2.sum(number1, number2));
            System.out.println(number2 + " + " + number1 + " = " + Task2.sum(number2, number1));
            System.out.println(number2 + " + " + number2 + " = " + Task2.sum(number2, number2) + "\n");

            System.out.println(number1 + " - " + number1 + " = " + Task2.diff(number1, number1));
            System.out.println(number1 + " - " + number2 + " = " + Task2.diff(number1, number2));
            System.out.println(number2 + " - " + number1 + " = " + Task2.diff(number2, number1));
            System.out.println(number2 + " - " + number2 + " = " + Task2.diff(number2, number2));
        }
        catch (NullPointerException e)
        {
            System.out.println("NullPointerException");
            return;
        }

    }
}
