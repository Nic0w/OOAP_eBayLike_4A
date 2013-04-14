/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.ooa.ebaylike.core.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.iface.Buyer;
import fr.esiea.ooa.ebaylike.iface.Seller;
import fr.esiea.ooa.ebaylike.iface.User;

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
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testUserLoginCannotBeNull() {

		BidPlatform bidPlateform = new BidPlatform();
		
		User nullLoginUser = bidPlateform.newUser(null, "Benjamin", "Franklin");
	}
	
	/**
	 * The user is identified by a login - Test 2 :
	 * 		the login cannot be void (String length = 0).
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testUserLoginCannotBeVoid() {

		BidPlatform bidPlateform = new BidPlatform();
		
		User voidLoginUser = bidPlateform.newUser("", "Benjamin", "Franklin");
	}
	
	/**
	 * The user is identified by a login - Test 3 :
	 * 		The user's login is usable.
	 */
	@Test
	public final void testUserLoginIsUsable() {

		BidPlatform bidPlateform = new BidPlatform();

		String testLogin = "testLogin";
		User testLoginUser = bidPlateform.newUser(testLogin, "Benjamin", "Franklin");
		
		assertThat(testLoginUser.getLogin(), is(testLogin));
	}
	
	/**
	 * The user is identified by a login - Test 4 :
	 * 		The user's login is unique in the system.
	 */
	@Test(expected=UserAlreadyExistsException.class)
	public final void testUserLoginIsUnique() {

		BidPlatform bidPlateform = new BidPlatform();

		String uniqueLogin = "aUniqueLogin";
		
		User firstUser  = bidPlateform.newUser(uniqueLogin, "Benjamin", "Franklin");
		User secondUser = bidPlateform.newUser(uniqueLogin, "Benjamin", "Franklin");
	}
	
	
	/**
	 * Test if the user has a name and a forename.
	 */
	@Test
	public final void testUserHasNameAndForename() {
		
		String testName 	= "aRandomName";
		String testForename = "aRandomForename"; 
		
		BidPlatform bidPlateform = new BidPlatform();
		
		User testUser = bidPlateform.newUser("aRandomLogin", testName, testName);
		
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
