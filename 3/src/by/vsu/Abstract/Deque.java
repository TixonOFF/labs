package by.vsu.Abstract;

public abstract class Deque<T extends Comparable> extends Queue
{
    public abstract void pushFront(Comparable value);

    public abstract Comparable popBack();
}
