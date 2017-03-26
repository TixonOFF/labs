package com.company;

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
