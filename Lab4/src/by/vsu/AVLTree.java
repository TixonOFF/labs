package by.vsu;

public class AVLTree<T extends Comparable> extends BSTree
{
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

        return Handler.balance(p);
    }

    private Node remove(Node p, Comparable k)
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

                return Handler.balance(min);
            }
        }

        return Handler.balance(p);
    }

    public void add(Comparable key)
    {
        setRoot(insert(root, key));
    }

    public void delete(Comparable key)
    {
        setRoot(remove(root, key));
    }
}
