/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import java.util.List;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.factory.ProductFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.DefaultBidFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultOfferFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultProductFactory;
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
	private final ProductFactory productFactory;
	
	public BidPlatform(PersistenceAgent agent, UserFactory userFactory, ProductFactory productFactory) {
		
		this.storage = agent;
		this.userFactory = userFactory;
		this.productFactory = productFactory;
	}

	public static BidPlatform getDefaultInstance() {
		return getDefaultInstance(false);
	}
	
	public static BidPlatform getDefaultInstance(boolean newInstance) {
		
		BidPlatform instance;
		
		if(newInstance || defaultImpl == null) {
			
			PersistenceAgent storage = new JavaCollectionsPersistenceAgent();
			
			ProductFactory productFactory = new DefaultProductFactory();
			BidFactory	bidFactory        = new DefaultBidFactory(storage);
			OfferFactory offerFactory     = new DefaultOfferFactory();
			
			UserFactory userFactory  = new DefaultUserFactory(storage, bidFactory, offerFactory);
		
			instance =  new BidPlatform(storage, userFactory, productFactory);
			
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
	
	public Product newProduct(String description) {
		return this.productFactory.createNewProduct(description);
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
