package by.vsu.Hash;

//����� ��������, ���������� ����� ����, � ��������� ��������.
public class CloseItem<V>
{
	private int key;
	private V value;
	
	public CloseItem(int key, V value)
	{
		this.key = key;
		this.value = value;
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
	
	@Override
	public String toString() 
	{
		StringBuilder itemBuilder = new StringBuilder();
		
		itemBuilder.append("����: ");
		itemBuilder.append(key);
		itemBuilder.append("   ��������: ");
		itemBuilder.append(value);
		return itemBuilder.toString();
		}
}
