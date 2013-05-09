/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;



import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author nic0w
 *
 */
public class JavaCollectionsPersistenceAgent implements PersistenceAgent {

	private final Map<Class<?>, Map<Object, Object>> database;
	
	public JavaCollectionsPersistenceAgent() {
		
		this.database = new HashMap<>(4);
	}
	
	
	@Override
	public <T> void store(Object key, T value) {
		
		Class<?> clazz = value.getClass();
		
		if(this.database.get(clazz) == null)
			this.database.put(clazz, new HashMap<>());
		
		this.database.get(clazz).put(key, value);
	}

	@Override
	public <T> T get(Class<T> clazz, Object key) {

		return (T) this.database.get(clazz).get(key);
	}

	@Override
	public <T> Collection<T> getAll(Class<T> clazz) {
		return (Collection<T>) this.database.get(clazz).values();
	}

	@Override
	public <T> boolean remove(Class<T> clazz, Object key) {
	
		return this.database.get(clazz).remove(key) != null;
	}



}
