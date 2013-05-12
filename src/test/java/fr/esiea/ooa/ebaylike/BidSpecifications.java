/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.DateHelper;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.IllegalActionException;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.DefaultOffer;

/**
 * @author Nic0w
 *
 */
public class BidSpecifications {

	/**
	 * Test if a Bid is associated with a product which has an Id and a Description.
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test
	public final void testBidArguments() throws UserAlreadyExistsException, StorageException{

		Date limit = DateHelper.getTomorrowSameHour();
		
		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user1 = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		String desc = "test";
		
		Product p = platform.newProduct("test");
		
		Bid bid = user1.createBid(p, limit);

		assertFalse(bid.getProduct() == null);
		
		Product product = bid.getProduct();

		assertFalse(product.getID() == null);
		assertThat(product.getDescription(), is(desc));
	}

	/**
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 * 
	 */
	@Test(expected=NullPointerException.class)
	public final void testBidMustHaveALimitDate() throws UserAlreadyExistsException, StorageException {
		
		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Product p = platform.newProduct("test");
		
		Date limit = null;
		
		user.createBid(p, limit);
	}
	
	/**
	 * Test if the Bid has got a limited date and his state after this date
	 */
	@Test
	public final void testBidFinishedState() throws UserAlreadyExistsException, StorageException{

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Product p = platform.newProduct("test");
		
		Date limite = DateHelper.getDateFor(1, 0, 2000); //1er Janvier 2000
		
		Bid bid = user.createBid(p, limite).publishIt(user);

		assertTrue(bid.isFinished());
	}

	/**
	 * It should be impossible to add an offer if the Bid is finished.
	 */
	@Test(expected=IllegalActionException.class)
	public final void testAddOfferOnFinishedBid() throws UserAlreadyExistsException, StorageException{

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user   = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		Buyer buyer = platform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");
		
		Product p = platform.newProduct("test");
		
		Date limite = DateHelper.getDateFor(1, 0, 2000); //1er Janvier 2000
		
		Bid bid = user.createBid(p, limite).publishIt(user);

		buyer.bid(bid, 5);
	}
	
	/**
	 * A seller cannot add an offer on his own Bid.
	 */
	@Test(expected=IllegalActionException.class)
	public final void testOfferOnUnpublishedBid() throws UserAlreadyExistsException, StorageException{

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		User user = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Product p = platform.newProduct("test");
		
		Date limit = DateHelper.getTomorrowSameHour();
		
		Bid bid = user.createBid(p, limit).publishIt(user);
		
		user.bid(bid, 5);
	}
	
	/**
	 * Test if the Seller can set a minimum price on its Bid
	 */
	@Test
	public final void testMinimumPriceOffer() throws UserAlreadyExistsException, StorageException{

		
		Date limit = DateHelper.getTomorrowSameHour();
		
		float price = 5;

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Product p = platform.newProduct("test");
		
		Bid bid = seller.createBid(p, limit);		
		
		bid.setMinimumPrice(seller, price);
		
		assertThat(bid.getMinPrice(), is(price));
	}
	
	/**
	 * It should be impossible to make an offer below the minimum price.
	 */
	@Test(expected=IllegalActionException.class)
	public final void testOfferBelowMinPrice() throws UserAlreadyExistsException, StorageException{
		
		Date limit = DateHelper.getTomorrowSameHour();
		
		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		
		Buyer buyer  = platform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");

		Product p = platform.newProduct("test");
		
		float minPrice = 20;
		Bid bid = seller.createBid(p, limit).
					setMinimumPrice(seller, minPrice).
					publishIt(seller);
		
		buyer.bid(bid, 2);
	}
	
	
	/**
	 * It should be impossible to make an offer below the minimum price.
	 */
	@Test(expected=IllegalActionException.class)
	public final void testOfferBelowLastOffer() throws UserAlreadyExistsException, StorageException{
		
		Date limit = DateHelper.getTomorrowSameHour();
		
		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		
		Buyer buyer  = platform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");
		Buyer buyer2  = platform.newUser("aRandomLogin3", "Benjamin3", "Franklin3");

		Product p = platform.newProduct("test");
		
		Bid bid = seller.createBid(p, limit).publishIt(seller);
		
		buyer.bid(bid, 5);
		
		buyer2.bid(bid, 4);
	}
	
	/**
	 * Test if the Min price is visible to the other users
	 */
	@Test
	public final void testMinPriceIsVisible() throws UserAlreadyExistsException, StorageException{

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Date limit = DateHelper.getTomorrowSameHour();
		Product p = platform.newProduct("test");
		float minPrice = 20;
		
		Bid bid = seller.createBid(p, limit).
							setMinimumPrice(seller, minPrice);
		
		assertThat(bid.getMinPrice(), is(minPrice));
	}
	
	/**
	 * The Seller can make a reserve price on his Bid
	 */
	@Test
	public final void testReservePriceBid() throws UserAlreadyExistsException, StorageException {

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Product p = platform.newProduct("test");
		Date limit = DateHelper.getTomorrowSameHour();
		float price = 20;
		
		Bid bid = seller.createBid(p, limit).
							setReservePrice(seller, price);
	}
	
	/**
	 * Test if the Reserve price is visible only by the Seller
	 */
	@Test
	public final void testVisibleReservePrice() throws UserAlreadyExistsException, StorageException {
		fail("Not implemented yet !");
	}
	
	
	/**
	 * Test if the Buyer can know if the Reserve Price has been reached by an other Seller
	 */
	@Test
	public final void testReservePriceReached() throws UserAlreadyExistsException, StorageException{

		Date limit = DateHelper.getTomorrowSameHour();
		float price = 20;

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		
		Buyer buyer1 = platform.newUser("aRandomLogin1", "Benjamin1", "Franklin1");
		Buyer buyer2 = platform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");
		
		Product p = platform.newProduct("test");
		
		float reservePrice = 20;
		
		Bid bid = seller.createBid(p, limit).
					setReservePrice(seller, reservePrice).
					publishIt(seller);
		
		buyer1.bid(bid, 19);

		assertFalse(bid.hasReservePriceBeenReached());
		
		buyer2.bid(bid, 21);
		
		assertTrue(bid.hasReservePriceBeenReached());
	}
	
	/**
	 * The Seller cannot cancel a Bid if there are Offers which have reached the Reserve Price.
	 */
	@Test(expected=IllegalActionException.class)
	public final void testCannotCancelBidIfReservePriceReached() throws UserAlreadyExistsException, StorageException{

		Date limit = DateHelper.getTomorrowSameHour();

		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		Buyer buyer = platform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");

		Product p = platform.newProduct("test");
		
		float reservePrice = 10;
		float offerPrice   = 15; 
		
		Bid bid = seller.createBid(p, limit).
					setReservePrice(seller, reservePrice).
					publishIt(seller);
		
		buyer.bid(bid, offerPrice);
		
		bid.cancelIt(seller);
	}

}
