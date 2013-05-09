/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;


import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author nic0w
 *
 */
public class DefaultUserFactory implements UserFactory {

	private final PersistenceAgent bidStorage;
	
	private final BidFactory bidFactory;
	
	/**
	 * 
	 */
	public DefaultUserFactory(PersistenceAgent storage, BidFactory bidFactory) {
		this.bidStorage = storage;
		this.bidFactory = bidFactory;
	}
	
	@Override
	public User createNewUser(String login, String name, String forename) {
		return new DefaultUser(this.bidStorage, this.bidFactory, login, name, forename);
	}

}
