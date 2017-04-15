package by.vsu;

import by.vsu.abstractClasses.AbstractDoubleDirectionalList;
import by.vsu.abstractClasses.AbstractQueue;
import by.vsu.abstractClasses.AbstractStack;
import by.vsu.arrays.ArrayDoubleDirectionalList;
import by.vsu.arrays.ArrayQueue;
import by.vsu.arrays.ArrayStack;
import by.vsu.components.NodeDoubleDirectionalList;
import by.vsu.components.NodeQueue;
import by.vsu.components.NodeStack;
import by.vsu.entity.Person;
import by.vsu.threads.InThread;
import by.vsu.threads.OutThread;

public class Main
{
    public static void main(String[] args)
    {
        AbstractQueue<Person> queue = new NodeQueue<>();
        AbstractStack<Person> stack = new NodeStack<>();
        AbstractDoubleDirectionalList<Person> list = new NodeDoubleDirectionalList<>();

//        AbstractQueue<Person> queue = new ArrayQueue<>(512);
//        AbstractStack<Person> stack = new ArrayStack<>(512);
//        AbstractDoubleDirectionalList<Person> list = new ArrayDoubleDirectionalList<>(512);

        new Thread(new InThread(queue)).start();
        new Thread(new OutThread(queue, stack, list)).start();
    }
}
