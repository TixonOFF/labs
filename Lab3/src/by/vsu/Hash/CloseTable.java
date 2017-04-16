package by.vsu.Hash;



public class CloseTable<V>
{
	private V[] val;
	
	public CloseTable(int size)
	{
		this.val = (V[])new Object[size];
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder bodyBuilder = new StringBuilder();
		int size = val.length;
		bodyBuilder.append("Размер: " + size + "\n\n");
		for(int i = 0; i < size; i++)
		{
			if(this.val[i] != null)
			{
				bodyBuilder.append(this.val[i]);
				bodyBuilder.append('\n');
			}
		}
		return bodyBuilder.toString();
	}
}
