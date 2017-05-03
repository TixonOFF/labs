package by.vsu.Hash;

//Класс сущность, содержащий целый ключ, и некоторое значение.
public class CloseItem<V>
{
	private int key;
	private V value;
	private int pos;
	
	public CloseItem(int key, V value, int pos)
	{
		this.key = key;
		this.value = value;
		this.pos=pos;
	}

	public int getKey() 
	{
		return key;
	}

	public void setKey(int key) 
	{
		this.key = key;
	}

	public V getValue() 
	{
		return value;
	}

	public void setValue(V value) 
	{
		this.value = value;
	}
	
	public int getPos()
	{
		return this.pos;
	}
	
	public void setPos(int pos)
	{
		this.pos = pos;
	}
	@Override
	public String toString() 
	{
		StringBuilder itemBuilder = new StringBuilder();
		
		itemBuilder.append("Ключ: ");
		itemBuilder.append(key);
		itemBuilder.append("   Значение: ");
		itemBuilder.append(value);
		return itemBuilder.toString();
		}
}
