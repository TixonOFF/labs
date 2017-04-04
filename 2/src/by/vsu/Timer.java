package by.vsu;

public class Timer
{
    private long mSeconds = 0;

    public void start()
    {
        mSeconds = System.currentTimeMillis();
    }

    public long stop()
    {
        return System.currentTimeMillis() - mSeconds;
    }
}
