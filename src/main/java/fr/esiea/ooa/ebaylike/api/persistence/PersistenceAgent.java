/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.persistence;

import java.util.Collection;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;

/**
 * @author nic0w
 *
 */
public interface PersistenceAgent {
	
	public <T> void store(Object key, T value);
	
	public <T> T get(Class<T> clazz, Object key);
	
	public <T> Collection<T> getAll(Class<T> clazz);
	
	public <T> boolean remove(Class<T> clazz, Object key);
	
}
