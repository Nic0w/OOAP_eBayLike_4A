/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;
import java.util.List;

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

/**
 * @author Nic0w
 *
 */
public class UserSpecifications {

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

	/**
	 * The user is identified by a login - Test 1 :
	 * 		the login cannot be null.
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testUserLoginCannotBeNull() throws UserAlreadyExistsException, StorageException {

		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);

		User nullLoginUser = bidPlatform.newUser(null, "Benjamin", "Franklin");
	}

	/**
	 * The user is identified by a login - Test 2 :
	 * 		the login cannot be void (String length = 0).
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testUserLoginCannotBeVoid() throws UserAlreadyExistsException, StorageException {

		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);

		User voidLoginUser = bidPlatform.newUser("", "Benjamin", "Franklin");
	}

	/**
	 * The user is identified by a login - Test 3 :
	 * 		The user's login is usable.
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test
	public final void testUserLoginIsUsable() throws UserAlreadyExistsException, StorageException {

		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);

		String testLogin = "testLogin";
		User testLoginUser = bidPlatform.newUser(testLogin, "Benjamin", "Franklin");

		assertThat(testLoginUser.getLogin(), is(testLogin));
	}

	/**
	 * The user is identified by a login - Test 4 :
	 * 		The user's login is unique in the system.
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test(expected=UserAlreadyExistsException.class)
	public final void testUserLoginIsUnique() throws UserAlreadyExistsException, StorageException {

		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);

		String uniqueLogin = "aUniqueLogin";

		User firstUser  = bidPlatform.newUser(uniqueLogin, "Benjamin", "Franklin");
		User secondUser = bidPlatform.newUser(uniqueLogin, "Benjamin", "Franklin");
	}


	/**
	 * Test if the user has a name and a forename.
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test
	public final void testUserHasNameAndForename() throws UserAlreadyExistsException, StorageException {

		String testName 	= "aRandomName";
		String testForename = "aRandomForename"; 

		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);

		User testUser = bidPlatform.newUser("aRandomLogin", testName, testForename);

		assertThat(testUser.getFirstName(), is(testName));
		assertThat(testUser.getLastName(), is(testForename));
	}

	/**
	 * Test if an User can be a Buyer, a Seller, and both.
	 */
	@Test
	public final void testUserRoles() {
		assertTrue(Seller.class.isAssignableFrom(User.class));
		assertTrue(Buyer.class.isAssignableFrom(User.class));	
	}

	/**
	 *  Test if a User as a Seller can create a Bid and publish it
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test
	public final void testUserCreateAndPublishBid() throws UserAlreadyExistsException, StorageException{

		Date limit = DateHelper.getTomorrowSameHour();
		
		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);

		Seller user  = bidPlatform.newUser("aRandomLogin", "Benjamin", "Franklin");

		Product p = bidPlatform.newProduct("test");
		
		Bid bid = user.createBid(p, limit);

		user.publishBid(bid);
	}

	/**
	 *  Test if a User as a Buyer can do Offers for a Bid published by another User than himself
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test
	public final void testUserDoOfferBidAlreadyPublished() throws UserAlreadyExistsException, StorageException{

		Date limit = DateHelper.getTomorrowSameHour();
		float price = 0;

		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);

		Product p =  bidPlatform.newProduct("test");

		User user1  = bidPlatform.newUser("aRandomLogin", "Benjamin", "Franklin");
		Buyer user2 = bidPlatform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");


		Bid bid = user1.createBid(p, limit);
		
		user1.publishBid(bid);

		user2.bid(bid, price);
	}

	/**
	 *  Test if it is possible to do an Offer for a unpublished Bid
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test(expected=IllegalActionException.class)
	public final void testOfferOnUnpublishedBid() throws UserAlreadyExistsException, StorageException {
		
		Date limit = DateHelper.getTomorrowSameHour();
		float price = 0;

		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);

		User user1  = bidPlatform.newUser("aRandomLogin", "Benjamin", "Franklin");
		Buyer user2  = bidPlatform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");

		Product p =  bidPlatform.newProduct("test");
		
		Bid bid = user1.createBid(p, limit);

		user2.bid(bid, price);
	}


	/**
	 * A published Bid is visible to the other users
	 * 
	 * @throws StorageException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test
	public final void testVisibleBidPublished() throws UserAlreadyExistsException, StorageException{
		
		BidPlatform bidPlatform = BidPlatform.getDefaultInstance(true);
		
		
		Seller seller1  = bidPlatform.newUser("aRandomLogin", "Benjamin", "Franklin");
		Seller seller2  = bidPlatform.newUser("aRandomLogin2", "George", "Washington");
		
		
		Product car   = bidPlatform.newProduct("a car");
		Product bike  = bidPlatform.newProduct("a bike");
		Product plane = bidPlatform.newProduct("a plane");
		Product boat  = bidPlatform.newProduct("a boat");
		
		Date limit = DateHelper.getTomorrowSameHour();
		
		seller1.createBid(car,   limit).publishIt(seller1);
		seller1.createBid(bike,  limit).publishIt(seller1);
		seller2.createBid(boat,  limit).publishIt(seller2);
		seller2.createBid(plane, limit).publishIt(seller2);
		
		
		List<Bid> publishedBids = bidPlatform.getPublishedBids();
		
		assertThat(publishedBids.size(), is(4));
		
		
	}
}
