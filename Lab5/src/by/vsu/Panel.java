package by.vsu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Panel extends JFrame
{
    private static final int SIZE = 800;
    private static final int BORDER = 10;
    private static final int PADDING = 5;

    private static Heap heap;
    private static Form form;

    private JButton bAdd;
    private JButton bDel;
    private JTextField textField;


    public Panel(Heap heap)
    {
        this.heap = heap;

        setBounds(SIZE + 2 * BORDER,0, 200, 100);
        setMinimumSize(new Dimension(200, 100));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        form = new Form();

        setWidgets();

        validate();
        setVisible(true);
    }

    public void setWidgets()
    {
        textField = new JTextField();
        textField.setBounds(0, 0, 185, 30);
        add(textField);

        bAdd = new JButton("Добавить");
        bAdd.setBounds(0, 30, 92, 30);
        add(bAdd);

        bDel = new JButton("Удалить");
        bDel.setBounds(92, 30, 92, 30);
        add(bDel);

        bAdd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    insert(Integer.parseInt(textField.getText()));
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("NumberFormatException");
                }
            }
        });

        bDel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                remove();
            }
        });
    }

    public void insert(Integer key)
    {
        heap.insert(new Node(key, key));
        form.dispatchEvent(new WindowEvent(form, WindowEvent.WINDOW_CLOSING));
        form = new Form();
    }

    public void remove()
    {
        heap.getMin();
        form.dispatchEvent(new WindowEvent(form, WindowEvent.WINDOW_CLOSING));
        form = new Form();
    }


    public static class Form extends JFrame
    {
        private int radius;
        private int columns;
        private int step;


        public Form()
        {
            setBounds(0, 0, SIZE + 2 * BORDER, SIZE + 4 * BORDER);
            setMinimumSize(new Dimension(SIZE + 2 * BORDER, SIZE + 4 * BORDER));
            setLayout(null);

            validate();
            setVisible(true);
        }

        @Override
        public void paint(Graphics g)
        {
            columns = 0;

            ArrayList<Node> trees = heap.getRootList();

            int size = trees.size();
            for (int i = 0; i < size; i++)
            {
                if (trees.get(i).getDegree() < 3)
                {
                    columns += 1;
                }
                else
                {
                    columns += trees.get(i).getDegree() / 2;
                }
            }

            if (columns > 0)
            {
                if (size > 1)
                {
                    radius = SIZE / columns / 2 - PADDING;
                    step = SIZE / columns;
                }
                else
                {
                    radius = SIZE / (columns + 1) / 2 - PADDING;
                    step = SIZE / (columns + 1);
                }

                int lengthCounter = 0;
                for (int i = size - 1; i >= 0; i--)
                {
                    checkup(g, trees.get(i), SIZE - radius - step * lengthCounter, 3 * BORDER + PADDING + radius);

                    if (trees.get(i).getDegree() < 3)
                    {
                        lengthCounter += 1;
                    }
                    else
                    {
                        lengthCounter += trees.get(i).getDegree() / 2;
                    }
                }
            }
        }

        private void checkup(Graphics g, Node node, int x, int y)
        {
            ArrayList<Node> childes = node.getChildes();

            if (childes != null)
            {
                int size = childes.size();
                for (int i = 0; i < size; i++)
                {
                    drawLine(g, x, y, x - step * i, y + step);
                    checkup(g, childes.get(i), x - step * i, y + step);
                }
            }

            drawNode(g, node, x, y);
        }

        private void drawNode(Graphics g, Node node, int x, int y)
        {
            g.setColor(Color.cyan);
            g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
            g.setColor(Color.black);
            g.drawString(node.getKey().toString(), x, y);
        }

        private void drawLine(Graphics g, int x1, int y1, int x2, int y2)
        {
            g.setColor(Color.red);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
