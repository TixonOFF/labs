package by.vsu.Hash;

public class HashTable<V extends Comparable>
{
    private static int SIZE;

    private Item<V>[] array;


    public HashTable(int size)
    {
        array = new Item[size];
        
        this.SIZE = size;
        
        for(int i =0; i<size; i++)
        {
        	array[i] = new BorderItem<V>();
        }
    }

 
    public void put(Integer key, V value)
    {
        if (this.get(key) == null)
        {
            int pos = key % SIZE;

            if (!array[pos].isBorder())
            {
                array[pos].add(new Item<>(key, value));
            }
            else
            {
            	Item<V> buf = array[pos];
            	
                array[pos] = new Item<>(key, value);
                
                array[pos].setNext(buf);
            }
        }
    }

    public V get(Integer key)
    {
        int pos = key % SIZE;

        if (!array[pos].isBorder())
        {
            return array[pos].get(key);
        }

        return null;
    }

    public void pop(Integer key)
    {
        int pos = key % SIZE;
        if(array[pos].getKey() == key)
        {
        	array[pos] = array[pos].getNext();
        }
        else
        {
        	array[pos].pop(key);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < SIZE; i++)
        {
            if (array[i] != null)
            {
                stringBuilder.append(array[i]);
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
