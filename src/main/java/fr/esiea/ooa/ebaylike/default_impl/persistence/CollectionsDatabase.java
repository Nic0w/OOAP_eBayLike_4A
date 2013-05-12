/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.Table;

/**
 * @author nic0w
 *
 */
public class CollectionsDatabase implements PersistenceAgent {

	private static final Logger cpa2Logger = LoggerFactory.getLogger(CollectionsDatabase.class);
	
	private final Map<Class<?>, Class<?>> supertypeBindings;
	
	private final Map<Class<?>, Table<?>> tables;
	
	/**
	 * 
	 */
	public CollectionsDatabase() {
		this.tables = new HashMap<>();
		this.supertypeBindings = new HashMap<>();
	}

	public void bind(Class<?> source, Class<?> target) {
		this.supertypeBindings.put(source, target);
	}
	
	private Class<?> getBindingFor(Class<?> source) {
		return this.supertypeBindings.get(source);
	}
	
	@Override
	public <T> void store(T o) {
		
		cpa2Logger.debug("Storing object {} .", o);
		
		Class<?> type = getBindingFor(o.getClass());
		
		if(type == null) { //No binding found
			cpa2Logger.trace("No bindind for type {}.", o.getClass().getSimpleName());
			type = o.getClass();
		}
		else
			cpa2Logger.trace("Binding for type {} found : {}.", o.getClass().getSimpleName(), type.getSimpleName());
		
		Table<T> table = (Table<T>) tables.get(type);
		
		if(table == null) {
			
			cpa2Logger.trace("Table for type {} doesn't exist .", type.getSimpleName());
			
			table = new DefaultTable<T>((Class<T>) o.getClass());
			tables.put(type, table);
		}
		
		table.insert(o);
		
		cpa2Logger.trace("Object {} stored !", o);
	}


	@Override
	public <T> Table<T> get(Class<T> clazz) {
		
		cpa2Logger.debug("Retrieving table for type {}.", clazz.getSimpleName());
		
		Table<T> table = (Table<T>) this.tables.get(clazz);
		
		if(table == null) {
			
			cpa2Logger.trace("Table doesn't exist, creating it...");
			
			this.tables.put(clazz, table = new DefaultTable<>(clazz));
			
			cpa2Logger.trace("Table added !");
		}
		
		return table;
	}


	
	@Override
	public <T> Collection<T> getAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
