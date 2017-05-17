package by.vsu;

import java.util.ArrayList;

public class Pattern
{
    private int offsetY;// 1/2 ширины
    private int offsetX;// 1/2 высоты
    private Square[][] pattern;// массив клеток 1х1


    public Pattern(int n, int m)
    {
        if (n > 0 && n % 2 == 0 && n <= 30 && m > 0 && m % 2 == 0 && m <= 30)
        {
            offsetX = n / 2;
            offsetY = m / 2;
        }
        else
        {
            offsetX = 0;
            offsetY = 0;
        }

        pattern = new Square[2 * offsetX][2 * offsetY];

        for (int i = 0; i < 2 * offsetX; i++)
        {
            for (int j = 0; j < 2 * offsetY; j++)
            {
                pattern[i][j] = new Square(0);
            }
        }
    }

    public int getOffsetY()
    {
        return offsetY;
    }

    public int getOffsetX()
    {
        return offsetX;
    }

    public Square[][] getPattern()
    {
        return pattern;
    }

    public void setOffsetY(int offsetY)
    {
        this.offsetY = offsetY;
    }

    public void setOffsetX(int offsetX)
    {
        this.offsetX = offsetX;
    }

    public void setPattern(Square[][] pattern)
    {
        this.pattern = pattern;
    }

    //сложность: m * n
    public void addFigure(int x1, int y1, int x2, int y2, int color)
    {
        if (y1 <= offsetY && y2 <= offsetY && y1 >= -offsetY && y2 >= -offsetY &&
                x1 <= offsetX && x2 <= offsetX && x1 >= -offsetX && x2 >= -offsetX &&
                color > 0 && color < 65 && x1 < x2 && y1 < y2)
        {
            int posY = offsetY + y1;
            int posX = offsetX - x2;
            int n = x2 - x1;
            int m = y2 - y1;

            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < m; j++)
                {
                    pattern[i + posX][j + posY].setColor(color);//отмечаем нужные клетки цветом
                }
            }
        }
    }

    //сложность: ширина * высота
    public ArrayList<Pair> getAreas()
    {
        int n = 2 * offsetX;
        int m = 2 * offsetY;

        boolean[][] isChecked = new boolean[n][m];//массив посещенных клеток

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                isChecked[i][j] = false;
            }
        }

        ArrayList<Pair> figures = new ArrayList<>();//список фигур <цвет: площадь>

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (!isChecked[i][j])
                {
                    figures.add(new Pair(pattern[i][j].getColor(), getArea(i, j, isChecked)));
                }
            }
        }

        return figures;
    }

    //сложность: ширина * высота
    private int getArea(int x, int y, boolean[][] isChecked)//рекурсивно посещает все ближайшие не отмеченные клетки того же цвета и в итоге возвращает их количество = площадь
    {
        isChecked[x][y] = true;

        int topLeft = 0;
        int top = 0;
        int topRight = 0;
        int right = 0;
        int bottomRight = 0;
        int bottom = 0;
        int bottomLeft = 0;
        int left = 0;

        boolean t = x > 0;//есть ли клетки сверху
        boolean b = x < offsetX * 2 - 1;
        boolean l = y > 0;
        boolean r = y < offsetY * 2 - 1;

        int color = pattern[x][y].getColor();

        if (t && pattern[x - 1][y].getColor() == color && !isChecked[x - 1][y])
        {
            top = getArea(x - 1, y, isChecked);
        }
        if (b && pattern[x + 1][y].getColor() == color && !isChecked[x + 1][y])
        {
            bottom = getArea(x + 1, y, isChecked);
        }
        if (l && pattern[x][y - 1].getColor() == color && !isChecked[x][y - 1])
        {
            left = getArea(x, y - 1, isChecked);
        }
        if (r && pattern[x][y + 1].getColor() == color && !isChecked[x][y + 1])
        {
            right = getArea(x, y + 1, isChecked);
        }
        if (t && r && pattern[x - 1][y + 1].getColor() == color && !isChecked[x - 1][y + 1])
        {
            topRight = getArea(x - 1, y + 1, isChecked);
        }
        if (t && l && pattern[x - 1][y - 1].getColor() == color && !isChecked[x - 1][y - 1])
        {
            topLeft = getArea(x - 1, y - 1, isChecked);
        }
        if (b && r && pattern[x + 1][y + 1].getColor() == color && !isChecked[x + 1][y + 1])
        {
            bottomRight = getArea(x + 1, y + 1, isChecked);
        }
        if (b && l && pattern[x + 1][y - 1].getColor() == color && !isChecked[x + 1][y - 1])
        {
            bottomLeft = getArea(x + 1, y - 1, isChecked);
        }

        return 1 + topLeft + top + topRight + right + bottomRight + bottom + bottomLeft + left;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        int n = 2 * offsetX;
        int m = 2 * offsetY;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (pattern[i][j].getColor() < 10)
                {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(pattern[i][j].getColor());
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
