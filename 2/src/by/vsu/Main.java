package by.vsu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        InternalSorting internalSorting = new InternalSorting<Integer>();
        Random random = new Random();
        Timer timer = new Timer();
        int size = 20;


        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = i%10;
        }


        Integer[] a = array.clone();
        System.out.println("Timsort:");
        timer.start();
        internalSorting.timsort(a);
        long time = timer.stop();
        System.out.println("time: " + time);
        System.out.println("swap: " + InternalSorting.getSwapCount());
        System.out.println("compare: " + InternalSorting.getCompareCount());
        new Printer<Integer>().printArray(a);
        InternalSorting.setCompareCount(0);
        InternalSorting.setSwapCount(0);


        a = array.clone();
        System.out.println("\nShaker:");
        timer.start();
        internalSorting.shakerSort(a);
        time = timer.stop();
        System.out.println("time: " + time);
        System.out.println("swap: " + InternalSorting.getSwapCount());
        System.out.println("compare: " + InternalSorting.getCompareCount());
        new Printer<Integer>().printArray(a);
        InternalSorting.setCompareCount(0);
        InternalSorting.setSwapCount(0);


        BufferedWriter bufferedWriter;
        try
        {
            bufferedWriter = new BufferedWriter(new FileWriter("in.txt"));

            for (int i = 0; i < size; i++)
            {
                bufferedWriter.write(" " + random.nextInt() % size);
            }

            bufferedWriter.close();
        }
        catch (IOException e) {}


        timer.start();
        //ExternalSorting.nWayMerger("in.txt");
        time = timer.stop();
        System.out.println("\ntime: " + time);
        System.out.println("write: " + ExternalSorting.getWriteCount());
        System.out.println("read: " + ExternalSorting.getReadCount());
        ExternalSorting.setWriteCount(0);
        ExternalSorting.setReadCount(0);
    }
}
