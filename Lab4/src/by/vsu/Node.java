package by.vsu;

public class Node<T extends Comparable>
{
    private Node<T> left = null;
    private Node<T> right = null;
    private T value;
    private int depth;
    private int height = 1;


    public Node(T value, int depth)
    {
        this.value = value;
        this.depth = depth;
    }

    public Node<T> getLeft()
    {
        return left;
    }

    public Node<T> getRight()
    {
        return right;
    }

    public T getValue()
    {
        return value;
    }

    public int getDepth()
    {
        return depth;
    }

    public int getHeight()
    {
        return height;
    }

    public void setLeft(Node<T> left)
    {
        this.left = left;
    }

    public void setRight(Node<T> right)
    {
        this.right = right;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(value);

        return stringBuilder.toString();
    }
}
