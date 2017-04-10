package by.vsu;

import by.vsu.Abstract.DoubleDirectionalList;
import by.vsu.Abstract.Queue;
import by.vsu.Abstract.Stack;
import by.vsu.Arrays.ADoubleDirectionalList;
import by.vsu.Arrays.AQueue;
import by.vsu.Arrays.AStack;
import by.vsu.Entity.Person;
import by.vsu.Hash.HashTable;
import by.vsu.Threads.InThread;
import by.vsu.Threads.OutThread;

public class Main
{
    public static void main(String[] args)
    {
        Queue<Person> queue = new AQueue<>(new Person[0]);
        Stack<Person> stack = new AStack<>(new Person[0]);
        DoubleDirectionalList<Person> list = new ADoubleDirectionalList<>(new Person[0]);

//        Queue<Person> queue = new LQueue<>(new ArrayList<Person>());
//        Stack<Person> stack = new LStack<>(new ArrayList<Person>());
//        DoubleDirectionalList<Person> list = new LDoubleDirectionalList<>(new ArrayList<Person>());

//        new Thread(new InThread(queue)).start();
//        new Thread(new OutThread(queue, stack, list)).start();


        HashTable<Integer> hashTable = new HashTable<>();
        System.out.println(hashTable);
        hashTable.put(1, 11);
        hashTable.put(2, 222);
        hashTable.put(3, 3333);
        System.out.println(hashTable);
        System.out.println(hashTable.get(1));
        System.out.println(hashTable.getMapCount());
        hashTable.pop(1);
        System.out.println(hashTable.get(1));
        System.out.println(hashTable.getMapCount());
    }
}
