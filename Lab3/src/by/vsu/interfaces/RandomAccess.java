package by.vsu.interfaces;

public interface RandomAccess extends IsEmpty, PopBack, PopFront, PushBack, PushFront
{
    Object pop();
    Object popAfter();
    boolean pushBefore(Object value);
    boolean pushAfter(Object value);
}
