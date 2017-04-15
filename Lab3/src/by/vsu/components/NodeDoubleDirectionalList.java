package by.vsu.components;

import by.vsu.abstractClasses.AbstractDoubleDirectionalList;
import by.vsu.entity.Node;

public class NodeDoubleDirectionalList<T> extends AbstractDoubleDirectionalList
{
    private Node<T> head = null;
    private int pointer = 0;


    @Override
    public T next()
    {
        if (!isEmpty())
        {
            Node<T> node = head;

            for (int i = 0; i < pointer; i++)
            {
                node = node.getNextNode();

                if (node == null)
                {
                    node = head;
                }
            }

            pointer++;

            return node.getValue();
        }

        return null;
    }

    @Override
    public T previous()
    {
        if (!isEmpty())
        {
            Node<T> node = head;

            for (int i = 0; i < pointer + head.getSize() - 1; i++)
            {
                node = node.getNextNode();

                if (node == null)
                {
                    node = head;
                }
            }

            pointer += head.getSize() - 1;

            return node.getValue();
        }

        return null;
    }

    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    @Override
    public T popBack()
    {
        if (!isEmpty())
        {
            if (head.getNextNode() == null)
            {
                T value = head.getValue();
                head = null;
                pointer = 0;

                return value;
            }

            pointer = Math.max(head.getSize() - 2, 0);

            return head.popBack();
        }

        return null;
    }

    @Override
    public T popFront()
    {
        if (!isEmpty())
        {
            Node<T> node = this.head;
            this.head = this.head.getNextNode();
            pointer = 0;

            return node.getValue();
        }

        return null;
    }

    @Override
    public boolean pushBack(Object value)
    {
        if (isEmpty())
        {
            head = new Node<>((T)value);
        }
        else
        {
            head.pushBack((T)value);
        }

        pointer = head.getSize() - 1;

        return true;
    }

    @Override
    public boolean pushFront(Object value)
    {
        if (isEmpty())
        {
            head = new Node(value);
        }
        else
        {
            Node<T> node = this.head;
            this.head = new Node<>((T)value);
            this.head.setNextNode(node);
        }

        pointer = 0;

        return true;
    }

    @Override
    public T pop()
    {
        pointer = Math.max(0, --pointer);
        T res = popAfter();
        pointer = Math.max(0, pointer);

        return res;
    }

    @Override
    public T popAfter()
    {
        if (!isEmpty())
        {
            Node<T> node = head;

            for (int i = 0; i < pointer; i++)
            {
                node = node.getNextNode();

                if (node == null)
                {
                    node = head;
                }
            }

            if (node.getNextNode() != null)
            {
                T res = (T)node.getNextNode().getValue();
                node.setNextNode(node.getNextNode().getNextNode());

                return res;
            }
            else
            {
                T res = head.getValue();
                head = head.getNextNode();
                pointer = 0;

                return res;
            }
        }

        return null;
    }

    @Override
    public boolean pushBefore(Object value)
    {
        if (isEmpty())
        {
            head = new Node<>((T)value);
        }
        else
        {
            Node<T> node = head;

            for (int i = 0; i < pointer - 1; i++)
            {
                node = node.getNextNode();

                if (node == null)
                {
                    node = head;
                }
            }

            if (node == head)
            {
                Node<T> first = new Node<>((T)value);
                first.setNextNode(head);
                head = first;
            }
            else
            {
                Node<T> nextNode = node.getNextNode();
                node.setNextNode(new Node<>((T) value));
                node.getNextNode().setNextNode(nextNode);
            }
        }

        return true;
    }

    @Override
    public boolean pushAfter(Object value)
    {
        if (isEmpty())
        {
            head = new Node<>((T)value);
        }
        else
        {
            Node<T> node = head;

            for (int i = 0; i < pointer; i++)
            {
                node = node.getNextNode();

                if (node == null)
                {
                    node = head;
                }
            }

            Node<T> nextNode = node.getNextNode();
            node.setNextNode(new Node<>((T)value));
            node.getNextNode().setNextNode(nextNode);
        }

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (!isEmpty())
        {
            stringBuilder.append(head);
        }

        return stringBuilder.toString();
    }
}
