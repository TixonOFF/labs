package by.vsu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Handler
{
    public static boolean find(Node root, Comparable key, Node border)
    {
        return getNode(root, key, border) != border;
    }

    @Nullable
    public static Node getNode(Node root, Comparable key, Node border)
    {
        if (root != border)
        {
            if (key.compareTo(root.getValue()) == 0)
            {
                return root;
            }
            if (key.compareTo(root.getValue()) > 0 && root.getRight() != border)
            {
                if (root.getRight().getValue().compareTo(key) == 0)
                {
                    return root.getRight();
                }
                else
                {
                    return getNode(root.getRight(), key, border);
                }
            }
            if (key.compareTo(root.getValue()) < 0 && root.getLeft() != border)
            {
                if (root.getLeft().getValue().compareTo(key) == 0)
                {
                    return root.getLeft();
                }
                else
                {
                    return getNode(root.getLeft(), key, border);
                }
            }
        }

        return border;
    }

    public static int getHeight(Node root, Node node, Node border)
    {
        if (node != border && root != border && find(root, node.getValue(), border))
        {
            Node current = root;
            int height = getRootHeight(root, border);

            while (true)
            {
                if (current.getValue().compareTo(node.getValue()) == 0)
                {
                    return height;
                }
                if (current.getValue().compareTo(node.getValue()) > 0)
                {
                    current = current.getLeft();
                    height--;
                    continue;
                }
                if (current.getValue().compareTo(node.getValue()) < 0)
                {
                    current = current.getRight();
                    height--;
                    continue;
                }
            }
        }

        return -1;
    }

    public static int getHeight(Node p, Node border)
    {
        if (p != border)
        {
            return p.getHeight();
        }

        return 0;
    }

    public static int getRootHeight(Node root, Node border)
    {
        if (root != border)
        {
            int left = 0;
            int right = 0;

            if (root.getLeft() != border)
            {
                left = getRootHeight(root.getLeft(), border) + 1;
            }
            if (root.getRight() != border)
            {
                right = getRootHeight(root.getRight(), border) + 1;
            }

            return Math.max(left, right);
        }

        return -1;
    }

    public static void setHeight(Node root, Node border)
    {
        if (root.getLeft() != border)
        {
            root.getLeft().setHeight(root.getHeight() - 1);
            setHeight(root.getLeft(), border);
        }
        if (root.getRight() != border)
        {
            root.getRight().setHeight(root.getHeight() - 1);
            setHeight(root.getRight(), border);
        }
    }

    public static Node findMin(Node p, Node border)
    {
        if (p.getLeft() == border)
        {
            return p;
        }

        return findMin(p.getLeft(), border);
    }

    public static Node removeMin(Node p, Node border)
    {
        if(p.getLeft() == border)
        {
            return p.getRight();
        }

        p.setLeft(removeMin(p.getLeft(), border));

        return balance(p, border);
    }

    public static int bFactor(Node p, Node border)
    {
        return getHeight(p.getRight(), border) - getHeight(p.getLeft(), border);
    }

    public static void fixHeight(Node p, Node border)
    {
        p.setHeight(Math.max(getHeight(p.getLeft(), border), getHeight(p.getRight(), border)) + 1);
    }

    public static Node rotateRight(Node p, Node border)
    {
        Node q = p.getLeft();
        p.setLeft(q.getRight());
        q.setRight(p);

        fixHeight(p, border);
        fixHeight(q, border);

        resetDepth(q, border);

        return q;
    }

    public static Node rotateLeft(Node q, Node border)
    {
        Node p = q.getRight();
        q.setRight(p.getLeft());
        p.setLeft(q);

        fixHeight(q, border);
        fixHeight(p, border);

        resetDepth(p, border);

        return p;
    }

    public static Node balance(Node p, Node border)
    {
        fixHeight(p, border);

        if(bFactor(p, border) == 2)
        {
            if(bFactor(p.getRight(), border) < 0)
            {
                p.setRight(rotateRight(p.getRight(), border));
            }

            return rotateLeft(p, border);
        }
        if(bFactor(p, border) == -2)
        {
            if(bFactor(p.getLeft(), border) > 0)
            {
                p.setLeft(rotateLeft(p.getLeft(), border));
            }

            return rotateRight(p, border);
        }

        p.setHeight(Handler.getRootHeight(p, border));

        return p;
    }

    @NotNull
    public static String reverseBypass(Node root, Node border)
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (root.getLeft() != border)
        {
            stringBuilder.append(reverseBypass(root.getLeft(), border));
        }
        if (root.getRight() != border)
        {
            stringBuilder.append(reverseBypass(root.getRight(), border));
        }

        stringBuilder.append(root);

        return stringBuilder.toString();
    }

    @NotNull
    public static String symmetricBypass(Node root, Node border)
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (root.getLeft() != border)
        {
            stringBuilder.append(reverseBypass(root.getLeft(), border));
        }

        stringBuilder.append(root);

        if (root.getRight() != border)
        {
            stringBuilder.append(reverseBypass(root.getRight(), border));
        }

        return stringBuilder.toString();
    }

    @NotNull
    public static String linearBypass(Node root, Node border)
    {
        if (root == border)
        {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(root);
        stringBuilder.append(" ");

        if (root.getLeft() != border)
        {
            stringBuilder.append(linearBypass(root.getLeft(), border));
        }
        if (root.getRight() != border)
        {
            stringBuilder.append(linearBypass(root.getRight(), border));
        }

        return stringBuilder.toString();
    }

    public static int getChildCount(Node root, Node border)
    {
        if (root == border)
        {
            return 0;
        }

        return 1 + getChildCount(root.getLeft(), border) + getChildCount(root.getRight(), border);
    }

    public static void task22(BSTree bsTree)
    {
        Node border = bsTree.getBorder();
        ArrayList<Node> allNodes = new ArrayList<>();

        getNodes(allNodes, bsTree.getRoot(), border);

        ArrayList<Comparable> values = new ArrayList<>();

        for (int i = 0; i < allNodes.size(); i++)//находим подходщие узлы из всех
        {
            if (allNodes.get(i).getLeft() != border && allNodes.get(i).getRight() != border)
            {
                if (getChildCount(allNodes.get(i).getLeft(), border) == getChildCount(allNodes.get(i).getRight(), border) && allNodes.get(i).getLeft().getHeight() != allNodes.get(i).getRight().getHeight())
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

    public static void getNodes(ArrayList<Node> nodes, Node root, Node border)
    {
        if (root != border)
        {
            nodes.add(root);

            getNodes(nodes, root.getLeft(), border);
            getNodes(nodes, root.getRight(), border);
        }
    }

    public static void resetDepth(Node root, Node border)
    {
        if (root != border)
        {
            root.getLeft().setDepth(root.getDepth() + 1);
            resetDepth(root.getLeft(), border);

            root.getRight().setDepth(root.getDepth() + 1);
            resetDepth(root.getRight(), border);
        }
    }
}
