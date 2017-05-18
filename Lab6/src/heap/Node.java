package heap;

import java.util.ArrayList;

public class Node
{
    private Integer key;
    private Integer value;
    private ArrayList<Node> childes;
    private Integer degree;//степень вершины


    public Node(Integer key, Integer value)
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

    public Integer getValue()
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

    public void setValue(Integer value)
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
