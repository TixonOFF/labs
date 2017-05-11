package by.vsu;

public class Node
{
    public static final int FARE_ON_THE_ROAD = 143;//сотимость проезда по шоссе(false)
    public static final int FARE_ON_THE_RAIL = 96;//стоимость проезда по железной дороге(true)

    private int apex;
    private boolean roadType;
    private Node next;


    public Node(int apex, boolean roadType)
    {
        this.apex = apex;
        this.roadType = roadType;
    }

    public int getApex()
    {
        return apex;
    }

    public boolean getRoadType()
    {
        return roadType;
    }

    public Node getNext()
    {
        return next;
    }

    public void setApex(int apex)
    {
        this.apex = apex;
    }

    public void setRoadType(boolean roadType)
    {
        this.roadType = roadType;
    }

    public void setNext(Node next)
    {
        this.next = next;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (!roadType)
        {
            stringBuilder.append("(");
            stringBuilder.append(apex);
            stringBuilder.append(")");
        }
        else
        {
            stringBuilder.append("[");
            stringBuilder.append(apex);
            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }
}

