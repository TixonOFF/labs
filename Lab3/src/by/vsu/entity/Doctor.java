package by.vsu.entity;

public class Doctor
{
    private int number;
    private long startTime = 0L;
    private long time = 0L;

    public Doctor(int number)
    {
        this.number = number;
    }

    public boolean isFree(long currentTime)
    {
        return currentTime - startTime >= time;
    }

    public int getNumber()
    {
        return number;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public long getTime()
    {
        return time;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

    public String toString(long currentTime)
    {
        if (isFree(currentTime))
        {
            return "Врач" + number + " свободен";
        }
        else
        {
            return "Врач" + number + " занят еще " + (time - currentTime + startTime) / 100 + " минут";
        }
    }
}
