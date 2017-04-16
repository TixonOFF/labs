package by.vsu.Hash;

public class HashTable<V extends Comparable>
{
    private static int SIZE;

    private Item<V>[] array;
    //int mapCount = 0;


    public HashTable(int size)
    {
        array = new Item[size];
        this.SIZE=size;
    }

    /*public int getMapCount()
    {
        return mapCount;
    }

    private void setMapCount(int mapCount)
    {
        this.mapCount = mapCount;
    }
*/
    public void put(Integer key, V value)
    {
        if (this.get(key) == null)
        {
            int pos = key % SIZE;

            if (array[pos] != null)
            {
                array[pos].add(new Item<>(key, value));
            }
            else
            {
                array[pos] = new Item<>(key, value);
            }

//            setMapCount(getMapCount() + 1);
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
        if(array[pos].getKey()==key)
        {
        	if(array[pos].getItem()!=null)
        	{
        		array[pos]=array[pos].getItem();
        	}else
        	{
        		array[pos]=null;
        	}
        }
        else
        {
        	array[pos].pop(key);
        }
        /*
        if(array[pos]==null)
        {
        switch(array[pos].pop(key)){
        case 1:
        	array[pos]=null;
        	this.setMapCount(getMapCount()-1);
        	break;
        case 0:
        	this.setMapCount(getMapCount()-1);
        	break;
        default:
        	break;
        }
        }
        */
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
