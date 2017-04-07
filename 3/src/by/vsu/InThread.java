package by.vsu;

import by.vsu.Abstract.Queue;

import java.util.Random;

class InThread implements Runnable//в этом потоке больные поступают в поликлинику
{
    public static final long WORKING_MINUTES = 48000;//1 минута = 100 WORKING_MINUTES
    private Queue queue;

    public InThread(Queue queue)
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
                queue.push(new Person("Person" + i, random.nextInt(3), i, System.currentTimeMillis() - startTime, random.nextInt(3900) + 100));//добавляем случайного постетителя

                Thread.sleep(random.nextInt(2600));//примерная частота появления посетителей = 13 минут
            }
        }
        catch (Exception e) {}
    }
}
