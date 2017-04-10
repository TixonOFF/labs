package by.vsu.Threads;

import by.vsu.Abstract.DoubleDirectionalList;
import by.vsu.Abstract.Queue;
import by.vsu.Abstract.Stack;
import by.vsu.Entity.Doctor;
import by.vsu.Entity.Person;

public class OutThread implements Runnable//в этом потоке больные распределяются по врачам
{
    private Queue<Person> queue;
    private Stack<Person> stack;
    private DoubleDirectionalList<Person> list;
    private Doctor doctor0;
    private Doctor doctor1;
    private Doctor doctor2;


    public OutThread(Queue<Person> queue, Stack<Person> stack, DoubleDirectionalList<Person> list)
    {
        this.queue = queue;
        this.stack = stack;
        this.list = list;

        doctor0 = new Doctor(0);
        doctor1 = new Doctor(1);
        doctor2 = new Doctor(2);
    }

    public void run()
    {
        Long startTime = System.currentTimeMillis();
        Long currentTime;

        try
        {
            do
            {
                Thread.sleep(100);//минута

                currentTime = System.currentTimeMillis();

                System.out.println("Время: " + new Person().getStringTime(currentTime - startTime));
                System.out.println("Список обследованных: " + list);
                System.out.println("Стек: " + stack);
                System.out.println("Очередь: " + queue);
                System.out.println(doctor0.toString(currentTime));
                System.out.println(doctor1.toString(currentTime));
                System.out.println(doctor2.toString(currentTime));
                System.out.println();

                while (stack.size() != 0)//по возможности достаем вершину из стека
                {
                    Person stackPerson = (Person)stack.get();
                    switch (stackPerson.getDoctor())
                    {
                        case 0:
                            if (doctor0.isFree(currentTime))
                            {
                                doctor0.setTime(stackPerson.getMinutes());
                                doctor0.setStartTime(currentTime);
                                list.pushBack(stack.pop());
                                continue;
                            }
                            break;

                        case 1:
                            if (doctor1.isFree(currentTime))
                            {
                                doctor1.setTime(stackPerson.getMinutes());
                                doctor1.setStartTime(currentTime);
                                list.pushBack(stack.pop());
                                continue;
                            }
                            break;

                        case 2:
                            if (doctor2.isFree(currentTime))
                            {
                                doctor2.setTime(stackPerson.getMinutes());
                                doctor2.setStartTime(currentTime);
                                list.pushBack(stack.pop());
                                continue;
                            }
                            break;
                    }

                    break;
                }

                while ((doctor0.isFree(currentTime) | doctor1.isFree(currentTime) | doctor2.isFree(currentTime)) & queue.size() != 0)//пока хотябы 1 врач свободен и очередь не пуста
                {
                    Person queuePerson = queue.pop();
                    switch (queuePerson.getDoctor())
                    {
                        case 0:
                            if (doctor0.isFree(currentTime))
                            {
                                doctor0.setTime(queuePerson.getMinutes());
                                doctor0.setStartTime(currentTime);
                                list.pushBack(queuePerson);
                                continue;
                            }
                            break;

                        case 1:
                            if (doctor1.isFree(currentTime))
                            {
                                doctor1.setTime(queuePerson.getMinutes());
                                doctor1.setStartTime(currentTime);
                                list.pushBack(queuePerson);
                                continue;
                            }
                            break;

                        case 2:
                            if (doctor2.isFree(currentTime))
                            {
                                doctor2.setTime(queuePerson.getMinutes());
                                doctor2.setStartTime(currentTime);
                                list.pushBack(queuePerson);
                                continue;
                            }
                            break;
                    }

                    stack.push(queuePerson);//если все врачи заняты
                }
            }
            while (System.currentTimeMillis() - startTime < InThread.WORKING_MINUTES);
        }
        catch (Exception e) {}
    }
}
