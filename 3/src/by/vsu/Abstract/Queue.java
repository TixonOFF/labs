package by.vsu.Abstract;

import by.vsu.List;

public abstract class Queue<T extends Comparable> implements List
{
    public abstract int size();

    public abstract void push(Comparable value);

    public abstract T pop();
}
