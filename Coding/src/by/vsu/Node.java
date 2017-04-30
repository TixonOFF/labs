package by.vsu;

public class Node
{
    private String value;
    private Node left;
    private Node right;


    public Node(String value)
    {
        this.value = value;
    }

    public Node getRight()
    {
        return right;
    }

    public String getValue()
    {
        return value;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }
}
