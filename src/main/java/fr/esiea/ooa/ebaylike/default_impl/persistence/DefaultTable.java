package fr.esiea.ooa.ebaylike.default_impl.persistence;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.esiea.ooa.ebaylike.api.persistence.FilteredTable;
import fr.esiea.ooa.ebaylike.api.persistence.Table;

public class DefaultTable<T> implements Table<T> {

	private static final Logger dtLogger = LoggerFactory.getLogger(DefaultTable.class);
	
	private final LinkedList<T> rows;
	private final Class<T> storedType;
	
	
	protected DefaultTable(Class<T> clazz, Collection<T> rows) {
		
		this.rows = new LinkedList<>(rows);
		this.storedType = clazz;
		
	}
	
	public DefaultTable(Class<T> clazz) {
		this(clazz, new LinkedList<T>());
	}
	
	@Override
	public T lastRow() {
		return this.rows.peekLast();
	}

	@Override
	public FilteredTable<T> where(Class<?> field) {
		
		dtLogger.info("Creating FilteredTable for field of type {}.", field.getSimpleName());
		
		return new DefaultFTable<T>(this.storedType, this.rows).byField(field);
	}
	
	@Override
	public FilteredTable<T> where(String name) {
		
		dtLogger.info("Creating FilteredTable for field named '{}'.", name);
	
		return new DefaultFTable<T>(this.storedType, this.rows).byName(name);
	}
	
	@Override
	public void insert(T o) {
		
		this.rows.add(o);
	}

	@Override
	public List<T> asList() {
		return this.rows;
	}

	@Override
	public Iterator<T> iterator() {
		return this.rows.iterator();
	}

	@Override
	public T row(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T firstRow() {
		return this.rows.peekFirst();
	}



	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		
	}

}
