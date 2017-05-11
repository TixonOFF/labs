package by.vsu;

public class AVLTree<T extends Comparable> extends BSTree
{
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

        return Handler.balance(p, border);
    }

    private Node remove(Node p, Comparable k)
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

                return Handler.balance(min, border);
            }
        }

        p.setHeight(Math.max(p.getLeft().getHeight(), p.getRight().getHeight()));

        return Handler.balance(p, border);
    }
}
