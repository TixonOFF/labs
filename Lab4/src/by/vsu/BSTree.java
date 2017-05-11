package by.vsu;

import java.util.ArrayList;
import java.util.Stack;

public class BSTree<T extends Comparable>
{
    protected Node border = new Node(null, 0);
    protected Node root = border;


    public BSTree()
    {
        border.setHeight(0);
    }

    public Node getRoot()
    {
        return root;
    }

    public Node getBorder()
    {
        return border;
    }

    public void setRoot(Node root)
    {
        this.root = root;
    }

    public void setBorder(Node border)
    {
        this.border = border;
    }

    private Node insert(Node p, Comparable k)
    {
        T key = (T)k;

        if(p == border)
        {
            Node node = new Node(key, 0);
            node.setLeft(getBorder());
            node.setRight(getBorder());
            node.setHeight(0);

            return node;
        }

        if(key.compareTo(p.getValue()) < 0)
        {
            p.setLeft(insert(p.getLeft(), key));
        }

        if(key.compareTo(p.getValue()) > 0)
        {
            p.setRight(insert(p.getRight(), key));
        }

        p.setHeight(Math.max(p.getLeft().getHeight(), p.getRight().getHeight()));

        return p;
    }

    private Node remove(Node p, T k)
    {
        if(p == border)
        {
            return border;
        }

        if(k.compareTo(p.getValue()) < 0)
        {
            p.setLeft(remove(p.getLeft(), k));
        }
        else
        {
            if(k.compareTo(p.getValue()) > 0)
            {
                p.setRight(remove(p.getRight(), k));
            }
            else
            {
                Node q = p.getLeft();
                Node r = p.getRight();

                if(r == border)
                {
                    q.setHeight(Math.max(q.getLeft().getHeight(), q.getRight().getHeight()));

                    return q;
                }

                Node min = Handler.findMin(r, border);
                min.setRight(Handler.removeMin(r, border));
                min.setLeft(q);
                min.setHeight(Math.max(min.getLeft().getHeight(), min.getRight().getHeight()));

                return min;
            }
        }

        p.setHeight(Math.max(p.getLeft().getHeight(), p.getRight().getHeight()));

        return p;
    }

    public void add(T key)
    {
        setRoot(insert(root, key));

        root.setHeight(Handler.getRootHeight(root, border));
        Handler.setHeight(root, border);
    }

    public void delete(T key)
    {
        setRoot(remove(root, key));

        root.setHeight(Handler.getRootHeight(root, border));
        Handler.setHeight(root, border);
    }

    public boolean find(T key)
    {
        border.setValue(key);

        if (root.getValue().compareTo(key) == 0)
        {
            return root != border;
        }

        return Handler.find(root, key, border);
    }

    public int getHeight(T key)
    {
        if (root == border)
        {
            return -1;
        }
        else
        {
            return Handler.getHeight(root, Handler.getNode(root, key, border));
        }
    }

    public int getDepth(T key)
    {
        if (find(key))
        {
            return Handler.getNode(root, key, border).getDepth();
        }

        return -1;
    }

    public String reverseBypass()
    {
        return Handler.reverseBypass(root, border);
    }

    public String symmetricBypass()
    {
        return Handler.symmetricBypass(root, border);
    }

    public void symmetricBypass2()
    {
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> list = new ArrayList<>();
        ArrayList<Node> out = new ArrayList<>();

        stack.push(root);

        while (true)
        {
            if (list.indexOf(stack.get(stack.size() - 1).getLeft()) == -1 && stack.get(stack.size() - 1).getLeft() != border)
            {
                stack.push(stack.get(stack.size() - 1).getLeft());
            }
            else
            {
                if (out.indexOf(stack.get(stack.size() - 1)) == -1)
                {
                    System.out.println(stack.get(stack.size() - 1) + " ");
                    list.add(stack.get(stack.size() - 1));
                    out.add(stack.get(stack.size() - 1));
                }

                if (list.indexOf(stack.get(stack.size() - 1).getRight()) == -1 && stack.get(stack.size() - 1).getRight() != border)
                {
                    stack.push(stack.get(stack.size() - 1).getRight());
                }
                else
                {
                    stack.pop();
                }
            }

            if (stack.isEmpty())
            {
                break;
            }
        }
    }

    @Override
    public String toString()
    {
        return Handler.linearBypass(root, border);
    }
}
