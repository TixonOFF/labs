package by.vsu.Hash;


//Хэш-таблица с закрытой адресацией, хэш-функция h(x)= x mod SIZE;
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
	
	//Помещает item на позицию h(x),если та не занята, иначе сдвигает позицию вправо. 
	//Если достигли конца таблицы, а место не найдено, переходим в начало.
	public void put(int key, V value)
	{
		int pos = key%this.get_size();
		int border= this.get_size();
		while(border!=0)
		{
			if(pos==this.get_size())//Циклический переход
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
	
	//Ищем значение по ключу, до тех пор пока не встретим null
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
	
	//Находит, а затем удаляет значение по ключу.
	//Из-за удаления может образоваться дыра, которую придётся затыкать следующим, стояще не на своём месте item'ом
	public void pop(int key)
	{
		int pos = key%this.get_size();
		int border= this.get_size(); //Граница будет использоваться, для того чтобы мы не пробегали по второму кругу
		int oldPos=0;
		boolean isDelete=false;
		while(border!=0)
		{
			if(pos==this.get_size())//Обеспечиваем циклический переход
			{
				pos=0;
			}
			if(items[pos]!=null)
			{
				if(items[pos].getKey()==key)//Удаляем значение, и запоминаем позоцию на случай, если образоввалась дыра
				{
					items[pos]=null;
					oldPos=pos;
					isDelete=true;
					pos++;
					break;
				}
			}
			else //Искомого ключа нет, удаление не происходит
			{
				break;
			}
			pos++;
			border--;
		}
		border=this.get_size();
		while(isDelete)//Если произошло удаление, затыкаем дыры, которые могли образоваться
		{
			if(pos==this.get_size())//Обеспечиваем циклический переход
			{
				pos=0;
			}
			if(items[pos]!=null)
			{
				if(items[pos].getKey()%get_size()!=pos)
				{
					if(oldPos<items[pos].getKey()%get_size())
					//Проверка на тот случай, если старая позиция будет левее, чем истинная позиция item'a
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
		bodyBuilder.append("Размер: " + size + "\n\n");
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
