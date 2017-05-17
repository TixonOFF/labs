package by.vsu;

public class Pair
{
    private int color;
    private int area;

    public Pair(int color, int area)
    {
        this.color = color;
        this.area = area;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<");
        stringBuilder.append(color);
        stringBuilder.append(": ");
        stringBuilder.append(area);
        stringBuilder.append(">");

        return stringBuilder.toString();
    }
}
