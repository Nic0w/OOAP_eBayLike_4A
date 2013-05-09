/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;


import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author nic0w
 *
 */
public class DefaultUserFactory implements UserFactory {

	private final PersistenceAgent bidStorage;
	
	/**
	 * 
	 */
	public DefaultUserFactory(PersistenceAgent storage) {
		this.bidStorage = storage;
	}
	
	@Override
	public User createNewUser(String login, String name, String forename) {
		return new DefaultUser(this.bidStorage, login, name, forename);
	}

}
