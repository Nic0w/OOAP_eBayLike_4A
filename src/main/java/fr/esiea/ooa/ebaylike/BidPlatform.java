/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import fr.esiea.ooa.ebaylike.core.factory.DefaultUserFactory;
import fr.esiea.ooa.ebaylike.iface.User;
import fr.esiea.ooa.ebaylike.iface.factory.UserFactory;

/**
 * @author nic0w
 *
 */
public class BidPlatform {

	private final UserFactory userFactory;
	
	/**
	 * Default constructor, uses the default implementation.
	 */
	public BidPlatform() {
		this(new DefaultUserFactory());
	}

	/**
	 * @param factory A different implementation.
	 */
	public BidPlatform(UserFactory factory) {
		this.userFactory = factory;
	}
	
	/**
	 * Creates a new user in the system
	 * 
	 * @param login The new user's login.
	 * @param name The new user's name.
	 * @param forename The new user's forename.
	 * @return
	 */
	public User newUser(String login, String name, String forename) {
		return this.userFactory.createNewUser(login, name, forename);
	}
}
