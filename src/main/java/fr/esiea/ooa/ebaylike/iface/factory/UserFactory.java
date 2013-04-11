/**
 * 
 */
package fr.esiea.ooa.ebaylike.iface.factory;

import fr.esiea.ooa.ebaylike.iface.User;

/**
 * @author nic0w
 *
 */
public interface UserFactory {

	public User createNewUser(String login, String name, String forename);
	
}
