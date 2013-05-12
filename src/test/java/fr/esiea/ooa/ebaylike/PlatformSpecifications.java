/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;

/**
 * @author Nic0w
 *
 */
public class PlatformSpecifications {

	/**
	 * The platform should be able to create new Users
	 * 
	 * @throws UserAlreadyExistsException
	 * @throws StorageException
	 */
	@Test
	public final void testPlatformCanCreateUserInstances() throws UserAlreadyExistsException, StorageException {
		
		BidPlatform platform = BidPlatform.getDefaultInstance(true);
		
		User user = platform.newUser("aLogin", "aName", "aLastname");
		
		assertFalse(user == null);
	}

}
