package by.vsu;

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

    @Override
    public String toString()
    {
        return Handler.linearBypass(root);
    }
}
