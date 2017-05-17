package by.vsu;

import java.util.ArrayList;

public class Node<T extends Comparable>
{
    private Integer key;
    private T value;
    private ArrayList<Node> childes;
    private Integer degree;//степень вершины


    public Node(Integer key, T value)
    {
        this.key = key;
        this.value = value;

        childes = new ArrayList<>();
        degree = 1;
    }

    public Integer getKey()
    {
        return key;
    }

    public T getValue()
    {
        return value;
    }

    public ArrayList<Node> getChildes()
    {
        return childes;
    }

    public Integer getDegree()
    {
        return degree;
    }

    public void setKey(Integer key)
    {
        this.key = key;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public void setChildes(ArrayList<Node> childes)
    {
        this.childes = childes;
    }

    public void setDegree(Integer degree)
    {
        this.degree = degree;
    }

    public void addChild(Node child)
    {
        childes.add(child);
    }

    @Override
    public String toString()
    {
        return key.toString();
    }
}
