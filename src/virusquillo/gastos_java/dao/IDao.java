package virusquillo.gastos_java.dao;

import java.util.Map;

public interface IDao<T> {
	
	public T get(int id);
	public Map<Integer,T> getAll();
	public int insert(T obj);
	public void update(T obj);
	public void remove(int id);

}
