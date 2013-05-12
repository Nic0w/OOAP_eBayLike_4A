/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.persistence;

import java.util.Collection;

import fr.esiea.ooa.ebaylike.api.Alert;
import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;

/**
 * @author nic0w
 *
 */
public interface PersistenceAgent2 {
	
	public <T> void store(T o);
	
	public <T> Table<T> get(Class<T> clazz);
	
	public <T> Collection<T> getAll(Class<T> clazz);
	
}
