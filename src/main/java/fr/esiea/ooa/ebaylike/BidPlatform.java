/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import java.util.List;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.DefaultBidFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultUserFactory;
import fr.esiea.ooa.ebaylike.default_impl.JavaCollectionsPersistenceAgent;

/**
 * 
 * @author Nicolas Remi Romain
 *
 */
public class BidPlatform {

	private static BidPlatform defaultImpl = null;
	
	private final PersistenceAgent storage;
	
	private final UserFactory userFactory;
	
	public BidPlatform(PersistenceAgent agent, UserFactory userFactory) {
		
		this.storage = agent;
		this.userFactory = userFactory;
	}

	public static BidPlatform getDefaultInstance() {
		return getDefaultInstance(false);
	}
	
	public static BidPlatform getDefaultInstance(boolean newInstance) {
		
		BidPlatform instance;
		
		if(newInstance || defaultImpl == null) {
			
			PersistenceAgent storage = new JavaCollectionsPersistenceAgent();
			BidFactory	bidFactory   = new DefaultBidFactory();
			UserFactory userFactory  = new DefaultUserFactory(storage, bidFactory);
		
			instance =  new BidPlatform(storage, userFactory);
			
			if(!newInstance) 
				defaultImpl = instance;
		}
		else
			instance = defaultImpl;

		return instance;
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
		
		User user = null;
		
		if((user = storage.get(User.class, login)) != null)
			throw new UserAlreadyExistsException(user);
			
		user = this.userFactory.createNewUser(login, name, forename);
		
		storage.store(user.getLogin(), user);
		
		return user;
	}
	
	/**
	 * 
	 * return all the bids
	 * 
	 * @return List<Bid>
	 */
	public List<Bid> getPublishedBids() {
		return null;
	}
}
