/**
 * 
 */
package fr.esiea.ooa.ebaylike.core.exception;

import fr.esiea.ooa.ebaylike.iface.User;

/**
 * @author nic0w
 *
 */
public class UserAlreadyExistsException extends Exception {

	private final User alreadyExistingUser;
	
	UserAlreadyExistsException(User user) {
		super(String.format(
			"User with login '%s' is already existing in the system !", user.getLogin()
		));
	
		this.alreadyExistingUser = user;
	}
	
	public User getOriginalUser() {
		return this.alreadyExistingUser;
	}
}
