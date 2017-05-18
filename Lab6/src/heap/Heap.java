package heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Heap
{
    private ArrayList<Node> rootList;


    public Heap()
    {
        rootList = new ArrayList<>();
    }

    public ArrayList<Node> getRootList()
    {
        return rootList;
    }

    public void setRootList(ArrayList<Node> rootList)
    {
        this.rootList = rootList;
    }

    public void merge(Heap heap)
    {
        if (heap.rootList.size() > 0 && rootList.size() > 0)//сортировка слиянием элементов rootList двух куч
        {
            ArrayList<Node> res = new ArrayList<>();

            ArrayList<Node> heapList = heap.rootList;

            int thisSize = rootList.size();
            int heapSize = heapList.size();

            int thisPoint = 0;
            int heapPoint = 0;

            for (int i = 0; i < thisSize + heapSize; i++)
            {
                if (rootList.get(thisPoint).getDegree().compareTo(heapList.get(heapPoint).getDegree()) > 0)
                {
                    res.add(heapList.get(heapPoint));
                    heapPoint++;

                    if (heapPoint == heapSize)
                    {
                        for (int j = thisPoint; j < thisSize; j++)
                        {
                            res.add(rootList.get(j));
                        }

                        break;
                    }
                }
                else
                {
                    res.add(rootList.get(thisPoint));
                    thisPoint++;

                    if (thisPoint == thisSize)
                    {
                        for (int j = heapPoint; j < heapSize; j++)
                        {
                            res.add(heapList.get(j));
                        }

                        break;
                    }
                }
            }

            int size = res.size();

            for (int i = 0; i < size - 1;)
            {
                if (res.get(i).getDegree().compareTo(res.get(i + 1).getDegree()) == 0)//если нашли 2 корня одинакового размера
                {
                    if (res.get(i).getKey().compareTo(res.get(i + 1).getKey()) > 0)
                    {
                        res.get(i + 1).addChild(res.get(i));
                        res.get(i + 1).setDegree(res.get(i + 1).getDegree() * 2);
                        res.remove(i);
                    }
                    else
                    {
                        res.get(i).addChild(res.get(i + 1));
                        res.get(i).setDegree(res.get(i).getDegree() * 2);
                        res.remove(i + 1);
                    }

                    size--;
                }
                else
                {
                    i++;
                }
            }

            rootList = res;

            return;
        }

        if (heap.rootList.size() > 0)
        {
            rootList = heap.rootList;
        }
    }

    public void insert(Node node)
    {
        Heap heap = new Heap();
        heap.getRootList().add(node);//создаем кучу с 1 элементом node

        merge(heap);//объединяем кучи
    }

    public Node getMin()
    {
        int size = rootList.size();

        if (size > 0)
        {
            Node min = rootList.get(0);

            for (int i = 1; i < size; i++)
            {
                if (min.getKey().compareTo(rootList.get(i).getKey()) > 0)
                {
                    min = rootList.get(i);
                }
            }

            rootList.remove(rootList.indexOf(min));

            ArrayList<Node> childes = min.getChildes();
            Collections.reverse(childes);

            size = childes.size();
            for (int i = 0; i < size; i++)
            {
                insert(childes.get(i));
            }

            return min;
        }

        return null;
    }

    public void toHeap(Collection<Node> collection)
    {
        int size = collection.size();

        Node[] nodes = new Node[size];
        nodes = collection.toArray(nodes);

        rootList = new ArrayList<>();

        for (int i = 0; i < size; i++)
        {
            insert(nodes[i]);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        int size = rootList.size();
        for (int i = 0; i < size; i++)
        {
            stringBuilder.append(toString(rootList.get(i)));
            stringBuilder.append("\n");
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private String toString(Node node)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(node);

        if (node.getChildes() != null)
        {
            int size = node.getChildes().size();
            for (int i = 0; i < size; i++)
            {
                stringBuilder.append(" ");
                stringBuilder.append(toString((Node) node.getChildes().get(i)));
            }
        }

        return stringBuilder.toString();
    }
}
