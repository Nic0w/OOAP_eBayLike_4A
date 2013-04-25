package fr.esiea.ooa.ebaylike;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.JavaCollectionsPersistenceAgent;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();
		
		BidPlatform bidPlatform = new BidPlatformBuilder(storageAgent).build();
		
		try {
			User u = bidPlatform.newUser("noob", "Noob", "Noob");
			
			System.out.println(u);
			
			Bid b = u.createBid(null, null).setMinimumPrice(0).setReservePrice(0).publishIt();
			
			
			
			u.bid(b, 0);
			
		} catch (UserAlreadyExistsException | StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
