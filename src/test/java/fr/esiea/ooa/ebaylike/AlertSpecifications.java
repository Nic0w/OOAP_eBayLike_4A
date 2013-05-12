/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.DateHelper;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;

/**
 * @author nic0w
 *
 */
public class AlertSpecifications {

	@Test
	public void testAutomaticAlertOnSeller() throws UserAlreadyExistsException, StorageException {
		
		Date limit = DateHelper.getTomorrowSameHour();
		
		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Seller seller = platform.newUser("aRandomLogin", "Benjamin", "Franklin");
		
		Buyer buyer  = platform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");
		Buyer buyer2  = platform.newUser("aRandomLogin3", "Benjamin3", "Franklin3");

		Product p = platform.newProduct("test");
		
	}

}
