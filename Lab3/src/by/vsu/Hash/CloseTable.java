package by.vsu.Hash;


//���-������� � �������� ����������, ���-������� h(x)= x mod SIZE;
public class CloseTable<V>
{
	private CloseItem [] items;
	
	public CloseTable(int size)
	{
		this.items = new CloseItem[size];
	}
	
	public int get_size()
	{
		return items.length;
	}
	
	//�������� item �� ������� h(x),���� �� �� ������, ����� �������� ������� ������. 
	//���� �������� ����� �������, � ����� �� �������, ��������� � ������.
	public void put(int key, V value)
	{
		int pos = key%this.get_size();
		int border= this.get_size();
		while(border!=0)
		{
			if(pos==this.get_size())//����������� �������
			{
				pos=0;
			}
			
			if(items[pos]==null)
			{
			items[pos]=new CloseItem<V>(key, value);
			break;
			}else
			{
				pos++;
			}
			border--;
		}
	}
	
	//���� �������� �� �����, �� ��� ��� ���� �� �������� null
	public V get(int key)
	{
		int pos = key%this.get_size();
		int border= this.get_size();
		while(border!=0)
		{
			if(pos==this.get_size())
			{
				pos=0;
			}
			if(items[pos]!=null)
			{
				if(items[pos].getKey()==key)
				{
					return (V)items[pos].getValue();
				}
			}else
			{
				return null;
			}
			pos++;
			border--;
		}
		return null;
	}
	
	//�������, � ����� ������� �������� �� �����.
	//��-�� �������� ����� ������������ ����, ������� ������� �������� ���������, ������ �� �� ���� ����� item'��
	public void pop(int key)
	{
		int pos = key%this.get_size();
		int border= this.get_size(); //������� ����� ��������������, ��� ���� ����� �� �� ��������� �� ������� �����
		int oldPos=0;
		boolean isDelete=false;
		while(border!=0)
		{
			if(pos==this.get_size())//������������ ����������� �������
			{
				pos=0;
			}
			if(items[pos]!=null)
			{
				if(items[pos].getKey()==key)//������� ��������, � ���������� ������� �� ������, ���� ������������� ����
				{
					items[pos]=null;
					oldPos=pos;
					isDelete=true;
					pos++;
					break;
				}
			}
			else //�������� ����� ���, �������� �� ����������
			{
				break;
			}
			pos++;
			border--;
		}
		border=this.get_size();
		while(isDelete)//���� ��������� ��������, �������� ����, ������� ����� ������������
		{
			if(pos==this.get_size())//������������ ����������� �������
			{
				pos=0;
			}
			if(items[pos]!=null)
			{
				if(items[pos].getKey()%get_size()!=pos)
				{
					if(oldPos<items[pos].getKey()%get_size())
					//�������� �� ��� ������, ���� ������ ������� ����� �����, ��� �������� ������� item'a
					{
						break;
					}
					items[oldPos]=items[pos];
					items[pos]=null;
					oldPos=pos;
				}
			}else
			{
				break;
			}
			pos++;
			border--;
		}
	}
	@Override
	public String toString()
	{
		StringBuilder bodyBuilder = new StringBuilder();
		int size = items.length;
		bodyBuilder.append("������: " + size + "\n\n");
		for(int i = 0; i < size; i++)
		{
			if(this.items[i] != null)
			{
				bodyBuilder.append(this.items[i]);
				bodyBuilder.append('\n');
			}
		}
		return bodyBuilder.toString();
	}
}
