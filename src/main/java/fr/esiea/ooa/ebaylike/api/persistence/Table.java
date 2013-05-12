package fr.esiea.ooa.ebaylike.api.persistence;

import java.util.List;

public interface Table<T> extends Iterable<T> {
	
	public T lastRow();
	
	public T row(int i);
	
	public T firstRow();
	
	public FilteredTable<T> where(Class<?> field);
	
	public FilteredTable<T> where(String name);
	
	public void insert(T o);
	
	public List<T> asList();

	public void removeAll();

}
