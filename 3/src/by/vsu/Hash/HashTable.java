package by.vsu.Hash;

public class HashTable<V extends Comparable>
{
    private static final int SIZE = 32;

    private Map<V>[] array;
    int mapCount = 0;


    public HashTable()
    {
        array = new Map[SIZE];
    }

    public int getMapCount()
    {
        return mapCount;
    }

    private void setMapCount(int mapCount)
    {
        this.mapCount = mapCount;
    }

    public void put(Integer key, V value)
    {
        if (get(key) == null)
        {
            int pos = key % SIZE;

            if (array[pos] != null)
            {
                array[pos].add(new Map<>(key, value));
            }
            else
            {
                array[pos] = new Map<>(key, value);
            }

            setMapCount(getMapCount() + 1);
        }
    }

    public V get(Integer key)
    {
        int pos = key % SIZE;

        if (array[pos] != null)
        {
            return array[pos].get(key);
        }

        return null;
    }

    public void pop(Integer key)
    {
        int pos = key % SIZE;

        if (array[pos].getKey().equals(key))
        {
            array[pos] = null;
            setMapCount(getMapCount() - 1);
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
