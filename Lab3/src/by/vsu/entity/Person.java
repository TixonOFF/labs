package by.vsu.entity;

public class Person implements Comparable
{
    private String name;
    private int doctor;
    private int numberInTheQueue;
    private long time;
    private int minutes;

    public Person() {}

    public Person(String name, int nDoctor, int numberInTheQueue, long time, int minutes)
    {
        this.name = name;
        this.doctor = nDoctor;
        this.numberInTheQueue = numberInTheQueue;
        this.time = time;
        this.minutes = minutes;
    }

    public String getName()
    {
        return name;
    }

    public int getDoctor()
    {
        return doctor;
    }

    public int getNumberInTheQueue()
    {
        return numberInTheQueue;
    }

    public long getTime()
    {
        return time;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public String getStringTime(long time)
    {
        long hours = 8 + time / 6000;
        Long minutes = (time % 6000) / 100;

        String min = minutes.toString();

        if (min.length() == 1)
        {
            min = "0" + min;
        }

        return (hours + ":" + min);
    }

    @Override
    public String toString()
    {
        return name + "{номер в очереди: " + numberInTheQueue + ", врач № " + doctor + ", время прибытия " + getStringTime(time) + ", минут у врача: " + minutes / 100 + '}';
    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }
}
