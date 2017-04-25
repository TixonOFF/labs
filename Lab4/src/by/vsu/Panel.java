package by.vsu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Panel extends JFrame
{
    private static final int SIZE = 800;
    private static final int BORDER = 10;
    private static final int PADDING = 5;

    private static BSTree tree;
    private static Form form;

    private JButton bAdd;
    private JButton bDel;
    private JTextField tfAdd;
    private JTextField tfDel;


    public Panel(BSTree tree)
    {
        this.tree = tree;

        setBounds(SIZE + 2 * BORDER,0, 200, 180);
        setMinimumSize(new Dimension(200, 180));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        form = new Form();

        setWidgets();

        validate();
        setVisible(true);
    }

    public void setWidgets()
    {
        tfAdd = new JTextField();
        tfAdd.setBounds(0, 0, 185, 30);
        add(tfAdd);

        bAdd = new JButton("Добавить узел");
        bAdd.setBounds(0, 30, 185, 30);
        add(bAdd);

        tfDel = new JTextField();
        tfDel.setBounds(0, 80, 185, 30);
        add(tfDel);

        bDel = new JButton("Удалить узел");
        bDel.setBounds(0, 110, 185, 30);
        add(bDel);

        bAdd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                insert(tfAdd.getText());
            }
        });

        bDel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                remove(tfDel.getText());
            }
        });
    }

    public void insert(String key)
    {
        tree.add(Integer.parseInt(key));
        form.dispatchEvent(new WindowEvent(form, WindowEvent.WINDOW_CLOSING));
        form = new Form();
    }

    public void remove(String key)
    {
        tree.delete(Integer.parseInt(key));
        form.dispatchEvent(new WindowEvent(form, WindowEvent.WINDOW_CLOSING));
        form = new Form();
    }


    public static class Form extends JFrame
    {
        private int scale;
        private int radius;
        private int height;


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
            if (tree.root != null)
            {
                height = Handler.getRootHeight(tree.root);
                scale = SIZE / (int) Math.pow(2, height);
                radius = scale / 2 - PADDING;

                checkup(g, tree.root, BORDER + SIZE / 2, radius + 4 * BORDER);
            }
        }

        public void drawNode(Graphics g, Node node, int x, int y)
        {
            g.setColor(Color.cyan);
            g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
            g.setColor(Color.black);
            g.drawString(node.getValue().toString(), x, y);
        }

        public void drawLine(Graphics g, int x1, int y1, int x2, int y2)
        {
            g.setColor(Color.green);
            g.drawLine(x1, y1, x2, y2);
        }

        public void checkup(Graphics g, Node node, int x, int y)
        {
            if (node != null)
            {
                int y1 = y + SIZE / (height + 1);
                int x1 = (int) (scale * Math.pow(2, Handler.getHeight(tree.root, node) - 2));

                if (node.getLeft() != null)
                {
                    drawLine(g, x, y, x - x1, y1);
                    checkup(g, node.getLeft(), x - x1, y1);
                }

                if (node.getRight() != null)
                {
                    drawLine(g, x, y, x + x1, y1);
                    checkup(g, node.getRight(), x + x1, y1);
                }

                drawNode(g, node, x, y);
            }
        }
    }
}
