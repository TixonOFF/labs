package by.vsu.Hash;

public class Item<V extends Comparable>
{
    private Integer key;
    private V value;
    private Item<V> item = null;


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

    public Item<V> getItem()
    {
        return item;
    }

    public void add(Item<V> item)
    {
    	Item<V> current= this;
    	while(current.item!=null)
    	{
    		current=current.item;
    	}
        
    	current.item=item;
    }

    public V get(Integer key)
    {
        if (this.key.equals(key))
        {
            return value;
        }
        else
        {
            if (item != null)
            {
                return item.get(key);
            }
            else
            {
                return null;
            }
        }
    }

    public void pop(Integer key)
    {
    	if(this.item!=null)
    	{
    	 if(this.item.key==key)
    	 {
    		 if(this.item.item!=null)
    		 {
    			 this.item=this.item.item;
    		 }else
    		 {
    			 this.item=null;
    		 }
    	 }
    	 else
    	 {
    		 this.item.pop(key);
    	 }
    	}
    	/*
    	if(this.getKey().equals(key))  //Если нужно удалить текущий
    	{
    		
    		if(this.item!=null)  // Если нужно удалить текущий и есть следующий
    		{
                 this.key = item.key;
                 this.value = item.value;
                 this.item = item.item;
                 return 0; //Удаление прошло успешно
    		}
    		else 
    		{
    			return 1; //Происходит удаление последнего элемента
    		}
    	}
    	else
    	{
    		if(this.item!=null)
    		{
    			if( this.item.pop(key)==1)
    			{
    				this.item=null;
    			}
    			return 0; //Удаление прошло успешно
    		}
    		else
    		{
    			return 2; //Нет такого элемента
    		}
    	}
    	*/
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

        if (item != null)
        {
            stringBuilder.append("\n");
            stringBuilder.append(item.toString());
        }

        return stringBuilder.toString();
    }
}
