package by.vsu.threads;

import by.vsu.abstractClasses.AbstractQueue;
import by.vsu.entity.Person;

import java.util.Random;

public class InThread implements Runnable//в этом потоке больные поступают в поликлинику
{
    public static final long WORKING_MINUTES = 48000;//1 минута = 100 WORKING_MINUTES
    private AbstractQueue queue;

    public InThread(AbstractQueue queue)
    {
        this.queue = queue;
    }

    public void run()
    {
        Random random = new Random();
        Long startTime = System.currentTimeMillis();

        try
        {
            for (int i = 0;System.currentTimeMillis() - startTime < WORKING_MINUTES; i++)
            {
                queue.pushBack(new Person("Person" + i, random.nextInt(3), i, System.currentTimeMillis() - startTime, random.nextInt(3900) + 100));//добавляем случайного постетителя

                Thread.sleep(random.nextInt(2600));//примерная частота появления посетителей = 13 минут
            }
        }
        catch (Exception e) {}
    }
}
