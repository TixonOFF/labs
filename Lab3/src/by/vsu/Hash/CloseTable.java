package by.vsu.Hash;

import by.vsu.TableOverflowException;

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
	public void put(int key, V value) throws TableOverflowException 
	{
		int size = this.get_size();
		int pos = key%size;
		int border= size;
		while(border!=0)
		{
			
			if(items[pos%size]==null)
			{
			     items[pos%size]=new CloseItem<V>(key, value, pos);
			     return;
			}else
			{
				pos++;
			}
			border--;
		}
		throw new TableOverflowException();
	}
	
	//���� �������� �� �����, �� ��� ��� ���� �� �������� null
	public V get(int key)
	{
		int pos = key%this.get_size();
		int border = this.get_size();
		while(border != 0 && items[pos] != null)
		{
			if(items[pos].getKey() == key)
			{
				return (V)items[pos].getValue();
			}
			pos++;
			pos %= this.get_size();
			border--;
		}
		return null;
	}
	
	//�������, � ����� ������� �������� �� �����.
	//��-�� �������� ����� ������������ ����, ������� ������� �������� ���������, ������ �� �� ���� ����� item'��
	public void pop(int key)
	{
		int size = this.get_size();
		int pos = key%size;
		int border = size; //������� ����� ��������������, ��� ���� ����� �� �� ��������� �� ������� �����
		int deletePosition = 0;
		boolean isDelete = false;
		while(border != 0)
		{			
			if(items[pos%size] != null)
			{
				if(items[pos%size].getKey() == key)//������� ��������, � ���������� ������� �� ������, ���� ������������� ����
				{
					items[pos%size] = null;
					deletePosition = pos;
					isDelete = true;
					pos++;
					border--;
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
		border = size;
		
		while(isDelete)//���� ��������� ��������, �������� ����, ������� ����� ������������
		{	
			
			if(items[pos%size] != null)
			{
				if(items[pos%size].getKey()%size != pos%size)
				{
					if(deletePosition>items[pos%size].getPos())
					//�������� �� ��� ������, ���� ������ ������� ����� �����, ��� �������� ������� item'a
					{
						pos++;
						continue;
					}
					items[deletePosition%size] = items[pos%size];
					items[pos%size].setPos(deletePosition);
					items[pos%size] = null;
					deletePosition = pos;
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
