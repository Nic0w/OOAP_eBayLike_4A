/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.junit.Assert.*;

import java.util.Date;

import javax.swing.DefaultButtonModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.BidState;
import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.DefaultOffer;
import fr.esiea.ooa.ebaylike.default_impl.JavaCollectionsPersistenceAgent;
import fr.esiea.ooa.ebaylike.default_impl.factory.DefaultBidFactory;

/**
 * @author Nic0w
 *
 */
public class BidSpecifications {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test if A bid has got an id and a description
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testBidArguments() throws UserAlreadyExistsException, StorageException{

		Date limit = null;
		Product p = null;
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user1 = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Bid bid = user1.createBid(p, limit);

		Product product = bid.getProduct();

		String id = product.getID();
		String desc = product.getDescription();

	}

	/**
	 * Test if the Bid has got a limited date and his state after this date
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testDateAndStateBid() throws UserAlreadyExistsException, StorageException{

		Date limit = null;
		Product p;
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user1 = platform.newUser("aRandomLogin", "Benjamin", "Franklin");


		Bid bid = user1.createBid(p, limit);


		Date limitDate = bid.getLimitDate();

		bid.isFinished();

	}

	/**
	 * Test if it is possible to make offer on a Bid as soon as this one is published
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testOfferBidPublished() throws UserAlreadyExistsException, StorageException{

		Date limit = null;
		Product p = null;
		Offer o = new DefaultOffer();
		
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user1 = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Bid bid = user1.createBid(p, limit);
		user1.publishBid(bid);

		
		bid.addOffer(o);
		
	}
	
	/**
	 * Test if the Offer has got an Emmiter and a price
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testOfferEmiiterAndPrice() throws UserAlreadyExistsException, StorageException{

		
		Offer o = new DefaultOffer();
		
		float price = o.getPrice();
		Buyer emitter = o.getEmitter();
		
	}
	
	/**
	 * Test if the Buyer can precise a minimum price on a Bid
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testMinimumPriceOffer() throws UserAlreadyExistsException, StorageException{

		
		Date limit = null;
		Product p = null;
		float price = 0;
		Offer o = new DefaultOffer();
		
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Bid bid = seller.createBid(p, limit);		
		bid.setMinimumPrice(seller, price);
		seller.publishBid(bid);

	}
	
	/**
	 * Test if it is possible to make an offer below the minimum price
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testOfferBelowMinPrice() throws UserAlreadyExistsException, StorageException{

		
		Date limit = null;
		Product p = null;
		float price = 20;
		Offer o = new DefaultOffer();
		
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		Buyer buyer  = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Bid bid = seller.createBid(p, limit);
		bid.setMinimumPrice(seller, price);
		seller.publishBid(bid);
		
		
		buyer.bid(bid, 2);
		
	}
	
	/**
	 * Test if the Min price is visible to the other users
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testVisibleMinPrice() throws UserAlreadyExistsException, StorageException{

		
	}
	
	/**
	 * The Seller can make a reserve price on his Bid
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testReservPriceBid() throws UserAlreadyExistsException, StorageException{

		
		Date limit = null;
		Product p = null;
		float price = 20;
		Offer o = new DefaultOffer();
		
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Bid bid = seller.createBid(p, limit);
		
		bid.setReservePrice(seller, price);
		
	}
	
	/**
	 * Test if the Reserve price is visible only by the Seller
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testVisibleReservePrice() throws UserAlreadyExistsException, StorageException{

		
	}
	
	
	/**
	 * Test if the Buyer can know if the Reserve Price has been reached by an other Seller
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testReservePriceReached() throws UserAlreadyExistsException, StorageException{

		Date limit = null;
		Product p = null;
		float price = 20;
		Offer o = new DefaultOffer();
		
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		Buyer buyer = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Bid bid = seller.createBid(p, limit);	
		bid.setReservePrice(seller, price);
		seller.publishBid(bid);

		boolean reach = bid.hasReservePriceBeenReached();
		
		
	}
	
	/**
	 * Test if the Seller can cancel a Bid only if their is no Offers which have reached the Reserve Price 
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testSellerCancelBid() throws UserAlreadyExistsException, StorageException{

		Date limit = null;
		Product p = null;
		float price = 20;
		Offer o = new DefaultOffer();
		
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		Buyer buyer = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Bid bid = seller.createBid(p, limit);	
		bid.setReservePrice(seller, price);
		seller.publishBid(bid);
		
		buyer.bid(bid, price);
		
		bid.cancelIt(seller);
	}
	
	/**
	 * Test if a Seller can make an Offers on his own Bid
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testSellerMakeOfferOnhisOwn() throws UserAlreadyExistsException, StorageException{

		Date limit = null;
		Product p = null;
		float price = 20;
		Offer o = new DefaultOffer();
		
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Bid bid = user.createBid(p, limit);	
		bid.setReservePrice(user, price);
		user.publishBid(bid);
		
		user.bid(bid, price);
				
	}
	
	
}
