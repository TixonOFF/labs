package by.vsu.Abstract;

import by.vsu.List;

public abstract class SingleDirectionalList<T extends Comparable> implements List
{
    public abstract int getPointer();

    public abstract int size();

    public abstract T get();

    public abstract T next();

    public abstract void begin();

    public abstract void end();

    public abstract void pushFront(T value);

    public abstract void pushBack(T value);

    public abstract void pushAfter(T value);

    public abstract void pushBefore(T value);

    public abstract void remove(int index);
}
