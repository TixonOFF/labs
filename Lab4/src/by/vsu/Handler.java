package by.vsu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Handler
{
    public static boolean find(Node root, Comparable key)
    {
        return getNode(root, key) != null;
    }

    @Nullable
    public static Node getNode(Node node, Comparable key)
    {
        if (key.compareTo(node.getValue()) == 0)
        {
            return node;
        }
        if (key.compareTo(node.getValue()) > 0 && node.getRight() != null)
        {
            if (node.getRight().getValue().compareTo(key) == 0)
            {
                return node.getRight();
            }
            else
            {
                return getNode(node.getRight(), key);
            }
        }
        if (key.compareTo(node.getValue()) < 0 && node.getLeft() != null)
        {
            if (node.getLeft().getValue().compareTo(key) == 0)
            {
                return node.getLeft();
            }
            else
            {
                return getNode(node.getLeft(), key);
            }
        }

        return null;
    }

    public static int getHeight(Node root, Node node)
    {
        Node current = root;
        int height = getRootHeight(root);

        if (node == null)
        {
            return -1;
        }

        while (true)
        {
            if (current.getValue().compareTo(node.getValue()) == 0)
            {
                return height;
            }

            if (current.getLeft() != null && find(current.getLeft(), node.getValue()))
            {
                current = current.getLeft();
                height--;
                continue;
            }

            if (current.getRight() != null && find(current.getRight(), node.getValue()))
            {
                current = current.getRight();
                height--;
                continue;
            }
        }
    }

    public static int getRootHeight(Node root)
    {
        if (root != null)
        {
            int left = 0;
            int right = 0;

            if (root.getLeft() != null)
            {
                left = getRootHeight(root.getLeft()) + 1;
            }
            if (root.getRight() != null)
            {
                right = getRootHeight(root.getRight()) + 1;
            }

            return Math.max(left, right);
        }

        return -1;
    }

    public static Node findMin(Node p)
    {
        if (p.getLeft() == null)
        {
            return p;
        }

        return findMin(p.getLeft());
    }

    public static Node removeMin(Node p)
    {
        if(p.getLeft() == null)
        {
            return p.getRight();
        }

        p.setLeft(removeMin(p.getLeft()));

        return balance(p);
    }

    public static int getHeight(Node p)
    {
        if (p != null)
        {
            return p.getHeight();
        }

        return 0;
    }

    public static int bFactor(Node p)
    {
        return getHeight(p.getRight()) - getHeight(p.getLeft());
    }

    public static void fixHeight(Node p)
    {
        p.setHeight(Math.max(getHeight(p.getLeft()), getHeight(p.getRight())) + 1);
    }

    public static Node rotateRight(Node p)
    {
        Node q = p.getLeft();
        p.setLeft(q.getRight());
        q.setRight(p);

        fixHeight(p);
        fixHeight(q);

        return q;
    }

    public static Node rotateLeft(Node q)
    {
        Node p = q.getRight();
        q.setRight(p.getLeft());
        p.setLeft(q);

        fixHeight(q);
        fixHeight(p);

        return p;
    }

    public static Node balance(Node p)
    {
        fixHeight(p);

        if(bFactor(p) == 2)
        {
            if(bFactor(p.getRight()) < 0)
            {
                p.setRight(rotateRight(p.getRight()));
            }

            return rotateLeft(p);
        }
        if(bFactor(p) == -2)
        {
            if(bFactor(p.getLeft()) > 0)
            {
                p.setLeft(rotateLeft(p.getLeft()));
            }

            return rotateRight(p);
        }

        p.setHeight(Handler.getRootHeight(p));

        return p;
    }

    @NotNull
    public static String reverseBypass(Node root)
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (root.getLeft() != null)
        {
            stringBuilder.append(reverseBypass(root.getLeft()));
        }
        if (root.getRight() != null)
        {
            stringBuilder.append(reverseBypass(root.getRight()));
        }

        stringBuilder.append(root);

        return stringBuilder.toString();
    }

    @NotNull
    public static String symmetricBypass(Node root)
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (root.getLeft() != null)
        {
            stringBuilder.append(reverseBypass(root.getLeft()));
        }

        stringBuilder.append(root);

        if (root.getRight() != null)
        {
            stringBuilder.append(reverseBypass(root.getRight()));
        }

        return stringBuilder.toString();
    }

    @NotNull
    public static String linearBypass(Node root)
    {
        if (root == null)
        {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(root);
        stringBuilder.append(" ");

        if (root.getLeft() != null)
        {
            stringBuilder.append(linearBypass(root.getLeft()));
        }
        if (root.getRight() != null)
        {
            stringBuilder.append(linearBypass(root.getRight()));
        }

        return stringBuilder.toString();
    }

    public static int getChildCount(Node root)
    {
        if (root == null)
        {
            return 0;
        }

        return 1 + getChildCount(root.getLeft()) + getChildCount(root.getRight());
    }

    public static void task22(BSTree bsTree)
    {
        ArrayList<Node> allNodes = new ArrayList<>();

        getNodes(allNodes, bsTree.getRoot());

        ArrayList<Comparable> values = new ArrayList<>();

        for (int i = 0; i < allNodes.size(); i++)//находим подходщие узлы из всех
        {
            if (allNodes.get(i).getLeft() != null && allNodes.get(i).getRight() != null)
            {
                if (getChildCount(allNodes.get(i).getLeft()) == getChildCount(allNodes.get(i).getRight()) && allNodes.get(i).getLeft().getHeight() != allNodes.get(i).getRight().getHeight())
                {
                    values.add(allNodes.get(i).getValue());
                }
            }
        }

        for (int i = 0; i < values.size() - 1; i++)//сортировка
        {
            for (int j = 0; j < values.size() - 1 - i; j++)
            {
                if (values.get(j).compareTo(values.get(j + 1)) > 0)
                {
                    Comparable buffer = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, buffer);
                }
            }
        }

        if (!values.isEmpty())
        {
            bsTree.delete(values.get(values.size() / 2));
        }
    }

    public static void getNodes(ArrayList<Node> nodes, Node root)
    {
        if (root != null)
        {
            nodes.add(root);

            getNodes(nodes, root.getLeft());
            getNodes(nodes, root.getRight());
        }
    }
}
