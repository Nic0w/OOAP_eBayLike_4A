/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.persistence;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;

/**
 * @author nic0w
 *
 */
public interface PersistenceAgent {
	
	public Persistor<User, UserFactory> getUserPersistor();
	
	public Persistor<Bid, BidFactory> getBidPersistor();
}
