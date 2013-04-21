/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;


import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;

/**
 * @author nic0w
 *
 */
public class DefaultUserFactory implements UserFactory {

	private final Persistor<Bid, BidFactory> bidPersistor;
	
	/**
	 * 
	 */
	public DefaultUserFactory(Persistor<Bid, BidFactory> persistor) {
		this.bidPersistor = persistor;
	}
	
	@Override
	public User createNewUser(String login, String name, String forename) {
		return new DefaultUser(this.bidPersistor, login, name, forename);
	}

}
