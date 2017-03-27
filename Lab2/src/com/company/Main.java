package com.company;

public class Main
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        int size = 10000;


        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = ((((i % 10) * i) % 10)* i * i) % 10;
        }

        timer.start();
        Timsort.sort(array);
        System.out.println("time: " + timer.stop());
        System.out.println("swap: " + Timsort.getSwapCount());
        System.out.println("compare: " + Timsort.getCompareCount());
        new Printer<Integer>().printArray(array);


        for (int i = 0; i < size; i++)
        {
            array[i] = ((((i % 10) * i) % 10)* i * i) % 10;
        }

        timer.start();
        Shaker.sort(array);
        System.out.println("\ntime: " + timer.stop());
        System.out.println("swap: " + (Shaker.getSwapCount()));
        System.out.println("compare: " + Shaker.getCompareCount());
        new Printer<Integer>().printArray(array);


//        for (int i = 0; i < size; i++)
//        {
//            array[i] = i % 10;
//        }
//
//        timer.start();
//        //Merger.sort("in.txt");
//        System.out.println("\ntime: " + timer.stop());
//
//
//        Double[] doubleArray = new Double[size];
//        for (int i = 0; i < size; i++)
//        {
//            doubleArray[i] = 1d / (i % 10 + 1);
//        }
//
//        timer.start();
//        doubleArray = Bucket.sort(doubleArray, 10);
//        System.out.println("\ntime: " + timer.stop());
//        new Printer<Double>().printArray(doubleArray);
//
//
//        String[] stringArray = {"How",  "can",  "Cheese", "Pepperoni", "Black", "Olives", "how", "Can", "can", "Big", "Bird", "Fish", "Small", "A", "very", "long", "string", "F11", "1241235"};
//
//        timer.start();
//        Lexicon.sort(stringArray);
//        System.out.println("\ntime: " + timer.stop());
//        new Printer<String>().printArray(stringArray);
//
//
//        array = new Integer[10];
//        for (int i = 0; i < 10; i++)
//        {
//            array[i] = i % 10;
//        }
//
//        System.out.println("\n" + Array.containsElements(array, 12));
//        new Printer<Integer>().printArray(array);
    }
}
