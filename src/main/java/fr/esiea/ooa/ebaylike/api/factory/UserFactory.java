/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.factory;

import fr.esiea.ooa.ebaylike.api.User;

/**
 * @author nic0w
 *
 */
public interface UserFactory {

	public User createNewUser(String login, String name, String forename);
	
}
