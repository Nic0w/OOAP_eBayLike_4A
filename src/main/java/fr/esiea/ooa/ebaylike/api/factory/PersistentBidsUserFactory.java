/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.factory;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;

/**
 * @author nic0w
 *
 */
public abstract class PersistentBidsUserFactory implements UserFactory {

	private final Persistor<Bid, BidFactory> bidPersistor;
	
	/**
	 * 
	 */
	protected PersistentBidsUserFactory(Persistor<Bid, BidFactory> persistor) {
		this.bidPersistor = persistor;
	}

	protected abstract User newUser(String login, String name, String forename);
	
	
	@Override
	public final User createNewUser(String login, String name, String forename) {
		
		
		
		
		return null;
	}
	
	
	
}
