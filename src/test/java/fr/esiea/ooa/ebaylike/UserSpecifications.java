/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.JavaCollectionsPersistenceAgent;

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

		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();
		
		BidPlatform bidPlatform = new BidPlatformBuilder(storageAgent).build();
		
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

		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();
		
		BidPlatform bidPlatform = new BidPlatformBuilder(storageAgent).build();
		
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

		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();
		
		BidPlatform bidPlatform = new BidPlatformBuilder(storageAgent).build();

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

		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();
		
		BidPlatform bidPlatform = new BidPlatformBuilder(storageAgent).build();
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
		
		JavaCollectionsPersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();
		
		BidPlatform bidPlatform = new BidPlatformBuilder(storageAgent).build();
		
		User testUser = bidPlatform.newUser("aRandomLogin", testName, testName);
		
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
	
	
	
}
