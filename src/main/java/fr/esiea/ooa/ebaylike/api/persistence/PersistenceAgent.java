/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.persistence;

import java.util.Collection;

/**
 * @author nic0w
 *
 */
public interface PersistenceAgent {
	
	public <T> void store(T o);
	
	public <T> Table<T> get(Class<T> clazz);
	
	public <T> Collection<T> getAll(Class<T> clazz);
	
}
