/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import java.util.List;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;

/**
 * @author nic0w
 *
 */
public class BidPlatform {

	private final PersistenceAgent persistenceAgent;
	
	BidPlatform(PersistenceAgent agent) {
		this.persistenceAgent = agent;
	}

	/**
	 * Creates a new user in the system
	 * 
	 * @param login The new user's login.
	 * @param name The new user's name.
	 * @param forename The new user's forename.
	 * @return
	 * @throws UserAlreadyExistsException
	 * @throws StorageException 
	 */
	public User newUser(String login, String name, String forename) throws UserAlreadyExistsException, StorageException {
		
		Persistor<User, UserFactory> userPersistor = this.persistenceAgent.getUserPersistor();
		
		User user = null;
		
		if((user = userPersistor.get(login)) != null)
			throw new UserAlreadyExistsException(user);
			
		user = userPersistor.getFactory().createNewUser(login, name, forename);
		
		userPersistor.store(user);
		
		return user;
	}
	
	public List<Bid> getPublishedBids() {
		return null;
	}
}
