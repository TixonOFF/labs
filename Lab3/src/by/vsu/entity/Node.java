package by.vsu.entity;

public class Node<T>
{
    protected Node next = null;
    protected T value;


    public Node(){}

    public Node(T value)
    {
        this.value = value;
    }

    public Node getNext()
    {
        return next;
    }

    public T getValue()
    {
        return value;
    }

    public void setNext(Node next)
    {
        this.next = next;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(value);

        return stringBuilder.toString();
    }
}
