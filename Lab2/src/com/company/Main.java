package com.company;

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Random random = new Random();
        Timer timer = new Timer();
        int size = 250;


        Double[] doubleArray = new Double[size];
        for (int i = 0; i < size; i++)
        {
            doubleArray[i] = random.nextDouble();
        }
        System.out.println("\nBucket:");
        timer.start();
        doubleArray = Bucket.sort(doubleArray, 10);
        long time = timer.stop();
        System.out.println("time: " + time);
        new Printer<Double>().printArray(doubleArray);


        String[] stringArray = {"How",  "can",  "Cheese", "Pepperoni", "Green", "Olives", "how", "Can", "can", "Big", "Bird", "Fish", "Small", "A", "green", "very", "long", "string", "F11", "1241235"};
        System.out.println("\nLexicon:");
        Lexicon.sort(stringArray);
        new Printer<String>().printArray(stringArray);


        Integer[] shortArray = new Integer[10];
        for (int i = 0; i < 10; i++)
        {
            shortArray[i] = random.nextInt() % 100;
        }
        System.out.println("\nЗадание 3:");
        new Printer<Integer>().printArray(shortArray);
        System.out.println(Array.containsElements(shortArray, 12));
        new Printer<Integer>().printArray(shortArray);


        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = random.nextInt() % 100;
        }


        Integer[]a = array.clone();
        System.out.println("\nInsertion2:");
        new Insertion2().sort(a);
        new Printer<Integer>().printArray(a);


        a = array.clone();
        System.out.println("\nBubble:");
        new Bubble().sort(a);
        new Printer<Integer>().printArray(a);


        a = array.clone();
        System.out.println("\nBarrier:");
        a = new Barrier<Integer>().sort(a);
        new Printer<Integer>().printArray(a);


        a = array.clone();
        System.out.println("\nBarrierRec:");
        a = new Barrier<Integer>().recursionSort(a);
        new Printer<Integer>().printArray(a);
    }
}
