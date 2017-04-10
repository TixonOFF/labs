package by.vsu.Hash;

public class Map<V extends Comparable>
{
    private Integer key;
    private V value;
    private Map<V> map = null;
    private int count = 0;


    public Map(Integer key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public Integer getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    public Map<V> getMap()
    {
        return map;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public void add(Map<V> map)
    {
        if (this.map == null)
        {
            this.map = map;
        }
        else
        {
            this.map.add(map);
        }

        setCount(getCount() + 1);
    }

    public V get(Integer key)
    {
        if (this.key.equals(key))
        {
            return value;
        }
        else
        {
            if (map != null)
            {
                return map.get(key);
            }
            else
            {
                return null;
            }
        }
    }

    public void pop(Integer key)
    {
        if (map != null)
        {
            if (map.getKey().equals(key))
            {
                this.map = map.map;
                this.key = map.key;
                this.value = map.value;
                setCount(getCount() - 1);
            }
            else
            {
                map.pop(key);
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Ключ: ");
        stringBuilder.append(getKey());
        stringBuilder.append("\n");
        stringBuilder.append("Значение: ");
        stringBuilder.append(getValue());

        if (map != null)
        {
            stringBuilder.append("\n");
            stringBuilder.append(map.toString());
        }

        return stringBuilder.toString();
    }
}
