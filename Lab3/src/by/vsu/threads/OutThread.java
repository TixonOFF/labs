package by.vsu.threads;

import by.vsu.abstractClasses.AbstractDoubleDirectionalList;
import by.vsu.abstractClasses.AbstractQueue;
import by.vsu.abstractClasses.AbstractStack;
import by.vsu.entity.Doctor;
import by.vsu.entity.Person;

public class OutThread implements Runnable//в этом потоке больные распределяются по врачам
{
    private AbstractQueue<Person> queue;
    private AbstractStack<Person> stack;
    private AbstractDoubleDirectionalList<Person> list;
    private Doctor doctor0;
    private Doctor doctor1;
    private Doctor doctor2;


    public OutThread(AbstractQueue<Person> queue, AbstractStack<Person> stack, AbstractDoubleDirectionalList<Person> list)
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

                while (!stack.isEmpty())//по возможности достаем вершину из стека
                {
                    Person stackPerson = (Person)stack.popBack();

                    switch (stackPerson.getDoctor())
                    {
                        case 0:
                            if (doctor0.isFree(currentTime))
                            {
                                doctor0.setTime(stackPerson.getMinutes());
                                doctor0.setStartTime(currentTime);
                                list.pushBack(stackPerson);
                                continue;
                            }
                            else
                            {
                                stack.pushBack(stackPerson);
                            }
                            break;

                        case 1:
                            if (doctor1.isFree(currentTime))
                            {
                                doctor1.setTime(stackPerson.getMinutes());
                                doctor1.setStartTime(currentTime);
                                list.pushBack(stackPerson);
                                continue;
                            }
                            else
                            {
                                stack.pushBack(stackPerson);
                            }
                            break;

                        case 2:
                            if (doctor2.isFree(currentTime))
                            {
                                doctor2.setTime(stackPerson.getMinutes());
                                doctor2.setStartTime(currentTime);
                                list.pushBack(stackPerson);
                                continue;
                            }
                            else
                            {
                                stack.pushBack(stackPerson);
                            }
                            break;
                    }

                    break;
                }

                while ((doctor0.isFree(currentTime) || doctor1.isFree(currentTime) || doctor2.isFree(currentTime)) && !queue.isEmpty())//пока хотябы 1 врач свободен и очередь не пуста
                {
                    Person queuePerson = (Person)queue.popFront();

                    System.out.println(queuePerson);

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

                    stack.pushBack(queuePerson);//если все врачи заняты
                }
            }
            while (System.currentTimeMillis() - startTime < InThread.WORKING_MINUTES);
        }
        catch (Exception e) {}
    }
}
