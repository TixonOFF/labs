package by.vsu.Hash;

public class Item<V extends Comparable>
{
    private Integer key;
    private V value;
    private Item<V> next = null;

    public Item()
    {
    	
    }
    
    public Item(Integer key, V value)
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

    public void setNext(Item<V> next)
    {
    	this.next = next;
    }
    
    public Item<V> getNext()
    {
        return next;
    }

    public void add(Item<V> item)
    {
    	Item<V> current = this;
    	while(!current.next.isBorder())
    	{
    		current = current.next;
    	}
        
    	current.next = item;
    }

    public V get(Integer key)
    {
        if (this.key.equals(key))
        {
            return value;
        }
        else
        {
            if (!next.isBorder())
            {
                return next.get(key);
            }
            else
            {
                return null;
            }
        }
    }

    public void pop(Integer key)
    {
    	if(!this.next.isBorder())
    	{
    		if(this.next.key == key)
    		{
    			this.next = this.next.next;
    		}
    		else
    		{
    			this.next.pop(key);
    		}
    	}
    }

    public boolean isBorder()
    {
    	return false;
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

        if (next != null)
        {
            stringBuilder.append("\n");
            stringBuilder.append(next.toString());
        }

        return stringBuilder.toString();
    }
}
