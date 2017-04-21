package by.vsu.entity;

public class DoubleNode<T> extends Node
{
    private Node previous = null;


    public DoubleNode(T value)
    {
        this.value = value;
    }

    public Node getPrevious()
    {
        return previous;
    }

    public void setPrevious(Node previous)
    {
        this.previous = previous;
    }
}
