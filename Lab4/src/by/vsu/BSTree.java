package by.vsu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class BSTree<T extends Comparable>
{
    protected Node root;


    public Node getRoot()
    {
        return root;
    }

    public void setRoot(Node root)
    {
        this.root = root;
    }

    private Node insert(Node p, Comparable k)
    {
        T key = (T)k;

        if(p == null)
        {
            return new Node(key, 0);
        }

        if(key.compareTo(p.getValue()) < 0)
        {
            p.setLeft(insert(p.getLeft(), key));
        }

        if(key.compareTo(p.getValue()) > 0)
        {
            p.setRight(insert(p.getRight(), key));
        }

        return p;
    }

    private Node remove(Node p, T k)
    {
        if(p == null)
        {
            return null;
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

                if(r == null)
                {
                    return q;
                }

                Node min = Handler.findMin(r);
                min.setRight(Handler.removeMin(r));
                min.setLeft(q);

                return min;
            }
        }

        return p;
    }

    public void add(T key)
    {
        setRoot(insert(root, key));
    }

    public void delete(T key)
    {
        setRoot(remove(root, key));
    }

    public boolean find(T key)
    {
        if (root != null)
        {
            if (root.getValue().compareTo(key) == 0)
            {
                return true;
            }

            return Handler.find(root, key);
        }

        return false;
    }

    public int getHeight(T key)
    {
        if (root == null)
        {
            return -1;
        }
        else
        {
            return Handler.getHeight(root, Handler.getNode(root, key));
        }
    }

    public int getDepth(T key)
    {
        if (find(key))
        {
            return Handler.getNode(root, key).getDepth();
        }

        return -1;
    }

    public String reverseBypass()
    {
        return Handler.reverseBypass(root);
    }

    public String symmetricBypass()
    {
        return Handler.symmetricBypass(root);
    }

    public void symmetricBypass2()
    {
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> list = new ArrayList<>();
        ArrayList<Node> out = new ArrayList<>();

        stack.push(root);

        while (true)
        {
            if (list.indexOf(stack.get(stack.size() - 1).getLeft()) == -1 && stack.get(stack.size() - 1).getLeft() != null)
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

                if (list.indexOf(stack.get(stack.size() - 1).getRight()) == -1 && stack.get(stack.size() - 1).getRight() != null)
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
        return Handler.linearBypass(root);
    }
}
