/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.BidState;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.PersistentBid;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.factory.AlertFactory;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.factory.ProductFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.DefaultAlertFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultBidFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultOffer;
import fr.esiea.ooa.ebaylike.default_impl.DefaultOfferFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultProduct;
import fr.esiea.ooa.ebaylike.default_impl.DefaultProductFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultUser;
import fr.esiea.ooa.ebaylike.default_impl.DefaultUserFactory;
import fr.esiea.ooa.ebaylike.default_impl.p2.CollectionsDatabase;

/**
 * 
 * @author Nicolas Remi Romain
 *
 */
public class BidPlatform {

	private static final Logger bpLogger = LoggerFactory.getLogger(BidPlatform.class);
	
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
			
			bpLogger.info("Creating new BidPlatform.");
			
			CollectionsDatabase storage = new CollectionsDatabase();
			
			storage.bind(DefaultUser.class,    User.class);
			storage.bind(DefaultOffer.class,   Offer.class);
			storage.bind(DefaultProduct.class, Product.class);
			storage.bind(PersistentBid.class,  Bid.class);
			
			ProductFactory productFactory = new DefaultProductFactory();
			AlertFactory alertFactory     = new DefaultAlertFactory();
			BidFactory	bidFactory        = new DefaultBidFactory(storage, alertFactory);
			OfferFactory offerFactory     = new DefaultOfferFactory();
			
			UserFactory userFactory  = new DefaultUserFactory(storage, bidFactory, offerFactory);
		
			instance =  new BidPlatform(storage, userFactory, productFactory);
			
			if(!newInstance) {
				bpLogger.info("This instance will be the singleton.");
				defaultImpl = instance;
			}
		}
		else {
			bpLogger.trace("Returned th singleton");
			instance = defaultImpl;
		}

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
		
		bpLogger.info("Trying to create a new User.");
		
		if(login == null) throw new IllegalArgumentException("Login cannot be null !");
		
		if(login.length() == 0) throw new IllegalArgumentException("Login cannot be void !");
		
		User user = this.storage.get(User.class).
						where("login").isEqualTo(login).
						firstRow();
		
		if(user != null)
			throw new UserAlreadyExistsException(user);
		
		user = this.userFactory.createNewUser(login, name, forename);
		
		this.storage.store(user);
		
		bpLogger.info("New user created and stored.");
		
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
		
		return this.storage.get(Bid.class).
							where(BidState.class).isEqualTo(BidState.PUBLISHED).
					asList();

	}
	

}
