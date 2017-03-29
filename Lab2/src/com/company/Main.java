package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        int size = 1000;


        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = i % 10;
        }


        System.out.println("Timsort:");
        timer.start();
        Timsort.sort(array);
        System.out.println("time: " + timer.stop());
        System.out.println("swap: " + Timsort.getSwapCount());
        System.out.println("compare: " + Timsort.getCompareCount());
        new Printer<Integer>().printArray(array);


        for (int i = 0; i < size; i++)
        {
            array[i] = i % 10;
        }


        System.out.println("\nShaker:");
        timer.start();
        Shaker.sort(array);
        System.out.println("time: " + timer.stop());
        System.out.println("swap: " + (Shaker.getSwapCount()));
        System.out.println("compare: " + Shaker.getCompareCount());
        new Printer<Integer>().printArray(array);


        BufferedWriter bufferedWriter;
        try
        {
            bufferedWriter = new BufferedWriter(new FileWriter("in.txt"));
            Random random = new Random();

            for (int i = 0; i < size; i++)
            {
                bufferedWriter.write(" " + random.nextInt() % size);
            }

            bufferedWriter.close();
        }
        catch (IOException e) {}


        timer.start();
        Merger.sort("in.txt");
        System.out.println("\ntime: " + timer.stop());


        Double[] doubleArray = new Double[size];
        for (int i = 0; i < size; i++)
        {
            doubleArray[i] = 1d / (i % 10 + 1);
        }

        System.out.println("\nBucket:");
        timer.start();
        doubleArray = Bucket.sort(doubleArray, 10);
        System.out.println("time: " + timer.stop());
        new Printer<Double>().printArray(doubleArray);


        String[] stringArray = {"How",  "can",  "Cheese", "Pepperoni", "Green", "Olives", "how", "Can", "can", "Big", "Bird", "Fish", "Small", "A", "green", "very", "long", "string", "F11", "1241235"};
        System.out.println("\nLexicon:");
        timer.start();
        Lexicon.sort(stringArray);
        System.out.println("time: " + timer.stop());
        new Printer<String>().printArray(stringArray);


        array = new Integer[10];
        for (int i = 0; i < 10; i++)
        {
            array[i] = i % 10;
        }
        System.out.println("\nЗадание 3:");
        System.out.println(Array.containsElements(array, 12));
        new Printer<Integer>().printArray(array);



        array = new Integer[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = ((((i % 10) * i) % 10)* i * i) % 10;
        }


        System.out.println("\nInsertion2:");
        timer.start();
        Insertion2.sort(array);
        System.out.println("time: " + timer.stop());
        new Printer<Integer>().printArray(array);



        for (int i = 0; i < size; i++)
        {
            array[i] = ((((i % 10) * i) % 10)* i * i) % 10;
        }


        System.out.println("\nBubble:");
        timer.start();
        Bubble.sort(array);
        System.out.println("time: " + timer.stop());
        new Printer<Integer>().printArray(array);
    }
}
