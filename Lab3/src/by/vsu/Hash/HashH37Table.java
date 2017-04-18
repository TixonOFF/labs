package by.vsu.Hash;

public class HashH37Table<V extends Comparable> {

	private static int SIZE;

    private Item<V>[] array;


    public HashH37Table(int size)
    {
        array = new Item[size];
        this.SIZE=size;
    }

    public void put(Integer key, V value)
    {
        if (this.get(key) == null)
        {
            int pos = (int) (this.pos(key) % SIZE);

            if (array[pos] != null)
            {
                array[pos].add(new Item<>(key, value));
            }
            else
            {
                array[pos] = new Item<>(key, value);
            }

        }
    }

    public V get(Integer key)
    {
        int pos = (int) (this.pos(key) % SIZE);

        if (array[pos] != null)
        {
            return array[pos].get(key);
        }

        return null;
    }

    public void pop(Integer key)
    {
        int pos =(int) (this.pos(key) % SIZE);
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
    }

    private long pos(int key)
    {
    	long hash = 2139062143;
    	hash = hash*37+key;
    	
    	return Math.abs(hash);
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
