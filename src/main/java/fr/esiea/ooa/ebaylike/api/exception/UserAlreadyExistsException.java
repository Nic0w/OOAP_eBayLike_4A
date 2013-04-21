/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.exception;

import fr.esiea.ooa.ebaylike.api.User;

/**
 * @author nic0w
 *
 */
public class UserAlreadyExistsException extends Exception {

	private final User alreadyExistingUser;
	
	public UserAlreadyExistsException(User user) {
		super(String.format(
			"User with login '%s' is already existing in the system !", user.getLogin()
		));
	
		this.alreadyExistingUser = user;
	}
	
	public User getOriginalUser() {
		return this.alreadyExistingUser;
	}
}
